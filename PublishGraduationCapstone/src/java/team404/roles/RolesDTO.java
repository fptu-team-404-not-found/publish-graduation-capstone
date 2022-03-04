package team404.roles;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "roles")
public class RolesDTO implements Serializable {

    private int roleId;
    private String roleName;

    public RolesDTO() {
        roleId = 0;
        roleName = "";
    }

    public RolesDTO(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RolesDTO{" + "roleId=" + roleId + ", roleName=" + roleName + '}';
    }
}
