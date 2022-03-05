package team404.upcomingproject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import team404.utils.DBHelpers;

public class UpcomingProjectDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<UpcomingProjectDTO> getUpcomingProjectList() { //Lỗi
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From UpcomingProject ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<UpcomingProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    UpcomingProjectDTO dto = new UpcomingProjectDTO(rs.getString("Id"), rs.getString("ProjectName"), rs.getString("Location"), rs.getString("Date"), rs.getString("Description"), rs.getString("Image"));
                    list.add(dto);
                }
                return list;
            }
        } catch (Exception ex) {
            Logger.getLogger(UpcomingProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
