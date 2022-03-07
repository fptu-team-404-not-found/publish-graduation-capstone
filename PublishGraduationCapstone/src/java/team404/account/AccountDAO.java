package team404.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import team404.roles.RolesDTO;
import team404.utils.DBHelpers;

public class AccountDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    //Lỗi
    public boolean checkId(String id) {
        try {
            //1. make connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Select UserId "
                        + "From Account "
                        + "Where UserId = ?";
                //3. Create statement obj to load SQL string
                //and set value to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    //Lỗi
    public boolean createNewAcccount(AccountDTO dto) {

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert Into Account("
                        + "UserId, email, name, picture"
                        + ") Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                //stm.setString(1, dto.getSub());
                stm.setString(2, dto.getEmail());
                stm.setString(3, dto.getName());
                stm.setString(4, dto.getPicture());
                int affectedRow = stm.executeUpdate();
                if (affectedRow > 0) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public AccountDTO getUserNamePictureByEmail(String email) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Name, Picture "
                        + "From Account "
                        + "Where Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getNString("Name");
                    String picture = rs.getString("Picture");

                    AccountDTO dto = new AccountDTO();
                    dto.setName(name);
                    dto.setPicture(picture);

                    return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    //Lỗi
    public AccountDTO getUserInforByUserId(String userId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From Account "
                        + "Where UserId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    //AccountDTO dto = new AccountDTO(rs.getString("UserId"), rs.getString("Email"), rs.getNString("Name"), rs.getString("Picture"), rs.getInt("RoleId"));
                    //return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    //Lỗi
    public void updateRole(String userId, int roleId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Account "
                        + "Set RoleId = ? "
                        + "Where UserId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, roleId);
                stm.setString(2, userId);
                stm.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<AccountDTO> showAccountList() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Account.Email, Roles.RoleName, Roles.RoleId "
                        + "From Account JOIN Roles "
                        + "On Account.RoleId = Roles.RoleId ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                
                List<AccountDTO> list = new ArrayList<>();
                
                while(rs.next()) {
                    String email = rs.getString("Email");
                    String roleName = rs.getString("RoleName");
                    int roleId = rs.getInt("RoleId");
                    
                    RolesDTO rolesDTO = new RolesDTO(roleId, roleName);
                    
                    AccountDTO accountDTO = new AccountDTO();
                    accountDTO.setEmail(email);
                    accountDTO.setRole(rolesDTO);
                    
                    list.add(accountDTO);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
