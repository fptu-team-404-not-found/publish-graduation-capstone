package team404.semester;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import team404.utils.DBHelpers;

public class SemesterDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public SemesterDTO getSemesterName(int semesterId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select SemesterId, SemesterName "
                        + "From Semester "
                        + "Where SemesterId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, semesterId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int semesterId2 = rs.getInt("SemesterId");
                    String semesterName = rs.getString("SemesterName");
                    SemesterDTO dto = new SemesterDTO(semesterId, semesterName);
                    return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SemesterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SemesterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SemesterDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }
}
