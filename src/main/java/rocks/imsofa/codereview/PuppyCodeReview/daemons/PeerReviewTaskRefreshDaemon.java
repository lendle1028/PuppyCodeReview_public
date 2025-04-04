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
import rocks.imsofa.codereview.PuppyCodeReview.entities.ReviewResultsEntity;
import rocks.imsofa.codereview.PuppyCodeReview.entities.StudentReply;
import rocks.imsofa.codereview.PuppyCodeReview.services.PeerReviewTaskManagementService;
import rocks.imsofa.codereview.PuppyCodeReview.services.PuppyCodeReviewService;
import rocks.imsofa.codereview.ReviewResults;

/**
 *
 * @author lendle
 */
@Component
public class PeerReviewTaskRefreshDaemon extends Thread{
    @Autowired
    private PeerReviewTaskManagementService peerReviewTaskManagementService=null;

    public PeerReviewTaskRefreshDaemon() {
        this.setDaemon(true);
    }
    
    public void run(){
        while(true){
            try {
                peerReviewTaskManagementService.refreshTasks();
                Thread.sleep(1 * 60 * 60 * 1000);
            } catch (Exception ex) {
                Logger.getLogger(PeerReviewTaskRefreshDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @PostConstruct
    public void init(){
        this.start();
    }
}
