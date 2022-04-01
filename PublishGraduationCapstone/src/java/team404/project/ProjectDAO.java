package team404.project;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.projectimage.ProjectImageDAO;
import team404.projectimage.ProjectImageDTO;
import team404.semester.SemesterDAO;
import team404.semester.SemesterDTO;
import team404.sharepost.SharePostDAO;
import team404.sharepost.SharePostDTO;
import team404.supervisor.SupervisorDAO;
import team404.supervisor.SupervisorDTO;
import team404.teammember.TeamMemberDAO;
import team404.teammember.TeamMemberDTO;
import team404.utils.DBHelpers;

public class ProjectDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    private List<ProjectDTO> searchProjectList;

    public List<ProjectDTO> getHighlightProjectList() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select ProjectId, ProjectName, IntroductionContent, ProjectAva "
                        + "From Project "
                        + "Order by ViewNumber "
                        + "Desc ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(rs.getString("ProjectId"));
                    dto.setProjectName(rs.getNString("ProjectName"));
                    dto.setIntroductionContent(rs.getString("IntroductionContent"));
                    dto.setProjectAva(rs.getString("ProjectAva"));
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

    public void updateView(String projectId)
            throws SQLException, NamingException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Project "
                        + "Set ViewNumber = ViewNumber + 1 "
                        + "Where ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                stm.executeUpdate();
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
    }

    public int showView(String projectId)
            throws SQLException, NamingException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select ViewNumber "
                        + "From Project "
                        + "where ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int view = rs.getInt("ViewNumber");
                    return view;
                }
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
        return -1;
    }

    public List<ProjectDTO> getSearchProjectList(String keyword) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "EXECUTE SearchHome "
                        + "@SearchValue = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, keyword);
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                SemesterDAO semesterDao = new SemesterDAO();
                SemesterDTO semesterDto = new SemesterDTO();

                while (rs.next()) {

//                    dto.setProjectId(rs.getString("ProjectId"));
//                    dto.setProjectName(rs.getNString("ProjectName"));
//                    dto.setProjectAva(rs.getString("ProjectAva"));
//                    dto.getSemester().setSemesterId(rs.getInt("SemesterId"));
//                    dto.setSemester(rs.getString("Semester"));
                    semesterDto = semesterDao.getSemesterName(rs.getInt("SemesterId"));
                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(rs.getString("ProjectId"));
                    dto.setProjectName(rs.getNString("ProjectName"));
                    dto.setProjectAva(rs.getString("ProjectAva"));
                    dto.setSemester(semesterDto);
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

    public List<ProjectDTO> getFilterSemesterList(String semester) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select ProjectId, ProjectName, ProjectAva "
                        + "From Project p inner join Semester s "
                        + "on p.SemesterId = s.SemesterId "
                        + "Where s.SemesterName = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, semester);
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    String projectAva = rs.getString("ProjectAva");

                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(projectId);
                    dto.setProjectName(projectName);
                    dto.setProjectAva(projectAva);
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

    public ProjectDTO getSingleProject(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select ProjectId, ProjectName, ProjectAva, IntroductionContent "
                        + "From Project "
                        + "Where ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String projectId2 = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    String projectAva = rs.getString("ProjectAva");
                    String introductionContent = rs.getNString("IntroductionContent");
                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(projectId);
                    dto.setProjectName(projectName);
                    dto.setProjectAva(projectAva);
                    dto.setIntroductionContent(introductionContent);
                    return dto;
                }
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

    //-- TIENHUYNHTN --//
    public List<ProjectDTO> showOtherProjects() {
        try {
            con = DBHelpers.makeConnection();
            String sql = "Select ProjectId, ProjectName, ProjectAva, IntroductionContent "
                    + "From Project ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            List<ProjectDTO> list = new ArrayList<>();

            while (rs.next()) {
                String projectId = rs.getString("ProjectId");
                String projectName = rs.getNString("ProjectName");
                String projectAva = rs.getString("ProjectAva");
                String introductionContent = rs.getNString("IntroductionContent");

                ProjectDTO dto = new ProjectDTO();
                dto.setProjectId(projectId);
                dto.setProjectName(projectName);
                dto.setProjectAva(projectAva);
                dto.setIntroductionContent(introductionContent);
                list.add(dto);
            }

            return list;
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

    //-- TIENHUYNHTN --//
    public ProjectDTO getProjectDetailsInPojectDetail(String projectId) { // Dat ms chỉnh một chút phần truyèn tham số constructor
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select ProjectId, ProjectName, VideoUrl, IntroductionContent, Details, Recap "
                        + "From Project "
                        + "Where ProjectId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("ProjectId");
                    String projectName = rs.getString("ProjectName");
                    String videoUrl = rs.getString("VideoUrl");
                    String intro = rs.getNString("IntroductionContent");
                    String details = rs.getNString("Details");
                    String recap = rs.getNString("Recap");

                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(id);
                    dto.setProjectName(projectName);
                    dto.setVideoUrl(videoUrl);
                    dto.setIntroductionContent(intro);
                    dto.setDetails(details);
                    dto.setRecap(recap);

                    return dto;
                }
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

    //-- TIENHUYNHTN --//
    public String showProjectDetails(String projectId) {
        ProjectDAO projectDAO = new ProjectDAO();
        TeamMemberDAO teamMemberDAO = new TeamMemberDAO();
        SupervisorDAO supervisorDAO = new SupervisorDAO();
        ProjectImageDAO projectImageDAO = new ProjectImageDAO();

        ProjectDTO projectDTO = projectDAO.getProjectDetailsInPojectDetail(projectId);
        List<TeamMemberDTO> listTeamMemberDTO = teamMemberDAO.getTeamMembersInPojectDetail(projectId);
        List<SupervisorDTO> listSupervisorDTO = supervisorDAO.getSupervisorsInPojectDetail(projectId);
        List<String> listUrlImages = projectImageDAO.getProjectImagesInPojectDetail(projectId);

        JSONArray jsArray = new JSONArray();
        JSONObject jsObject = new JSONObject();
        jsObject.put("projectId", projectDTO.getProjectId());
        jsObject.put("projectName", projectDTO.getProjectName());
        jsObject.put("videoUrl", projectDTO.getVideoUrl());
        jsObject.put("introductionContent", projectDTO.getIntroductionContent());
        jsObject.put("details", projectDTO.getDetails());
        jsObject.put("recap", projectDTO.getRecap());

        JSONArray jsArrTeamMem = new JSONArray();
        for (TeamMemberDTO teamMemberDTO : listTeamMemberDTO) {
            JSONObject jsObjectMember = new JSONObject();
            jsObjectMember.put("memberId", teamMemberDTO.getMemberId());
            jsObjectMember.put("memberName", teamMemberDTO.getMemberName());
            jsObjectMember.put("memberAva", teamMemberDTO.getMemberAvatar());
            jsObjectMember.put("memberEmail", teamMemberDTO.getUser().getEmail());
            jsObjectMember.put("memberPhone", teamMemberDTO.getPhone());

            jsArrTeamMem.add(jsObjectMember);
        }
        jsObject.put("listMember", jsArrTeamMem);

        JSONArray jsArrSupervisor = new JSONArray();
        for (SupervisorDTO supervisorDTO : listSupervisorDTO) {
            JSONObject jsObjectSup = new JSONObject();
            jsObjectSup.put("supervisorName", supervisorDTO.getSupervisorName());
            jsObjectSup.put("supervisorImage", supervisorDTO.getSupervisorImage());
            jsObjectSup.put("supervisorInformation", supervisorDTO.getInformation());
            jsObjectSup.put("supervisorPosition", supervisorDTO.getPostion());

            jsArrSupervisor.add(jsObjectSup);
        }
        jsObject.put("listSupervisor", jsArrSupervisor);

        JSONArray jsArrImage = new JSONArray();
        for (String imageUrl : listUrlImages) {
            JSONObject jsObjectImg = new JSONObject();
            jsObjectImg.put("imageUrl", imageUrl);

            jsArrImage.add(jsObjectImg);
        }
        jsObject.put("listUrlImage", jsArrImage);

        return jsObject.toJSONString();
    }

    public List<ProjectDTO> getAllPostWithApproving() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select p.ProjectId, p.ProjectName, p.CreateDate, p.AuthorName "
                        + "From Project p inner join States s "
                        + "on p.StateId = s.StateId "
                        + "Where s.StateName = 'Approving' ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    Date createDate = rs.getDate("CreateDate");
                    String authorName = rs.getNString("AuthorName");
                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(projectId);
                    dto.setProjectName(projectName);
                    dto.setCreateDate(createDate);
                    dto.setAuthorName(authorName);
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

    public List<ProjectDTO> getAllPostWithApproved() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select p.ProjectId, p.ProjectName, p.CreateDate, p.AuthorName "
                        + "From Project p inner join States s "
                        + "on p.StateId = s.StateId "
                        + "Where s.StateName = 'Approved' ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    Date createDate = rs.getDate("CreateDate");
                    String authorName = rs.getNString("AuthorName");
                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(projectId);
                    dto.setProjectName(projectName);
                    dto.setCreateDate(createDate);
                    dto.setAuthorName(authorName);
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

    public List<ProjectDTO> searchInFilter(String keyword) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select p.ProjectId, p.ProjectName, p.ProjectAva "
                        + "From Project p "
                        + "Where p.ProjectName LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                rs = stm.executeQuery();
                List<ProjectDTO> list = new ArrayList<>();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    String projectAva = rs.getString("ProjectAva");
                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(projectId);
                    dto.setProjectName(projectName);
                    dto.setProjectAva(projectAva);
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

    public List<ProjectDTO> getProjectFromTeamByAccount(String email) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select p.ProjectId, p.ProjectName, p.ProjectAva "
                        + "From TeamMember tm inner join Project p "
                        + "on tm.ProjectId = p.ProjectId "
                        + "Where tm.Account = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                List<ProjectDTO> list = new ArrayList<>();
                rs = stm.executeQuery();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    String projectAva = rs.getString("ProjectAva");
                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(projectId);
                    dto.setProjectName(projectName);
                    dto.setProjectAva(projectAva);
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

    public List<ProjectDTO> getProjectFromSupervisorByAccount(String email) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select p.ProjectId, p.ProjectName, p.ProjectAva "
                        + "From Project p inner join Project_Supervisor ps "
                        + "on p.ProjectId = ps.ProjectId "
                        + "inner join Supervisor s "
                        + "on ps.SupervisorID = s.SupervisorID "
                        + "Where s.Account = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                List<ProjectDTO> list = new ArrayList<>();
                rs = stm.executeQuery();
                while (rs.next()) {
                    String projectId = rs.getString("ProjectId");
                    String projectName = rs.getNString("ProjectName");
                    String projectAva = rs.getString("ProjectAva");
                    ProjectDTO dto = new ProjectDTO();
                    dto.setProjectId(projectId);
                    dto.setProjectName(projectName);
                    dto.setProjectAva(projectAva);
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

    public void updateState(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Project "
                        + "Set StateId = 2 "
                        + "Where ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                stm.executeUpdate();
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
    }

    //-- TIENHUYNHTN --//
    public boolean insertProject(ProjectDTO project) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                if ("".equals(project.getProjectAva())) {
                    project.setProjectAva("https://play-lh.googleusercontent.com/BFYTO8vhN2ZveSWA7XGoQVwei9cCvpi2je5eyDI2a1WoKxTjJJw5Sv8ULoQEGqAYo0g");
                }
                String sql = "INSERT INTO Project(ProjectId, ProjectName, ProjectAva, VideoUrl, IntroductionContent, Details, Recap, AuthorName, StateId, SemesterId) "
                        + "Values(?,?,?,?,?,?,?,?,1,?) ";
                stm = con.prepareStatement(sql);
                stm.setNString(1, project.getProjectId());
                stm.setNString(2, project.getProjectName());
                stm.setString(3, project.getProjectAva());
                stm.setString(4, project.getVideoUrl());
                stm.setNString(5, project.getIntroductionContent());
                stm.setNString(6, project.getDetails());
                stm.setNString(7, project.getRecap());
                stm.setNString(8, project.getAuthorName());
                stm.setInt(9, project.getSemester().getSemesterId());
                int affectedRow = stm.executeUpdate();
                if (affectedRow > 0) {
                    con.setAutoCommit(false);
                    String sqlImage = "INSERT INTO ProjectImage(ImageUrl, ProjectId) "
                            + "Values(?,?) ";
                    stm = con.prepareStatement(sqlImage);
                    int affectedRows = 0;
                    List<ProjectImageDTO> list = project.getListImages();
                    for (ProjectImageDTO projectImageDTO : list) {
                        stm.setNString(1, projectImageDTO.getImageUrl());
                        stm.setNString(2, project.getProjectId());
                        affectedRows += stm.executeUpdate();
                    }
                    con.commit();
                    if (affectedRows == list.size()) {
                        con.setAutoCommit(false);
                        String sqlMember = "Update TeamMember "
                                + "Set ProjectId = ? "
                                + "Where StudentId = ? ";
                        stm = con.prepareStatement(sqlMember);
                        
                        List<TeamMemberDTO> listTeam = project.getListMembers();
                        for (TeamMemberDTO teamMemberDTO : listTeam) {
                            stm.setString(1, project.getProjectId());
                            stm.setString(2, teamMemberDTO.getMemberId());
                            affectedRows += stm.executeUpdate();
                        }
                        con.commit();
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
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
        return false;
    }
}
