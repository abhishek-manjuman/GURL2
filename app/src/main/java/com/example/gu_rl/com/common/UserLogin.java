package com.example.gu_rl.com.common;

public class UserLogin {

    String id, enrollment, email, password, status, userType,strDate;

    public UserLogin() {
    }

    public UserLogin(String id, String enrollment, String email, String password, String ststus, String userType, String strDate) {
        this.id = id;
        this.enrollment = enrollment;
        this.email = email;
        this.password = password;
        this.status = ststus;
        this.userType = userType;
        this.strDate = strDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String ststus) {
        this.status = ststus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }
}
