/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.daos;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import rocks.imsofa.codereview.PuppyCodeReview.entities.StudentReply;

/**
 *
 * @author lendle
 */
public interface StudentReplyDao extends JpaRepository<StudentReply, Long>{
    public List<StudentReply> findByReviewResultsIsNull();
    public List<StudentReply> findByQuiz_Id(Sort sort, long id);
    public List<StudentReply> findByQuiz_Id(long id);
    public List<StudentReply> findByPeerReviewed(boolean peerReviewed);
}
