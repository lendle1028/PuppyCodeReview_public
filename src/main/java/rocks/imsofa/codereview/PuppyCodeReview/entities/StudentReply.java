/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author USER
 */
@Entity
public class StudentReply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String author;
    private Date createdDate;
    private Date lastModified;
    
    @ManyToOne(optional = true)
    private Quiz quiz=null;
    
    @Column(columnDefinition = "longvarchar")
    private String code=null;
    
    @OneToOne(optional = true)
    @Cascade(CascadeType.ALL)
    private ReviewResultsEntity reviewResults=null;
    
    @Column(columnDefinition = "BOOLEAN default false not null")
    private boolean peerReviewed=false;
    
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<PeerReviewResultsEntity> peerReviewResults=new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz Quiz) {
        this.quiz = Quiz;
    }

    public ReviewResultsEntity getReviewResults() {
        return reviewResults;
    }

    public void setReviewResults(ReviewResultsEntity reviewResults) {
        this.reviewResults = reviewResults;
    }

    public List<PeerReviewResultsEntity> getPeerReviewResults() {
        return peerReviewResults;
    }

    public void setPeerReviewResults(List<PeerReviewResultsEntity> peerReviewResults) {
        this.peerReviewResults = peerReviewResults;
    }

    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    public void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
    }

    
    
}
