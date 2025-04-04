/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rocks.imsofa.codereview.PuppyCodeReview.daos.QuizDao;
import rocks.imsofa.codereview.PuppyCodeReview.entities.Quiz;

/**
 *
 * @author USER
 */
@Service
public class QuizService {
    @Autowired
    private QuizDao quizDao=null;
    
    public List<Quiz> findAllByOrderByCreatedDate(){
        return quizDao.findAll(Sort.by(Sort.Order.desc("createdDate"), Sort.Order.asc("subject")));
    }
    
    public Optional<Quiz> findById(long id){
        return quizDao.findById(id);
    }
    
    @Transactional
    public void update(Quiz quiz){
        quiz.setLastModified(new Date());
        quizDao.save(quiz);
    }
    
    @Transactional
    public void add(Quiz quiz){
        quiz.setCreatedDate(new Date());
        quiz.setLastModified(new Date());
        quizDao.save(quiz);
    }
    
    @Transactional
    public void delete(long id){
        quizDao.deleteById(id);
    }
}
