/**
  * Copyright 2019 bejson.com 
  */
package com.sanyedu.myfeedback.model;

/**
 * Auto-generated: 2019-06-03 15:6:32
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PersonBean {

    private String id;
    private String teName;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setTeName(String teName) {
         this.teName = teName;
     }
     public String getTeName() {
         return teName;
     }

    @Override
    public String toString() {
        return "PersonBean{" +
                "id='" + id + '\'' +
                ", teName='" + teName + '\'' +
                '}';
    }
}