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
import team404.project.ProjectDAO;
import team404.teammember.TeamMemberDTO;
import team404.utils.DBHelpers;

public class SupervisorDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<SupervisorDTO> getSupervisorsInPojectDetail(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Supervisor.SupervisorName, Supervisor.SupervisorImage, Supervisor.Information, Supervisor.Position "
                        + "From Team_Supervisor JOIN Supervisor "
                        + "On Team_Supervisor.SupervisorID = Supervisor.SupervisorID "
                        + "Where TeamID = (Select TeamID From Project Where ProjectId = ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                List<SupervisorDTO> list = new ArrayList<>();
                while (rs.next()) {
                    String supervisorName = rs.getNString("SupervisorName");
                    String supervisorImage = rs.getString("SupervisorImage");
                    String supervisorInfo = rs.getNString("Information");
                    String supervisorPosition = rs.getString("Position");

                    SupervisorDTO dto = new SupervisorDTO(supervisorName, supervisorImage, supervisorInfo, supervisorPosition);
                    list.add(dto);
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
}
