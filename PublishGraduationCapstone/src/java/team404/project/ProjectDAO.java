package team404.project;

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

public class ProjectDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    private List<ProjectDTO> searchProjectList;

    public List<ProjectDTO> getHighlightProjectList() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select ProjectId, ProjectName, IntroductionContent, ProjectAva "
                        + "From Project "
                        + "Order by ViewNumber "
                        + "Desc ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    ProjectDTO dto = new ProjectDTO(rs.getString("ProjectId"), rs.getString("ProjectName"),
                            rs.getString("IntroductionContent"), rs.getString("ProjectAva"));
                    list.add(dto);
                }
                return list;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void updateView(String projectId)
            throws SQLException, NamingException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Project "
                        + "Set ViewNumber = ViewNumber + 1 "
                        + "Where ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public int showView(String projectId)
            throws SQLException, NamingException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select ViewNumber "
                        + "From Project "
                        + "where ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int view = rs.getInt("ViewNumber");
                    return view;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public List<ProjectDTO> getSearchProjectList(String keyword) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "EXECUTE SearchHome "
                        + "@SearchValue = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, keyword);
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    String projectAva = rs.getString("ProjectAva");

                    ProjectDTO dto = new ProjectDTO(projectId, projectName, projectAva);
                    list.add(dto);
                }
                return list;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public List<ProjectDTO> getFilterSemesterList(String semester) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select ProjectId, ProjectName, ProjectAva "
                        + "From Project "
                        + "Where Semester = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, semester);
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    String projectAva = rs.getString("ProjectAva");

                    ProjectDTO dto = new ProjectDTO(projectId, projectName, projectAva);
                    list.add(dto);
                }
                return list;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //-- TIENHUYNHTN --//
    public List<ProjectDTO> showOtherProjects() {
        try {
            con = DBHelpers.makeConnection();
            String sql = "Select ProjectId, ProjectName, ProjectAva "
                    + "From Project ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            List<ProjectDTO> list = new ArrayList<>();

            while (rs.next()) {
                String projectId = rs.getString("ProjectId");
                String projectName = rs.getNString("ProjectName");
                String projectAva = rs.getString("ProjectAva");

                ProjectDTO dto = new ProjectDTO(projectId, projectName, projectAva);
                list.add(dto);
            }

            return list;
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

    //-- TIENHUYNHTN --//
    public int filterSearchSemesterNumberOfResults(String semester) {
        int numberResults = 0;

        try {
            con = DBHelpers.makeConnection();
            String sql = "Select COUNT(*) AS [Number Of Results] "
                    + "From Project "
                    + "Where Semester = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, semester);
            rs = stm.executeQuery();
            if (rs.next()) {
                numberResults = rs.getInt("Number Of Results");
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
        return numberResults;
    }

    //-- TIENHUYNHTN --//
    public List<ProjectDTO> filterSearchSemesterGetProjects(String semester) {
        try {
            con = DBHelpers.makeConnection();
            String sql = "Select ProjectId, ProjectName, ProjectAva "
                    + "From Project "
                    + "Where Semester = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, semester);
            rs = stm.executeQuery();

            List<ProjectDTO> list = new ArrayList<>();
            while (rs.next()) {
                String projectId = rs.getString("ProjectId");
                String projectName = rs.getNString("ProjectName");
                String projectAva = rs.getString("ProjectAva");

                ProjectDTO dto = new ProjectDTO(projectId, projectName, projectAva);
                list.add(dto);
            }

            return list;
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
}
