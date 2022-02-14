/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.project;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import team404.team.TeamDTO;
import team404.utils.DBHelpers;

/**
 *
 * @author jike
 */
public class ProjectDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    private List<ProjectDTO> projectList;
    private List<ProjectDTO> searchProjectList;
    private List<ProjectDTO> filterSemesterList;

    public List<ProjectDTO> getProjectList() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select p.ProjectId, p.ProjectName, p.IntroductionContent, p.ProjectAva, t.TeamName "
                        + "From Project p inner join Team t "
                        + "On p.TeamID = t.TeamID "
                        + "Order by ViewNumber "
                        + "Desc ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    ProjectDTO dto = new ProjectDTO(rs.getString("ProjectId"), rs.getString("ProjectName"),
                            rs.getString("IntroductionContent"), rs.getString("ProjectAva"), rs.getNString("TeamName"));
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

    public List<ProjectDTO> getSearchProjectList() {
        return searchProjectList;
    }

    public void searchProject(String keyword)
            throws SQLException, NamingException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "EXECUTE SearchHome "
                        + "@SearchValue = ? ";
                stm = con.prepareStatement(sql);
                stm.setNString(1, keyword);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    String projectAva = rs.getString("ProjectAva");

                    ProjectDTO dto = new ProjectDTO(projectId, projectName, projectAva);
                    if (this.searchProjectList == null) {
                        this.searchProjectList = new ArrayList<>();
                    }
                    this.searchProjectList.add(dto);
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

        }
        return null;
    }

    
}
