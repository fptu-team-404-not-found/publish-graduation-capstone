package team404.account;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import team404.roles.RolesDTO;

@XmlRootElement(name = "account")
public class AccountDTO implements Serializable {

    private String sub;
    private String email;
    private String name;
    private String picture;
    private RolesDTO role;

    public AccountDTO() {
        sub = "";
        email = "";
        name = "";
        picture = "";
        role = null;
    }

    public AccountDTO(String sub, String email, String name, String picture, RolesDTO roleId) {
        this.sub = sub;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.role = roleId;
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

    /**
     * @return the roleId
     */
    public RolesDTO getRole() {
        return role;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRole(RolesDTO role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "sub=" + sub + ", email=" + email + ", name=" + name + ", picture=" + picture + ", role=" + role + '}';
    }
    
    
}
