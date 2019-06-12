package com.sanyedu.myfeedback.model;

import java.io.Serializable;

public class ChangeFeedbackBean implements Serializable {
    private String feedbackId;
    private String feedbackStatus;
    private String feedbackContent;
    private String feedbackPerid;
    private String feedbackPername; //关闭人名称
    private String feedbackPerdept; //关闭人部门
    private String feedbackFilea; //整改图1
    private String feedbackFileb;//整改图2
    private String feedbackFilec;//整改图3

    public ChangeFeedbackBean(String feedbackId, String feedbackStatus, String feedbackContent, String feedbackPerid, String feedbackPername, String feedbackPerdept, String feedbackFilea, String feedbackFileb, String feedbackFilec) {
        this.feedbackId = feedbackId;
        this.feedbackStatus = feedbackStatus;
        this.feedbackContent = feedbackContent;
        this.feedbackPerid = feedbackPerid;
        this.feedbackPername = feedbackPername;
        this.feedbackPerdept = feedbackPerdept;
        this.feedbackFilea = feedbackFilea;
        this.feedbackFileb = feedbackFileb;
        this.feedbackFilec = feedbackFilec;
    }

    public ChangeFeedbackBean() {
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getFeedbackPerid() {
        return feedbackPerid;
    }

    public void setFeedbackPerid(String feedbackPerid) {
        this.feedbackPerid = feedbackPerid;
    }

    public String getFeedbackPername() {
        return feedbackPername;
    }

    public void setFeedbackPername(String feedbackPername) {
        this.feedbackPername = feedbackPername;
    }

    public String getFeedbackPerdept() {
        return feedbackPerdept;
    }

    public void setFeedbackPerdept(String feedbackPerdept) {
        this.feedbackPerdept = feedbackPerdept;
    }

    public String getFeedbackFilea() {
        return feedbackFilea;
    }

    public void setFeedbackFilea(String feedbackFilea) {
        this.feedbackFilea = feedbackFilea;
    }

    public String getFeedbackFileb() {
        return feedbackFileb;
    }

    public void setFeedbackFileb(String feedbackFileb) {
        this.feedbackFileb = feedbackFileb;
    }

    public String getFeedbackFilec() {
        return feedbackFilec;
    }

    public void setFeedbackFilec(String feedbackFilec) {
        this.feedbackFilec = feedbackFilec;
    }

    @Override
    public String toString() {
        return "ChangeFeedbackBean{" +
                "feedbackId='" + feedbackId + '\'' +
                ", feedbackStatus='" + feedbackStatus + '\'' +
                ", feedbackContent='" + feedbackContent + '\'' +
                ", feedbackPerid='" + feedbackPerid + '\'' +
                ", feedbackPername='" + feedbackPername + '\'' +
                ", feedbackPerdept='" + feedbackPerdept + '\'' +
                ", feedbackFilea='" + feedbackFilea + '\'' +
                ", feedbackFileb='" + feedbackFileb + '\'' +
                ", feedbackFilec='" + feedbackFilec + '\'' +
                '}';
    }
}
