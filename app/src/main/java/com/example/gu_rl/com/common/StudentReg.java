package com.example.gu_rl.com.common;

public class StudentReg {

    private String id;
    private String enrollment;
    private String email;
    private String pass;
    private String regDate;
    private String imageName;
    private String imageUri;

    public StudentReg() {
    }

    public StudentReg(String imageName, String imageUri) {

        //{}
        if(imageName.trim().equals("")){
            imageName = "no name";
        }


        this.imageName = imageName;
        this.imageUri = imageUri;
    }

    public StudentReg(String id, String enrollment, String email, String pass, String regDate) {
        this.id = id;
        this.enrollment = enrollment;
        this.email = email;
        this.pass = pass;
        this.regDate = regDate;
    }

    public StudentReg(String id, String enrollment, String email, String pass, String regDate, String imageUri) {
        this.id = id;
        this.enrollment = enrollment;
        this.email = email;
        this.pass = pass;
        this.regDate = regDate;
        this.imageUri = imageUri;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getId() {
        return id;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getRegDate() {
        return regDate;
    }
}
