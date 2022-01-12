/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.user;

import java.io.Serializable;

/**
 *
 * @author jike
 */
public class UserDTO implements Serializable{
    private String sub;
    private String email;
    private String name;
    private String picture;

    public UserDTO() {
    }

    public UserDTO(String sub, String email, String name, String picture) {
        this.sub = sub;
        this.email = email;
        this.name = name;
        this.picture = picture;
    }
    
    /**
     * @return the sub
     */
    public String getSub() {
        return sub;
    }

    /**
     * @param sub the sub to set
     */
    public void setSub(String sub) {
        this.sub = sub;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "sub=" + sub + ", email=" + email + ", name=" + name + ", picture=" + picture + '}';
    }
    
}
