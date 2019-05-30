package com.sanyedu.feedback.model;

class ItemBean {
    private String itemPhoto;
    private String itemName;
    private String itemDetail;

    public ItemBean(String itemPhoto, String itemName, String itemDetail) {
        this.itemPhoto = itemPhoto;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
    }

    public String getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(String itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "itemPhoto='" + itemPhoto + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDetail='" + itemDetail + '\'' +
                '}';
    }
}
