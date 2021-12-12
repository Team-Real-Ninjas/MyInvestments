package edu.famu.myinvestments.models;


import com.google.cloud.firestore.annotation.DocumentId;

public class User {
    @DocumentId
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String username;
    protected String emailAddress;
    protected String userImage;
    protected String password;
    protected Boolean admin;


    public User() {

    }

    public User(String id, String firstName, String lastName, String phoneNumber,
                String username, String emailAddress, String userImage,
                String password, Boolean admin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.emailAddress = phoneNumber;
        this.userImage = userImage;
        this.password = password;
        this.admin = admin;
    }

    public User(String id,String firstName, String lastName, String phoneNumber,
                String username, String emailAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.emailAddress = emailAddress;
        //this.userImage = userImage;
        //this.password = password;
        //this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}