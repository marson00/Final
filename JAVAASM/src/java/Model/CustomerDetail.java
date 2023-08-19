/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class CustomerDetail {
    private String accountID;
    private char gender;
    private int age;
    private String phone;
    private String address;
    private int points;
    private String loginID; 
    private String username;
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
 
    
    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }
    
    public CustomerDetail() {
    }
    
    public CustomerDetail (String accountID, String gender, int age, String phone, String address, int points){
        this.accountID = accountID;
        this.address = address;
        this.age = age;
        this.gender = gender.charAt(0);
        this.phone = phone;
        this.points = points;
    }
    
    public CustomerDetail (String accountID,String username,String gender, int age, String phone, String address, int points, String loginID){
       this.accountID = accountID;
        this.address = address;
        this.age = age;
        this.gender = gender.charAt(0);
        this.loginID = loginID;
        this.phone = phone;
        this.points = points;
        this.username = username;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}

