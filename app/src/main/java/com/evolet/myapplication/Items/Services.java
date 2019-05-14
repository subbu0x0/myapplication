package com.evolet.myapplication.Items;

import java.io.Serializable;

public class Services implements Serializable {
    public String name,phone,address,aadhar,age,details,fee,prodname,prodprice,status;

    public Services(String name, String phone, String address, String aadhar, String age, String details, String fee, String prodname, String prodprice,String status) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.aadhar = aadhar;
        this.age = age;
        this.details = details;
        this.fee = fee;
        this.prodname = prodname;
        this.prodprice = prodprice;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Services() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdprice() {
        return prodprice;
    }

    public void setProdprice(String prodprice) {
        this.prodprice = prodprice;
    }
}
