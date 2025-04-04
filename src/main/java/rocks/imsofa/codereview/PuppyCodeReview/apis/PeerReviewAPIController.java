/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rocks.imsofa.codereview.PuppyCodeReview.entities.PeerReviewResultsEntity;
import rocks.imsofa.codereview.PuppyCodeReview.models.PeerReviewTask;
import rocks.imsofa.codereview.PuppyCodeReview.services.PeerReviewTaskManagementService;

/**
 *
 * @author lendle
 */
@RestController
public class PeerReviewAPIController {
    @Autowired
    private PeerReviewTaskManagementService peerReviewTaskManagementService=null;
    
    @GetMapping("/api/requestPeerReviewTask")
    public PeerReviewTask requestPeerReviewTask(){
        return peerReviewTaskManagementService.requestPeerReview();
    }
    
    @PostMapping("/api/submitPeerReviewResult/taskId/{taskId}")
    public void submitPeerReviewResult(String taskId, @RequestBody PeerReviewResultsEntity peerReviewResultsEntity){
        peerReviewTaskManagementService.submitPeerReviewResult(taskId, peerReviewResultsEntity);
    }
}
