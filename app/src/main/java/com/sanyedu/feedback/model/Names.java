package com.sanyedu.feedback.model;

public class Names {
    String address;
    String names;

    public Names(String address, String names) {
        this.address = address;
        this.names = names;
    }

    public Names() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "Names{" +
                "address='" + address + '\'' +
                ", names='" + names + '\'' +
                '}';
    }
}
