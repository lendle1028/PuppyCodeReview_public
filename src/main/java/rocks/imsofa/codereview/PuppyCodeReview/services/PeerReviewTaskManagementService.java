/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.services;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rocks.imsofa.codereview.PuppyCodeReview.daos.StudentReplyDao;
import rocks.imsofa.codereview.PuppyCodeReview.entities.PeerReviewResultsEntity;
import rocks.imsofa.codereview.PuppyCodeReview.entities.StudentReply;
import rocks.imsofa.codereview.PuppyCodeReview.models.PeerReviewTask;
import rocks.imsofa.codereview.PuppyCodeReview.models.PeerReviewTaskState;

/**
 *
 * @author USER
 */
@Service
public class PeerReviewTaskManagementService {

    @Autowired
    private StudentReplyDao studentReplyDao = null;
    private List<PeerReviewTask> tasks = new ArrayList<>();

    private static final int MIN_PEERREVIEW_COUNT = 3;
    private static final long REVIEW_DURATION = 1 * 60 * 60 * 1000;//an hour

    public synchronized void refreshTasks() {
        //purge completed and timeout tasks
        List<PeerReviewTask> newQueue = new ArrayList<>();
        for (PeerReviewTask task : tasks) {
            if (task.getState().equals(PeerReviewTaskState.REVIEWING)
                    && task.getReviewDeadline() > System.currentTimeMillis()) {
                newQueue.add(task);
            }
        }
        //scan for StudentReply instances that haven't completed all peer reviewed
        List<StudentReply> notReviewedReply = studentReplyDao.findByPeerReviewed(false);
        for (StudentReply s : notReviewedReply) {
            if(s.getCode()==null || s.getCode().isBlank()){
                continue;
            }
            //fork multiple instances
            for (int i = 0; i < (MIN_PEERREVIEW_COUNT - s.getPeerReviewResults().size()); i++) {
                PeerReviewTask task = new PeerReviewTask();
                task.setStudentReply(s);
                //open for peer review (but not assigned)
                task.setState(PeerReviewTaskState.OPENED);
                newQueue.add(task);
            }
        }

        this.tasks.clear();
        this.tasks.addAll(newQueue);
        Collections.shuffle(tasks);
        Logger.getLogger(this.getClass().getName()).info("PeerReviewTask queue refreshed, now there are "+tasks.size()+" tasks in queue");
    }

    public synchronized PeerReviewTask requestPeerReview() {
        for (PeerReviewTask task : tasks) {
            if (task.getState().equals(PeerReviewTaskState.OPENED)) {
                task.setState(PeerReviewTaskState.REVIEWING);
                task.setReviewStartTime(System.currentTimeMillis());
                task.setReviewDeadline(task.getReviewStartTime() + REVIEW_DURATION);
                return task;
            }
        }
        //if no available slot, generate a new one
        
        if (tasks != null && tasks.size() > 0) {
            PeerReviewTask existingTask=tasks.get((int) (Math.random()*tasks.size()));
            PeerReviewTask task = new PeerReviewTask();
            task.setStudentReply(existingTask.getStudentReply());
            //open for peer review (but not assigned)
            task.setState(PeerReviewTaskState.REVIEWING);
            task.setReviewStartTime(System.currentTimeMillis());
            task.setReviewDeadline(task.getReviewStartTime() + REVIEW_DURATION);
            tasks.add(task);
            return task;
        }
        return null;
    }

    @Transactional
    public synchronized void submitPeerReviewResult(String taskId, PeerReviewResultsEntity peerReviewResultsEntity) {
        long now = System.currentTimeMillis();
        for (PeerReviewTask task : tasks) {
            Logger.getLogger(this.getClass().getName()).info("submitting peerreview for "+taskId);
            if (task.getId().equals(taskId) && task.getReviewDeadline() > now) {
                task.setState(PeerReviewTaskState.FINISHED);
                task.setReviewStartTime(System.currentTimeMillis());
                task.setReviewDeadline(task.getReviewStartTime() + REVIEW_DURATION);
                Optional<StudentReply> sOptional=studentReplyDao.findById(task.getStudentReply().getId());
                if(sOptional.isPresent()){
                    StudentReply studentReply=sOptional.get();
                    studentReply.getPeerReviewResults().add(peerReviewResultsEntity);
                    if(studentReply.getPeerReviewResults().size() >= MIN_PEERREVIEW_COUNT){
                        studentReply.setPeerReviewed(true);
                    }
                    Logger.getLogger(this.getClass().getName()).info("peer review entry appended to StudentReply "+studentReply.getId());
                    studentReplyDao.save(studentReply);
                }
                break;
            }
        }
    }
}
