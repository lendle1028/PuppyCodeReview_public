/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rocks.imsofa.codereview.PuppyCodeReview.daos.StudentReplyDao;
import rocks.imsofa.codereview.PuppyCodeReview.entities.StudentReply;

/**
 *
 * @author lendle
 */
@Service
public class StudentReplyService {
    @Autowired
    private StudentReplyDao studentReplyDao=null;
    public List<StudentReply> findByQuizId(int id){
        return studentReplyDao.findByQuiz_Id(Sort.by(Sort.Order.asc("author"), Sort.Order.desc("lastModified")), id);
    }
    
    @Transactional
    public void add(StudentReply studentReply){
        studentReplyDao.save(studentReply);
    }
    
    @Transactional
    public void delete(long id){
        studentReplyDao.deleteById(id);
    }
}
