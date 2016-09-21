package com.augmentum.oes.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.augmentum.oes.converter.DateSerializer;

public class Exam implements Serializable{

    private Integer id = 0;
    private String examId;
    private String examName;
    private String description;
    private Date effectiveTime;
    private String duration;
    private String questionQuantity;
    private String questionPoints;
    private String passCriteria;
    private String fullScore;
    private String creator;
    private String type = "java";
    private String isUsed = "false";
    private String isDeleted = "false";


    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getQuestionQuantity() {
        return questionQuantity;
    }

    public void setQuestionQuantity(String questionQuantity) {
        this.questionQuantity = questionQuantity;
    }

    public String getQuestionPoints() {
        return questionPoints;
    }

    public void setQuestionPoints(String questionPoints) {
        this.questionPoints = questionPoints;
    }

    public String getPassCriteria() {
        return passCriteria;
    }

    public void setPassCriteria(String passCriteria) {
        this.passCriteria = passCriteria;
    }

    public String getFullScore() {
        return fullScore;
    }

    public void setFullScore(String fullScore) {
        this.fullScore = fullScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    @JsonDeserialize(using = DateSerializer.class)
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
