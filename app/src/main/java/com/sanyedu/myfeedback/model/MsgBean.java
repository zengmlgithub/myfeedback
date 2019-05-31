package com.sanyedu.myfeedback.model;

public class MsgBean {

    private String title;
    private String content;
    private String typePhto;
    private String person;
    private String dateStr;

    public MsgBean(String title, String content, String typePhto, String person, String dateStr) {
        this.title = title;
        this.content = content;
        this.typePhto = typePhto;
        this.person = person;
        this.dateStr = dateStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypePhto() {
        return typePhto;
    }

    public void setTypePhto(String typePhto) {
        this.typePhto = typePhto;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public String toString() {
        return "MsgBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", typePhto='" + typePhto + '\'' +
                ", person='" + person + '\'' +
                ", dateStr='" + dateStr + '\'' +
                '}';
    }
}
