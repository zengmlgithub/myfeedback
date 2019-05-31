package com.sanyedu.myfeedback.model;

public class FeedbackBean {
    private String headUrl;
    private String departName;
    private String address;
    private String title;
    private String desciption;
    private String typeUrl;
    private String photo1;
    private String photo2;
    private String photo3;

    public FeedbackBean(String headUrl, String departName, String address, String title, String desciption, String typeUrl, String photo1, String photo2, String photo3) {
        this.headUrl = headUrl;
        this.departName = departName;
        this.address = address;
        this.title = title;
        this.desciption = desciption;
        this.typeUrl = typeUrl;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }

    public FeedbackBean() {
    }


    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getTypeUrl() {
        return typeUrl;
    }

    public void setTypeUrl(String typeUrl) {
        this.typeUrl = typeUrl;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }


    @Override
    public String toString() {
        return "FeedbackBean{" +
                "headUrl='" + headUrl + '\'' +
                ", departName='" + departName + '\'' +
                ", address='" + address + '\'' +
                ", title='" + title + '\'' +
                ", desciption='" + desciption + '\'' +
                ", typeUrl='" + typeUrl + '\'' +
                ", photo1='" + photo1 + '\'' +
                ", photo2='" + photo2 + '\'' +
                ", photo3='" + photo3 + '\'' +
                '}';
    }
}
