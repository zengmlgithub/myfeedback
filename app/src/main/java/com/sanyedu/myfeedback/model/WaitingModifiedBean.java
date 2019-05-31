package com.sanyedu.myfeedback.model;



import java.util.ArrayList;

public class WaitingModifiedBean {
    private String date;
    private ArrayList<FeedbackBean> beanArrayList;

    public WaitingModifiedBean(String date, ArrayList<FeedbackBean> beanArrayList) {

        this.date = date;
        this.beanArrayList = beanArrayList;
    }


    public WaitingModifiedBean() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<FeedbackBean> getBeanArrayList() {
        return beanArrayList;
    }

    public void setBeanArrayList(ArrayList<FeedbackBean> beanArrayList) {
        this.beanArrayList = beanArrayList;
    }

    @Override
    public String toString() {
        return "WaitingModifiedBean{" +
                "date='" + date + '\'' +
                ", beanArrayList=" + beanArrayList +
                '}';
    }
}
