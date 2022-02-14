/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.upcomingproject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import team404.utils.DBHelpers;

/**
 *
 * @author jike
 */
public class UpcomingProjectDAO implements Serializable{
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    private List<UpcomingProjectDTO> upcomingProjectList;

    public List<UpcomingProjectDTO> getUpcomingProjectList() {
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select * " 
                        +"From UpcomingProject ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<UpcomingProjectDTO> list = new ArrayList<>();
                while(rs.next()){
                    UpcomingProjectDTO dto = new UpcomingProjectDTO(rs.getInt("Id"), rs.getString("ProjectName"), rs.getString("Location"), rs.getString("Date"), rs.getString("Description"), rs.getString("Image"));
                    list.add(dto);
                }
                return list;
            }
        }catch(Exception ex){
        
        }
        return null;
    }
    
}
