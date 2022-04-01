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
import team404.project.ProjectDTO;
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

    public TeamMemberDTO getInforMember(String studentId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From TeamMember "
                        + "Where StudentId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, studentId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String studentId2 = rs.getString("StudentId");
                    String memberName = rs.getNString("MemberName");
                    String memberAvatar = rs.getString("MemberAvatar");
                    String phone = rs.getString("Phone");
                    String backupEmail = rs.getNString("BackupEmail");
                    String email = rs.getString("Account");

                    TeamMemberDTO dto = new TeamMemberDTO();
                    AccountDTO accountDTO = new AccountDTO();
                    accountDTO.setEmail(email);
                    
                    dto.setMemberId(studentId2);
                    dto.setMemberName(memberName);
                    dto.setMemberAvatar(memberAvatar);
                    dto.setPhone(phone);
                    dto.setBackupEmail(backupEmail);
                    dto.setUser(accountDTO);
                    return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public TeamMemberDTO getInforMemberWithEmail(String email) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From TeamMember "
                        + "Where Account = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String studentId2 = rs.getString("StudentId");
                    String memberName = rs.getNString("MemberName");
                    String memberAvatar = rs.getString("MemberAvatar");
                    String phone = rs.getString("Phone");
                    String backupEmail = rs.getNString("BackupEmail");

                    TeamMemberDTO dto = new TeamMemberDTO();
                    dto.setMemberId(studentId2);
                    dto.setMemberName(memberName);
                    dto.setMemberAvatar(memberAvatar);
                    dto.setPhone(phone);
                    dto.setBackupEmail(backupEmail);
                    return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public void insertMember(String email, TeamMemberDTO teamMemberDTO){
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Insert Into TeamMember(StudentId, MemberName, MemberAvatar, Phone, Account) "
                        + "Values(?, ?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, teamMemberDTO.getMemberId());
                stm.setNString(2, teamMemberDTO.getMemberName());
                stm.setString(3, teamMemberDTO.getMemberAvatar());
                stm.setString(4, teamMemberDTO.getPhone());
                stm.setString(5, email);
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public List<TeamMemberDTO> searchInFilter(String keyword) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select tm.StudentId, tm.MemberName, p.ProjectId, p.ProjectName, p.ProjectAva "
                        + "From TeamMember tm inner join Project p "
                        + "on tm.ProjectId = p.ProjectId "
                        + "Where tm.MemberName LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+keyword+"%");
                rs = stm.executeQuery();
                List<TeamMemberDTO> list = new ArrayList<>();
                ProjectDAO projectDao = new ProjectDAO();
                ProjectDTO projectDto = new ProjectDTO();
                while(rs.next()){
                    String studentId = rs.getString("StudentId");
                    String memberName = rs.getNString("MemberName");
                    String projectId = rs.getString("ProjectId");
                    projectDto = projectDao.getSingleProject(projectId);
                    TeamMemberDTO dto = new TeamMemberDTO();
                    dto.setMemberId(studentId);
                    dto.setMemberName(memberName);
                    dto.setProject(projectDto);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(TeamMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    //-- TIENHUYNHTN --//
    public List<String> getStudentId() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select StudentId "
                        + "From TeamMember "
                        + "Where ProjectId IS NULL ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<String> list = new ArrayList<>();

                while (rs.next()) {
                    String studentId = rs.getString("StudentId");

                    list.add(studentId);
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
