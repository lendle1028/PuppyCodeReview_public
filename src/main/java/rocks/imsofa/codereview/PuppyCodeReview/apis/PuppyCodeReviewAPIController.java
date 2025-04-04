/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rocks.imsofa.codereview.PuppyCodeReview.PuppyCodeReviewParameter;
import rocks.imsofa.codereview.PuppyCodeReview.services.PuppyCodeReviewService;
import rocks.imsofa.codereview.ReviewResults;

/**
 *
 * @author USER
 */
@RestController
public class PuppyCodeReviewAPIController {
    @Autowired
    private PuppyCodeReviewService puppyCodeReviewService=null;
    
    @PostMapping("/api/review")
    public ReviewResults review(@RequestBody PuppyCodeReviewParameter parameter) throws Exception{
        return puppyCodeReviewService.review(
                parameter.getLanguage(), 
                parameter.getObjective(), 
                parameter.getStudentCode(), 
                parameter.getAnswer());
    }
}
