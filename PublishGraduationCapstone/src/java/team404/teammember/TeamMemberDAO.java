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
import team404.account.AccountDTO;
import team404.utils.DBHelpers;

public class TeamMemberDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    //-- TIENHUYNHTN --//
    public List<TeamMemberDTO> getTeamMembersInPojectDetail(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select TeamMember.StudentId, TeamMember.MemberName, TeamMember.MemberAvatar, TeamMember.Phone, Account.Email "
                        + "From TeamMember JOIN Account "
                        + "On TeamMember.ProjectId = ? AND Account.Email = TeamMember.Account ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                List<TeamMemberDTO> list = new ArrayList<>();

                while (rs.next()) {
                    String studentId = rs.getString("StudentId");
                    String memberName = rs.getNString("MemberName");
                    String memberAvatar = rs.getString("MemberAvatar");
                    String phone = rs.getString("Phone");
                    String email = rs.getString("Email");

                    TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
                    AccountDTO user = new AccountDTO();

                    user.setEmail(email);

                    teamMemberDTO.setMemberId(studentId);
                    teamMemberDTO.setMemberName(memberName);
                    teamMemberDTO.setMemberAvatar(memberAvatar);
                    teamMemberDTO.setPhone(phone);
                    teamMemberDTO.setUser(user);

                    list.add(teamMemberDTO);
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
