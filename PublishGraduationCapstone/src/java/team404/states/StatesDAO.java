package team404.states;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import team404.utils.DBHelpers;

public class StatesDAO {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
     public StatesDTO getInforState(int stateId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From States "
                        + "Where StateId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, stateId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int stateId2 = rs.getInt("StateId");
                    String stateName = rs.getString("StateName");
                    StatesDTO dto = new StatesDTO();
                    dto.setStateId(stateId2);
                    dto.setStateName(stateName);
                    return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(StatesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(StatesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
