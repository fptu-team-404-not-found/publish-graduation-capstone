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
import team404.roles.RolesDAO;
import team404.roles.RolesDTO;
import team404.supervisor.SupervisorDTO;
import team404.teammember.TeamMemberDTO;
import team404.utils.DBHelpers;

public class AccountDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public boolean checkEmail(String email) {
        try {
            //1. make connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Select Email "
                        + "From Account "
                        + "Where Email = ?";
                //3. Create statement obj to load SQL string
                //and set value to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
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

    public AccountDTO getEmail(String email) {
        try {
            //1. make connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Select Email "
                        + "From Account "
                        + "Where Email = ?";
                //3. Create statement obj to load SQL string
                //and set value to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    String email2 = rs.getString("Email");
                    AccountDTO dto = new AccountDTO();
                    dto.setEmail(email2);
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

    public boolean createNewAcccount(AccountDTO dto) {

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert Into Account"
                        + "(Email, Name, Picture, RoleId)"
                        + " Values(?, ?, ?, 1)";
                stm = con.prepareStatement(sql);
                RolesDAO rolesDao = new RolesDAO();
                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getName());
                stm.setString(3, dto.getPicture());
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

    public void createNewAccountAdminMode(String email, TeamMemberDTO teamMemberDTO) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Account(Email, RoleId) "
                        + "Values(?, 1) "
                        + "Insert into TeamMember(StudentId, MemberName, MemberAvatar, Phone, Account) "
                        + "values(?, ?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, teamMemberDTO.getMemberId());
                stm.setNString(3, teamMemberDTO.getMemberName());
                stm.setString(4, teamMemberDTO.getMemberAvatar());
                stm.setString(5, teamMemberDTO.getPhone());
                stm.setString(6, email);
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

    public void createSupervisorAccountInAdminMode(String email, SupervisorDTO supervisorDTO) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Account(Email, RoleId) "
                        + "Values(?, 1) "
                        + "INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position, [Status], Account) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, supervisorDTO.getSupervisorId());
                stm.setNString(3, supervisorDTO.getSupervisorName());
                stm.setString(4, supervisorDTO.getSupervisorImage());
                stm.setNString(5, supervisorDTO.getInformation());
                stm.setString(6, supervisorDTO.getPostion());
                stm.setBoolean(7, supervisorDTO.isStatus());
                stm.setString(8, email);
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

    public AccountDTO getUserInforByEmail(String email) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From Account "
                        + "Where Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                RolesDAO rolesDao = new RolesDAO();
                RolesDTO rolesDto = new RolesDTO();
                if (rs.next()) {
                    String email2 = rs.getString("Email");
                    String name = rs.getNString("Name");
                    String picture = rs.getString("Picture");
                    rolesDto = rolesDao.getRoles(rs.getInt("RoleId"));
                    AccountDTO dto = new AccountDTO();
                    dto.setEmail(email2);
                    dto.setName(name);
                    dto.setPicture(picture);
                    dto.setRole(rolesDto);
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

    public void updateRole(String email, int roleId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Account "
                        + "Set RoleId = ? "
                        + "Where Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, roleId);
                stm.setString(2, email);
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

                while (rs.next()) {
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

    public int getRole(String email) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select r.[RoleId] "
                        + "From Account a inner join Roles r "
                        + "On a.RoleId = r.RoleId "
                        + "Where Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int role = rs.getInt("RoleId");
                    return role;
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
        return -1;
    }

    public List<AccountDTO> getAllAccountList() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select acc.Email, r.RoleId "
                        + "From Account acc inner join Roles r "
                        + "On acc.RoleId = r.RoleId ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<AccountDTO> list = new ArrayList<>();

                RolesDAO rolesDao = new RolesDAO();
                RolesDTO rolesDto = new RolesDTO();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    rolesDto = rolesDao.getRoles(rs.getInt("RoleId"));
                    AccountDTO dto = new AccountDTO();
                    dto.setEmail(email);
                    dto.setRole(rolesDto);
                    list.add(dto);
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

    public List<AccountDTO> searchAccountInAdmin(String keyword) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Email, RoleId "
                        + "From Account "
                        + "Where Email Like ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                rs = stm.executeQuery();
                List<AccountDTO> list = new ArrayList<>();
                RolesDAO rolesDao = new RolesDAO();
                RolesDTO rolesDto = new RolesDTO();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    rolesDto = rolesDao.getRoles(rs.getInt("RoleId"));
                    AccountDTO accountDTO = new AccountDTO();
                    accountDTO.setEmail(email);
                    accountDTO.setRole(rolesDto);

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

    public void updateRoleInAdminMode(String email) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Account "
                        + "Set RoleId = 2 "
                        + "Where Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
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
}
