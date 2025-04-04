/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.apis;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rocks.imsofa.codereview.PuppyCodeReview.daos.QuizDao;
import rocks.imsofa.codereview.PuppyCodeReview.entities.StudentReply;
import rocks.imsofa.codereview.PuppyCodeReview.services.StudentReplyService;

/**
 *
 * @author lendle
 */
@RestController
public class StudentReplyAPIController {
    @Autowired
    private StudentReplyService studentReplyService=null;
    @Autowired
    private QuizDao quizDao=null;
    @GetMapping("/api/studentReplies/quizId/{id}")
    public List<StudentReply> findByQuizId(@PathVariable("id") int id){
        return studentReplyService.findByQuizId(id);
    }
    
    @PostMapping("/api/studentReply")
    public void add(@RequestBody StudentReply studentReply){
        Date date=new Date();
        studentReply.setCreatedDate(date);
        studentReply.setLastModified(date);
//        studentReply.setQuiz(quizDao.findById(studentReply.getQuiz().getId()).get());
        studentReplyService.add(studentReply);
    }
    
    @DeleteMapping("/api/studentReply/id/{id}")
    public void delete(@PathVariable("id") long id){
        studentReplyService.delete(id);
    }
}
