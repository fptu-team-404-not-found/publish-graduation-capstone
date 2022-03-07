package team404.account;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import team404.roles.RolesDTO;

@XmlRootElement(name = "account")
public class AccountDTO implements Serializable {

    private String email;
    private String name;
    private String picture;
    private RolesDTO role;

    public AccountDTO() {
        email = "";
        name = "";
        picture = "";
        role = null;
    }

    public AccountDTO(String email, String name, String picture, RolesDTO role) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.role = role;
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
     * @return the role
     */
    public RolesDTO getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(RolesDTO role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "email=" + email + ", name=" + name + ", picture=" + picture + ", role=" + role + '}';
    }
}
