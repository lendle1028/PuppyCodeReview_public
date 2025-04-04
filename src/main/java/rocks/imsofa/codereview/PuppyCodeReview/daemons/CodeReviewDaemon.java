/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.daemons;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.imsofa.codereview.PuppyCodeReview.daos.StudentReplyDao;
import rocks.imsofa.codereview.PuppyCodeReview.entities.ReviewResultsEntity;
import rocks.imsofa.codereview.PuppyCodeReview.entities.StudentReply;
import rocks.imsofa.codereview.PuppyCodeReview.services.PuppyCodeReviewService;
import rocks.imsofa.codereview.ReviewResults;

/**
 *
 * @author lendle
 */
@Component
public class CodeReviewDaemon extends Thread{
    @Autowired
    private StudentReplyDao studentReplyDao=null;
    
    @Autowired
    private PuppyCodeReviewService puppyCodeReviewService=null;

    public CodeReviewDaemon() {
        this.setDaemon(true);
    }
    
    public void run(){
        while(true){
            try {
                List<StudentReply> list=studentReplyDao.findByReviewResultsIsNull();
                if(list!=null){
                    Gson gson=new Gson();
                    for(StudentReply studentReply : list){
                        ReviewResults review=puppyCodeReviewService.review(
                                studentReply.getQuiz().getLanguage(), 
                                studentReply.getQuiz().getObjective(), 
                                studentReply.getCode(),
                                studentReply.getQuiz().getCode());
                        ReviewResultsEntity reviewResultsEntity=new ReviewResultsEntity();
                        reviewResultsEntity.setCorrectnessScore(review.getCorrectnessScore());
                        reviewResultsEntity.setComplexityScore(review.getComplexityScore());
                        reviewResultsEntity.setDesignScore(review.getDesignScore());
                        reviewResultsEntity.setFunctionalityScore(review.getFunctionalityScore());
                        reviewResultsEntity.setSmellScore(review.getSmellScore());
                        reviewResultsEntity.setRawJson(gson.toJson(review));
                        studentReply.setReviewResults(reviewResultsEntity);
                        studentReplyDao.save(studentReply);
                    }
                }
                Thread.sleep(30000);
            } catch (Exception ex) {
                Logger.getLogger(CodeReviewDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @PostConstruct
    public void init(){
        this.start();
    }
}
