package com.sanyedu.myfeedback.model;

import java.io.Serializable;

public class TeacherBean implements Serializable {
    private String createtime;
    private String id;
    private String password;
    private String teComp;
    private String teDept;
    private String teEmail;
    private String teFlag;
    private String teJobnum;
    private String teName;
    private String tePhone;
    private String tePosi;
    private String teSex;
    private String username;


    public TeacherBean(String createtime, String id, String password, String teComp, String teDept, String teEmail, String teFlag, String teJobnum, String teName, String tePhone, String tePosi, String teSex, String username) {
        this.createtime = createtime;
        this.id = id;
        this.password = password;
        this.teComp = teComp;
        this.teDept = teDept;
        this.teEmail = teEmail;
        this.teFlag = teFlag;
        this.teJobnum = teJobnum;
        this.teName = teName;
        this.tePhone = tePhone;
        this.tePosi = tePosi;
        this.teSex = teSex;
        this.username = username;
    }

    public TeacherBean() {
    }


    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeComp() {
        return teComp;
    }

    public void setTeComp(String teComp) {
        this.teComp = teComp;
    }

    public String getTeDept() {
        return teDept;
    }

    public void setTeDept(String teDept) {
        this.teDept = teDept;
    }

    public String getTeEmail() {
        return teEmail;
    }

    public void setTeEmail(String teEmail) {
        this.teEmail = teEmail;
    }

    public String getTeFlag() {
        return teFlag;
    }

    public void setTeFlag(String teFlag) {
        this.teFlag = teFlag;
    }

    public String getTeJobnum() {
        return teJobnum;
    }

    public void setTeJobnum(String teJobnum) {
        this.teJobnum = teJobnum;
    }

    public String getTeName() {
        return teName;
    }

    public void setTeName(String teName) {
        this.teName = teName;
    }

    public String getTePhone() {
        return tePhone;
    }

    public void setTePhone(String tePhone) {
        this.tePhone = tePhone;
    }

    public String getTePosi() {
        return tePosi;
    }

    public void setTePosi(String tePosi) {
        this.tePosi = tePosi;
    }

    public String getTeSex() {
        return teSex;
    }

    public void setTeSex(String teSex) {
        this.teSex = teSex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TeacherBean{" +
                "createtime='" + createtime + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", teComp='" + teComp + '\'' +
                ", teDept='" + teDept + '\'' +
                ", teEmail='" + teEmail + '\'' +
                ", teFlag='" + teFlag + '\'' +
                ", teJobnum='" + teJobnum + '\'' +
                ", teName='" + teName + '\'' +
                ", tePhone='" + tePhone + '\'' +
                ", tePosi='" + tePosi + '\'' +
                ", teSex='" + teSex + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
