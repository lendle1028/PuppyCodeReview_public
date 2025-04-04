/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.models;

import java.util.UUID;
import rocks.imsofa.codereview.PuppyCodeReview.entities.StudentReply;


/**
 * a PeerReviewTask is a task used for tracking the status
 * of the peer review process of a StudentReply
 * @author USER
 */
public class PeerReviewTask {
    private String id=UUID.randomUUID().toString();
    private PeerReviewTaskState state=PeerReviewTaskState.OPENED;
    private long reviewStartTime=-1;
    private long reviewDeadline=-1;
    private StudentReply studentReply=null;

    public String getId() {
        return id;
    }

    public StudentReply getStudentReply() {
        return studentReply;
    }

    public void setStudentReply(StudentReply studentReply) {
        this.studentReply = studentReply;
    }
    
    public PeerReviewTaskState getState() {
        return state;
    }

    public void setState(PeerReviewTaskState state) {
        this.state = state;
    }

    public long getReviewStartTime() {
        return reviewStartTime;
    }

    public void setReviewStartTime(long reviewStartTime) {
        this.reviewStartTime = reviewStartTime;
    }

    public long getReviewDeadline() {
        return reviewDeadline;
    }

    public void setReviewDeadline(long reviewDeadline) {
        this.reviewDeadline = reviewDeadline;
    }
    
    
}
