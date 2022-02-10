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
import team404.utils.DBHelpers;

/**
 *
 * @author jike
 */
public class ProjectDAO implements Serializable{
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    private List<ProjectDTO> projectList;

    public List<ProjectDTO> getProjectList() {
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select * "
                        + "From Project "
                        + "Order by ViewNumber "
                        + "Desc ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                while(rs.next()){
                    ProjectDTO dto = new ProjectDTO(rs.getString("ProjectId"), rs.getString("ProjectName"), 
                            rs.getString("IntroductionContent"), rs.getString("Details"), rs.getString("Semester"), 
                            rs.getString("ProductURL"), rs.getString("CreateDate"), rs.getInt("ViewNumber"), rs.getString("AuthorName"), 
                            rs.getString("Note"), rs.getInt("TeamID"), rs.getInt("StateId"));
                    list.add(dto);
                }
                return list;
            }
        }catch(Exception e){
        
        }
        return null;
    }
    public void updateView(String projectId)
    throws SQLException, NamingException{
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Update Project "
                        + "Set ViewNumber = ViewNumber + 1 "
                        + "Where ProjectId = ? ";
                stm.setString(1, projectId);
                stm = con.prepareStatement(sql);
                stm.executeUpdate();
            }
        } finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
    public int showView(String projectId)
    throws SQLException, NamingException{
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select ViewNumber "
                        + "From Project "
                        + "where ProjectId = ? ";
                stm.setString(1, projectId);
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if(rs.next()){
                    int view = rs.getInt("ViewNumber");
                    return view;
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return -1;
    }
}
