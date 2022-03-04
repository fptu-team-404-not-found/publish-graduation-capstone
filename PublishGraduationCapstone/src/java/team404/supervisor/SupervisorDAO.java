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
import team404.user.AccountDAO;
import team404.user.AccountDTO;
import team404.utils.DBHelpers;

public class SupervisorDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<SupervisorDTO> getSupervisorsInPojectDetail(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select s.SupervisorName, s.SupervisorImage, s.Information, s.Position "
                        + "From Project p inner join Project_Supervisor ps "
                        + "on p.ProjectId = ps.ProjectId "
                        + "inner join Supervisor s "
                        + "on ps.SupervisorID = s.SupervisorID "
                        + "Where p.ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                List<SupervisorDTO> list = new ArrayList<>();
                AccountDAO userDao = new AccountDAO();
                AccountDTO userDto = new AccountDTO();
                while (rs.next()) {
                    String supervisorId = rs.getString("SupervisorID");
                    String supervisorName = rs.getNString("SupervisorName");
                    String supervisorImage = rs.getString("SupervisorImage");
                    String supervisorInfo = rs.getNString("Information");
                    String supervisorPosition = rs.getString("Position");
                    boolean supervisorStatus = rs.getBoolean("Status");
                    userDto = userDao.getUserInforByUserId(rs.getString("UserId"));

                    SupervisorDTO dto = new SupervisorDTO(supervisorId, supervisorName, supervisorImage, supervisorInfo, supervisorPosition, supervisorStatus, userDto);
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
