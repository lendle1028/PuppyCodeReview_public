/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rocks.imsofa.codereview;

/**
 *
 * @author USER
 */
public interface PuppyCodeReviewer {
    public ReviewResults review(String language, String objective, String studentCode, String answer) throws Exception;
}
