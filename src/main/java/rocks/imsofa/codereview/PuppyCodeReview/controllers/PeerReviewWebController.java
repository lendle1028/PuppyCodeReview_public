/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rocks.imsofa.codereview.PuppyCodeReview.models.PeerReviewTask;
import rocks.imsofa.codereview.PuppyCodeReview.services.PeerReviewTaskManagementService;

/**
 *
 * @author USER
 */
@Controller
public class PeerReviewWebController {
    @Autowired
    private PeerReviewTaskManagementService service=null;
    @GetMapping("/studentReply/peerreview")
    public String peerReviewWebAction(){
        return "studentReply/peerreview";
    }
}
