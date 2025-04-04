/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import rocks.imsofa.codereview.PuppyCodeReview.entities.Quiz;

/**
 *
 * @author USER
 */
public interface QuizDao extends JpaRepository<Quiz, Long>{
    
}
