/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.apis;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rocks.imsofa.codereview.PuppyCodeReview.daos.QuizDao;
import rocks.imsofa.codereview.PuppyCodeReview.daos.StudentReplyDao;
import rocks.imsofa.codereview.PuppyCodeReview.entities.PeerReviewResultsEntity;
import rocks.imsofa.codereview.PuppyCodeReview.entities.Quiz;
import rocks.imsofa.codereview.PuppyCodeReview.entities.StudentReply;

/**
 *
 * @author USER
 */
@RestController
public class InitTestDataAPIController {
    @Autowired
    private QuizDao quizDao;
    @Autowired
    private StudentReplyDao studentReplyDao;
    
    @GetMapping("/api/clearReview")
    @Transactional
    public void clearReviewAction(){
        List<StudentReply> list=studentReplyDao.findAll();
        for(StudentReply s : list){
            s.setReviewResults(null);
            studentReplyDao.save(s);
        }
    }
    @GetMapping("/api/initTestData")
    @Transactional
    public void testDataAction(){
//        Quiz quiz=new Quiz();
//        quiz.setLanguage("java");
//        quiz.setSubject("Hello");
//        quiz.setAuthor("test");
//        quiz.setCreatedDate(new Date());
//        quiz.setDescription("hello");
//        quiz.setLastModified(new Date());
//        quiz.setObjective("print hello");
//        quiz.setCode("System.out.println(\"hello\");");
//        quizDao.save(quiz);
        List<Quiz> quizs=quizDao.findAll();
        System.out.println(quizs.size());
        StudentReply studentReply=new StudentReply();
        studentReply.setAuthor("test2");
        studentReply.setCode("print(\"hello\")");
        studentReply.setCreatedDate(new Date());
        studentReply.setLastModified(new Date());
        studentReply.setQuiz(quizs.get(0));
        
        PeerReviewResultsEntity peerReviewResultsEntity=new PeerReviewResultsEntity();
        peerReviewResultsEntity.setAuthor("test");
        peerReviewResultsEntity.setComplexityScore(50);
        peerReviewResultsEntity.setCorrectnessScore(50);
        peerReviewResultsEntity.setDesignScore(50);
        peerReviewResultsEntity.setFunctionalityScore(50);
        peerReviewResultsEntity.setSmellScore(50);
        peerReviewResultsEntity.getKeywords().addAll(List.of("A", "B", "C"));
        studentReply.getPeerReviewResults().add(peerReviewResultsEntity);
        
        peerReviewResultsEntity=new PeerReviewResultsEntity();
        peerReviewResultsEntity.setAuthor("test2");
        peerReviewResultsEntity.setComplexityScore(60);
        peerReviewResultsEntity.setCorrectnessScore(50);
        peerReviewResultsEntity.setDesignScore(50);
        peerReviewResultsEntity.setFunctionalityScore(50);
        peerReviewResultsEntity.setSmellScore(50);
        peerReviewResultsEntity.getKeywords().addAll(List.of("A", "B", "D"));
        studentReply.getPeerReviewResults().add(peerReviewResultsEntity);
        
        studentReplyDao.save(studentReply);
    }
}
