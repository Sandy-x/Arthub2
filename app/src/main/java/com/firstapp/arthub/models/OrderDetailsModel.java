package com.firstapp.arthub.models;

public class OrderDetailsModel {
    String orderid
            ,firstname
            ,lastname
            ,phoneno
            ,whatsappno
            ,OrderDate
            ,description
            ,email
            ,category
            ,selectedbudget
            ,papersize
            ,framecharges
            ,frame
            ,referenceimage
            ,addressline1
            ,addressline2
            ,State
            ,pincode
            ,totalcharges;

    public OrderDetailsModel(String orderid,String firstname, String lastname, String phoneno, String whatsappno,String orderDate, String description, String email, String category, String selectedbudget, String papersize, String framecharges, String frame, String referenceimage, String addressline1, String addressline2, String state, String pincode,String totalcharges) {
        this.orderid = orderid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneno = phoneno;
        this.whatsappno = whatsappno;
        this.description = description;
        this.email = email;
        this.category = category;
        this.selectedbudget = selectedbudget;
        this.papersize = papersize;
        this.framecharges = framecharges;
        this.frame = frame;
        this.referenceimage = referenceimage;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        State = state;
        OrderDate = orderDate;
        this.pincode = pincode;
        this.totalcharges = totalcharges;
    }

    public OrderDetailsModel() {
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTotalcharges() {
        return totalcharges;
    }

    public void setTotalcharges(String totalcharges) {
        this.totalcharges = totalcharges;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getWhatsappno() {
        return whatsappno;
    }

    public void setWhatsappno(String whatsappno) {
        this.whatsappno = whatsappno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSelectedbudget() {
        return selectedbudget;
    }

    public void setSelectedbudget(String selectedbudget) {
        this.selectedbudget = selectedbudget;
    }

    public String getPapersize() {
        return papersize;
    }

    public void setPapersize(String papersize) {
        this.papersize = papersize;
    }

    public String getFramecharges() {
        return framecharges;
    }

    public void setFramecharges(String framecharges) {
        this.framecharges = framecharges;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getReferenceimage() {
        return referenceimage;
    }

    public void setReferenceimage(String referenceimage) {
        this.referenceimage = referenceimage;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
