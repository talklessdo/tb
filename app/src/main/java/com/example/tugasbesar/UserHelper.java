package com.example.tugasbesar;

public class UserHelper {
    String nama, usernama, email, phone, passwd;

    public UserHelper() {
    }

    public UserHelper(String nama, String usernama, String email, String phone, String passwd) {
        this.nama = nama;
        this.usernama = usernama;
        this.email = email;
        this.phone = phone;
        this.passwd = passwd;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsernama() {
        return usernama;
    }

    public void setUsernama(String usernama) {
        this.usernama = usernama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
