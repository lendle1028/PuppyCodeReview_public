/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.services;

import org.springframework.stereotype.Service;
import rocks.imsofa.codereview.PuppyCodeReview.CacheablePuppyCodeReviewer;
import rocks.imsofa.codereview.PuppyCodeReviewer;
import rocks.imsofa.codereview.ReviewResults;

/**
 *
 * @author USER
 */
@Service
public class PuppyCodeReviewService {
    private PuppyCodeReviewer puppyCodeReviewer=new CacheablePuppyCodeReviewer();
    public ReviewResults review(String language, String objective, String studentCode, String answer) throws Exception{
        if(studentCode==null){
            return new ReviewResults();
        }
        return this.puppyCodeReviewer.review(language, objective, studentCode, answer);
    }
}
