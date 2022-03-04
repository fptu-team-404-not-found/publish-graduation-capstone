package team404.account;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
public class AccountDTO implements Serializable {

    private String sub;
    private String email;
    private String name;
    private String picture;
    private int roleId;

    public AccountDTO() {
        sub = "";
        email = "";
        name = "";
        picture = "";
        roleId = 1;
    }

    public AccountDTO(String sub, String email, String name, String picture, int roleId) {
        this.sub = sub;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "sub=" + sub + ", email=" + email + ", name=" + name + ", picture=" + picture + ", roleId=" + roleId + '}';
    }
}
