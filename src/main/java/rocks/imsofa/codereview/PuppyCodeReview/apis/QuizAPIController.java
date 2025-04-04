/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.apis;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rocks.imsofa.codereview.PuppyCodeReview.entities.Quiz;
import rocks.imsofa.codereview.PuppyCodeReview.services.QuizService;

/**
 *
 * @author USER
 */
@RestController
public class QuizAPIController {
    @Autowired
    private QuizService quizService=null;
    
    @GetMapping("/api/quizzes")
    public List<Quiz> findAll(){
        return quizService.findAllByOrderByCreatedDate();
    }
    
    @GetMapping("/api/quiz/id/{id}")
    public Quiz findById(@PathVariable("id") int id){
        Optional<Quiz> ret=quizService.findById(id);
        if(ret.isPresent()){
            return ret.get();
        }else{
            return null;
        }
    }
    
    @PutMapping("/api/quiz")
    public void update(@RequestBody Quiz quiz){
        quizService.update(quiz);
    }
    
    @PostMapping("/api/quiz")
    public void add(@RequestBody Quiz quiz){
        quizService.add(quiz);
    }
    
    @DeleteMapping("/api/quiz/id/{id}")
    public void delete(@PathVariable("id") long id){
        quizService.delete(id);
    }
}
