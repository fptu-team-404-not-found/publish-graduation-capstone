package team404.upcomingproject;

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

public class UpcomingProjectDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<UpcomingProjectDTO> getUpcomingProjectList() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From UpcomingProject ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<UpcomingProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    UpcomingProjectDTO dto = new UpcomingProjectDTO(rs.getString("Id"), rs.getString("ProjectName"),
                            rs.getString("Location"), rs.getString("Date"), rs.getString("Description"), rs.getString("Image"));
                    list.add(dto);
                }
                return list;
            }
        } catch (Exception ex) {
            Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public UpcomingProjectDTO checkUpcoming(String upcomingProjectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From UpcomingProject "
                        + "Where Id = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, upcomingProjectId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return new UpcomingProjectDTO(rs.getString("Id"), rs.getNString("ProjectName"), rs.getNString("Location"), rs.getNString("Date"), rs.getNString("Description"), rs.getString("Image"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void insertUpcomingProject(UpcomingProjectDTO upcomingProjectDTO) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into UpcomingProject(Id, ProjectName, [Location], [Date], [Description], [Image]) "
                        + "Values(?, ?, ?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, upcomingProjectDTO.getUpcomingProjectId());
                stm.setNString(2, upcomingProjectDTO.getProjectName());
                stm.setNString(3, upcomingProjectDTO.getLocation());
                stm.setNString(4, upcomingProjectDTO.getDate());
                stm.setNString(5, upcomingProjectDTO.getDescription());
                stm.setNString(6, upcomingProjectDTO.getImage());
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteUpcomingInAdmin(String id) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Delete From UpcomingProject "
                        + "Where Id = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
