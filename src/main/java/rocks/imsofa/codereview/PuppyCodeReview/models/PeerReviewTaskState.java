/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.models;

/**
 *
 * @author USER
 */
public enum PeerReviewTaskState {
    OPENED, /*opened, waiting for some one to be assigned*/
    REVIEWING, /*assigned to someone*/
    FINISHED /*completed by someone*/
}
