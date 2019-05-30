package com.sanyedu.feedback.model;

import java.util.ArrayList;

public class GroupBean {
    private String groupPhoto;
    private String departName;
    private String mydate;
    ArrayList<ItemBean> itemList;

    public GroupBean(){

    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public void setGroupPhoto(String groupPhoto) {
        this.groupPhoto = groupPhoto;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getMydate() {
        return mydate;
    }

    public void setMydate(String mydate) {
        this.mydate = mydate;
    }

    public ArrayList<ItemBean> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<ItemBean> itemList) {
        this.itemList = itemList;
    }
}
