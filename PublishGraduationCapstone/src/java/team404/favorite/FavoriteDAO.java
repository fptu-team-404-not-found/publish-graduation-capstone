package team404.favorite;

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
import team404.project.ProjectDTO;
import team404.utils.DBHelpers;

public class FavoriteDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public boolean findBookmark(String projectId, String email) {

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From Favorite "
                        + "Where ProjectId = ? And Account = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                stm.setString(2, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public boolean bookmark(String projectId, String email) {

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Favorite(ProjectId, Account) "
                        + "Values (?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                stm.setString(2, email);
                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deleteBookmark(String projectId, String email) {

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Delete From Favorite "
                        + "Where projectId = ? And Account = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                stm.setString(2, email);
                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public List<ProjectDTO> getProjectFavorite(String email) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select p.ProjectId, p.ProjectName, p.ProjectAva, p.IntroductionContent "
                        + "From Account a inner join Favorite f "
                        + "on a.Email = f.Account "
                        + "inner join Project p "
                        + "on p.ProjectId = f.ProjectId "
                        + "Where a.Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                List<ProjectDTO> list = new ArrayList<>();
                rs = stm.executeQuery();
                while(rs.next()){
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    String projectAva = rs.getString("ProjectAva");
                    String intro = rs.getNString("IntroductionContent");
                    
                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(projectId);
                    dto.setProjectName(projectName);
                    dto.setProjectAva(projectAva);
                    dto.setIntroductionContent(intro);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
