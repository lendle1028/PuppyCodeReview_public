/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author lendle
 */
@Entity
public class ReviewResultsEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private double correctnessScore=-1, designScore=-1, functionalityScore=-1, complexityScore=-1, smellScore=-1;
    
    @Column(columnDefinition = "longvarchar")
    private String rawJson=null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCorrectnessScore() {
        return correctnessScore;
    }

    public void setCorrectnessScore(double correctnessScore) {
        this.correctnessScore = correctnessScore;
    }

    public double getDesignScore() {
        return designScore;
    }

    public void setDesignScore(double designScore) {
        this.designScore = designScore;
    }

    public double getFunctionalityScore() {
        return functionalityScore;
    }

    public void setFunctionalityScore(double functionalityScore) {
        this.functionalityScore = functionalityScore;
    }

    public double getComplexityScore() {
        return complexityScore;
    }

    public void setComplexityScore(double complexityScore) {
        this.complexityScore = complexityScore;
    }

    public double getSmellScore() {
        return smellScore;
    }

    public void setSmellScore(double smellScore) {
        this.smellScore = smellScore;
    }

    public String getRawJson() {
        return rawJson;
    }

    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }
    
    
    
}
