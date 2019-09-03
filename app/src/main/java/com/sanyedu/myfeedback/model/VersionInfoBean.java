package com.sanyedu.myfeedback.model;

public class VersionInfoBean {
   private String addressDow;
   private String id;
   private String versionNum;
   private String msg;
   private String apkPic;


   public VersionInfoBean(){}
   public VersionInfoBean(String addressDow,String id,String versionNum){
       this.addressDow = addressDow;
       this.id = id;
       this.versionNum = versionNum;
   }


    public String getApkPic() {
        return apkPic;
    }

    public void setApkPic(String apkPic) {
        this.apkPic = apkPic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAddressDow() {
        return addressDow;
    }

    public void setAddressDow(String addressDow) {
        this.addressDow = addressDow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    @Override
    public String toString() {
        return "VersionInfoBean{" +
                "addressDow='" + addressDow + '\'' +
                ", id='" + id + '\'' +
                ", versionNum='" + versionNum + '\'' +
                '}';
    }
}
