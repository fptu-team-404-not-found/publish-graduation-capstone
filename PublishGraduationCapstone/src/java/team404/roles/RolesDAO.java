package team404.roles;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import team404.utils.DBHelpers;

public class RolesDAO implements Serializable{
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    private List<RolesDTO> rolesList;

    public List<RolesDTO> getRolesList() {
        try{
            con = DBHelpers.makeConnection();
            if(con != null)
            {
                String sql = "Select * "
                        + "From Roles ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<RolesDTO> list = new ArrayList<>();
                while(rs.next())
                {
                    RolesDTO dto = new RolesDTO(rs.getInt("RoleId"), rs.getString("RoleName"));
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public RolesDTO getRoles(int roleId){
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select * "
                        + "From Roles "
                        + "Where RoleId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, roleId);
                rs = stm.executeQuery();
                if(rs.next()){
                    int roleId2 = rs.getInt("RoleId");
                    String roleName = rs.getString("RoleName");
                    RolesDTO dto = new RolesDTO(roleId, roleName);
                    return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
