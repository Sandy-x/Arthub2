package com.firstapp.arthub.models;

public class my_ordersModel {
    String orderid,OrderDate,totalcharges,category,referenceimage;

    public my_ordersModel() {
    }

    public my_ordersModel(String orderid, String orderDate, String totalcharges, String category,String referenceimage) {
        this.orderid = orderid;
        OrderDate = orderDate;
        this.totalcharges = totalcharges;
        this.category = category;
        this.referenceimage = referenceimage;
    }

    public String getReferenceimage() {
        return referenceimage;
    }

    public void setReferenceimage(String referenceimage) {
        this.referenceimage = referenceimage;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getTotalcharges() {
        return totalcharges;
    }

    public void setTotalcharges(String totalcharges) {
        this.totalcharges = totalcharges;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
