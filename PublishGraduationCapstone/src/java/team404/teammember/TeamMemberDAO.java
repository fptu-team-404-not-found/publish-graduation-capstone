package team404.teammember;

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
import team404.utils.DBHelpers;

public class TeamMemberDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<TeamMemberDTO> getTeamMembersInPojectDetail(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select MemberName, MemberAvatar, Email, Phone "
                        + "From TeamMember "
                        + "Where TeamID = (Select TeamID From Project Where ProjectId = ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                List<TeamMemberDTO> list = new ArrayList<>();
                while (rs.next()) {
                    String memberName = rs.getNString("MemberName");
                    String memberAvatar = rs.getString("MemberAvatar");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    
                    TeamMemberDTO dto = new TeamMemberDTO(memberName, memberAvatar, email, phone);
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
