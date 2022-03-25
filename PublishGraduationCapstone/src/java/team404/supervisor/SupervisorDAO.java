package team404.supervisor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import team404.account.AccountDAO;
import team404.account.AccountDTO;
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;
import team404.utils.DBHelpers;

public class SupervisorDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    //-- TIENHUYNHTN --//
    public List<SupervisorDTO> getSupervisorsInPojectDetail(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select s.SupervisorName, s.SupervisorImage, s.Information, s.Position "
                        + "From Project p inner join Project_Supervisor ps "
                        + "on p.ProjectId = ps.ProjectId "
                        + "inner join Supervisor s "
                        + "on ps.SupervisorID = s.SupervisorID "
                        + "Where p.ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                List<SupervisorDTO> list = new ArrayList<>();
                while (rs.next()) {
                    String supervisorName = rs.getNString("SupervisorName");
                    String supervisorImage = rs.getString("SupervisorImage");
                    String supervisorInfo = rs.getNString("Information");
                    String supervisorPosition = rs.getString("Position");

                    SupervisorDTO supervisorDTO = new SupervisorDTO();
                    supervisorDTO.setSupervisorName(supervisorName);
                    supervisorDTO.setSupervisorImage(supervisorImage);
                    supervisorDTO.setInformation(supervisorInfo);
                    supervisorDTO.setPostion(supervisorPosition);
                    list.add(supervisorDTO);
                }

                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public SupervisorDTO getInforSup(String supervisorId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select SupervisorID, SupervisorName, SupervisorImage, Information, Position "
                        + "From Supervisor "
                        + "Where SupervisorID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, supervisorId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String supervisorId2 = rs.getString("SupervisorID");
                    String supervisorName = rs.getNString("SupervisorName");
                    String supervisorImage = rs.getString("SupervisorImage");
                    String information = rs.getNString("Information");
                    String position = rs.getString("Position");
                    SupervisorDTO dto = new SupervisorDTO();
                    dto.setSupervisorId(supervisorId);
                    dto.setSupervisorName(supervisorName);
                    dto.setSupervisorImage(supervisorImage);
                    dto.setInformation(information);
                    dto.setPostion(position);
                    return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<SupervisorDTO> getAllSupervisors() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Account, SupervisorID, SupervisorName, [Status] "
                        + "From Supervisor "
                        + "Order BY Account ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<SupervisorDTO> list = new ArrayList<>();

                AccountDAO accountDao = new AccountDAO();
                AccountDTO accountDto = new AccountDTO();
                while (rs.next()) {
                    String supervisorId = rs.getString("SupervisorID");
                    String supervisorName = rs.getString("SupervisorName");
                    boolean status = rs.getBoolean("Status");
                    accountDto = accountDao.getUserInforByEmail(rs.getString("Account"));

                    SupervisorDTO dto = new SupervisorDTO();
                    dto.setUser(accountDto);
                    dto.setSupervisorId(supervisorId);
                    dto.setSupervisorName(supervisorName);
                    dto.setStatus(status);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<SupervisorDTO> searchSupervisor(String keyword) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select SupervisorID, SupervisorName, [Status] "
                        + "From Supervisor "
                        + "where SupervisorName Like ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                rs = stm.executeQuery();
                List<SupervisorDTO> list = new ArrayList<>();
                while (rs.next()) {
                    String supervisorId = rs.getString("SupervisorID");
                    String supervisorName = rs.getNString("SupervisorName");
                    boolean status = rs.getBoolean("Status");
                    SupervisorDTO dto = new SupervisorDTO();
                    dto.setSupervisorId(supervisorId);
                    dto.setSupervisorName(supervisorName);
                    dto.setStatus(status);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<SupervisorDTO> searchInFilter(String keyword) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select su.SupervisorID, su.SupervisorName, p.ProjectId, p.ProjectName, p.ProjectAva "
                        + "from Supervisor su inner join Project_Supervisor ps "
                        + "on su.SupervisorID = ps.SupervisorID "
                        + "inner join Project p "
                        + "on p.ProjectId = ps.ProjectId "
                        + "Where su.SupervisorName LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+keyword+"%");
                rs = stm.executeQuery();
                List<SupervisorDTO> list = new ArrayList<>();
                ProjectDAO projectDao = new ProjectDAO();
                ProjectDTO projectDto = new ProjectDTO();
                while(rs.next()){
                    String supervisorId = rs.getString("SupervisorID");
                    String supervisorName = rs.getNString("SupervisorName");
                    String projectId = rs.getString("ProjectId");
                    projectDto = projectDao.getSingleProject(projectId);
                    SupervisorDTO dto = new SupervisorDTO();
                    dto.setSupervisorId(supervisorId);
                    dto.setSupervisorName(supervisorName);
                    dto.setProject(projectDto);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SupervisorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
