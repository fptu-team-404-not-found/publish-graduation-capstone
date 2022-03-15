package team404.sharepost;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;
import team404.supervisor.SupervisorDAO;
import team404.supervisor.SupervisorDTO;
import team404.teammember.TeamMemberDAO;
import team404.teammember.TeamMemberDTO;
import team404.utils.DBHelpers;

public class SharePostDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    private List<SharePostDTO> sharePostList;

    public List<SharePostDTO> getSharePostList(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select PostId, Title, StudentId, SupervisorID "
                        + "From SharePost "
                        + "Where ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                List<SharePostDTO> list = new ArrayList<>();
                TeamMemberDAO teamMemberDao = new TeamMemberDAO();
                TeamMemberDTO teamMemberDto = new TeamMemberDTO();

                SupervisorDAO supervisorDao = new SupervisorDAO();
                SupervisorDTO supervisorDto = new SupervisorDTO();

                while (rs.next()) {
                    int postId = rs.getInt("PostId");
                    String title = rs.getNString("Title");
                    teamMemberDto = teamMemberDao.getInforMember(rs.getString("StudentId"));
                    supervisorDto = supervisorDao.getInforSup(rs.getString("SupervisorID"));

                    SharePostDTO dto = new SharePostDTO();
                    dto.setPostId(postId);
                    dto.setTitle(title);
                    dto.setStudent(teamMemberDto);
                    dto.setSupervisor(supervisorDto);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public SharePostDTO getSharePostDetail(int postId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select PostId, Title, Details, CreateDate, StudentId, SupervisorID, ProjectId "
                        + "From SharePost "
                        + "Where PostId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, postId);
                rs = stm.executeQuery();
                TeamMemberDAO teamMemberDao = new TeamMemberDAO();
                TeamMemberDTO teamMemberDto = new TeamMemberDTO();

                SupervisorDAO supervisorDao = new SupervisorDAO();
                SupervisorDTO supervisorDto = new SupervisorDTO();

                ProjectDAO projectDao = new ProjectDAO();
                ProjectDTO projectDto = new ProjectDTO();
                if (rs.next()) {
                    int postId2 = rs.getInt("PostId");
                    String title = rs.getNString("Title");
                    String details = rs.getNString("Details");
                    Date createDate = rs.getDate("CreateDate");
                    teamMemberDto = teamMemberDao.getInforMember(rs.getString("StudentId"));
                    supervisorDto = supervisorDao.getInforSup(rs.getString("SupervisorID"));
                    projectDto = projectDao.getSingleProject(rs.getString("ProjectId"));

                    SharePostDTO dto = new SharePostDTO();
                    dto.setPostId(postId2);
                    dto.setTitle(title);
                    dto.setDetails(details);
                    dto.setCreateDate(createDate);
                    dto.setStudent(teamMemberDto);
                    dto.setSupervisor(supervisorDto);
                    dto.setProject(projectDto);
                    return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<SharePostDTO> showSharePostWithApproving() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select sp.PostId, sp.Title, sp.CreateDate, sp.StudentId, sp.SupervisorID "
                        + "From SharePost sp inner join States s "
                        + "on sp.StateId = s.StateId "
                        + "Where s.StateName = 'Approving' ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<SharePostDTO> list = new ArrayList<>();
                TeamMemberDAO teamMemberDao = new TeamMemberDAO();
                TeamMemberDTO teamMemberDto = new TeamMemberDTO();

                SupervisorDAO supervisorDao = new SupervisorDAO();
                SupervisorDTO supervisorDto = new SupervisorDTO();
                while(rs.next()){
                    int postId = rs.getInt("PostId");
                    String title = rs.getString("Title");
                    Date createDate = rs.getDate("CreateDate");
                    teamMemberDto = teamMemberDao.getInforMember(rs.getString("StudentId"));
                    supervisorDto = supervisorDao.getInforSup(rs.getString("SupervisorID"));
                    
                    SharePostDTO dto = new SharePostDTO();
                    dto.setPostId(postId);
                    dto.setTitle(title);
                    dto.setCreateDate(createDate);
                    dto.setStudent(teamMemberDto);
                    dto.setSupervisor(supervisorDto);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public List<SharePostDTO> showSharePostWithApproved() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select sp.PostId, sp.Title, sp.CreateDate, sp.StudentId, sp.SupervisorID "
                        + "From SharePost sp inner join States s "
                        + "on sp.StateId = s.StateId "
                        + "Where s.StateName = 'Approved' ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<SharePostDTO> list = new ArrayList<>();
                TeamMemberDAO teamMemberDao = new TeamMemberDAO();
                TeamMemberDTO teamMemberDto = new TeamMemberDTO();

                SupervisorDAO supervisorDao = new SupervisorDAO();
                SupervisorDTO supervisorDto = new SupervisorDTO();
                while(rs.next()){
                    int postId = rs.getInt("PostId");
                    String title = rs.getString("Title");
                    Date createDate = rs.getDate("CreateDate");
                    teamMemberDto = teamMemberDao.getInforMember(rs.getString("StudentId"));
                    supervisorDto = supervisorDao.getInforSup(rs.getString("SupervisorID"));
                    
                    SharePostDTO dto = new SharePostDTO();
                    dto.setPostId(postId);
                    dto.setTitle(title);
                    dto.setCreateDate(createDate);
                    dto.setStudent(teamMemberDto);
                    dto.setSupervisor(supervisorDto);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SharePostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
