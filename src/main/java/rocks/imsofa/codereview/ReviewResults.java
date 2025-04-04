package rocks.imsofa.codereview;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lendle
 */
public class ReviewResults {
    private double correctnessScore=-1, designScore=-1, functionalityScore=-1, complexityScore=-1, smellScore=-1;
    private String correctnessExplain=null;
    private String reviewOverview=null;
    private String smellOverview=null;
    
    private List<Issue> design=new ArrayList<>();
    private List<Issue> complexity=new ArrayList<>();
    private List<Issue> functionality=new ArrayList<>();
    private List<String> keywords=new ArrayList<>();

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

    public String getCorrectnessExplain() {
        return correctnessExplain;
    }

    public void setCorrectnessExplain(String correctnessExplain) {
        this.correctnessExplain = correctnessExplain;
    }

    public String getReviewOverview() {
        return reviewOverview;
    }

    public void setReviewOverview(String reviewOverview) {
        this.reviewOverview = reviewOverview;
    }

    public String getSmellOverview() {
        return smellOverview;
    }

    public void setSmellOverview(String smellOverview) {
        this.smellOverview = smellOverview;
    }

    public List<Issue> getDesign() {
        return design;
    }

    public void setDesign(List<Issue> design) {
        this.design = design;
    }

    public List<Issue> getComplexity() {
        return complexity;
    }

    public void setComplexity(List<Issue> complexity) {
        this.complexity = complexity;
    }

    public List<Issue> getFunctionality() {
        return functionality;
    }

    public void setFunctionality(List<Issue> functionality) {
        this.functionality = functionality;
    }

    @Override
    public String toString() {
        Gson gson=new Gson();
        return gson.toJson(this);
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    
    
}
