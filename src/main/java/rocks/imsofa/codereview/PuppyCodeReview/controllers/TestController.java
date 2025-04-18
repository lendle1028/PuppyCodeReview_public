/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rocks.imsofa.codereview.PuppyCodeReview.models.PeerReviewTask;
import rocks.imsofa.codereview.PuppyCodeReview.services.PeerReviewTaskManagementService;

/**
 *
 * @author lendle
 */
@Controller
public class TestController {
    @Autowired
    private PeerReviewTaskManagementService service=null;
    @GetMapping("/test")
    public String testAction(){
        for(int i=0; i<1000; i++){
            PeerReviewTask task=service.requestPeerReview();
            System.out.print(task.getStudentReply().getId()+",");
        }
        return "test";
    }
}
