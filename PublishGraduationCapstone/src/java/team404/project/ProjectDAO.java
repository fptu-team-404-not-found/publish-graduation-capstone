package team404.project;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.projectimage.ProjectImageDAO;
import team404.semester.SemesterDAO;
import team404.semester.SemesterDTO;
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

    public List<ProjectDTO> getHighlightProjectList() { //Lỗi
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
                    ProjectDTO dto = new ProjectDTO(rs.getString("ProjectId"), rs.getString("ProjectName"),
                            rs.getString("IntroductionContent"), rs.getString("ProjectAva"));
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

    public List<ProjectDTO> getSearchProjectList(String keyword) { //Lỗi
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
                    ProjectDTO dto = new ProjectDTO(rs.getString("ProjectId"), rs.getNString("ProjectName"), rs.getString("ProjectAva"), semesterDto);

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
    public ProjectDTO getProjectDetailsInPojectDetail(String projectId) { //Lỗi
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select ProjectId, ProjectName, IntroductionContent, Details, Recap "
                        + "From Project "
                        + "Where ProjectId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("ProjectId");
                    String projectName = rs.getString("ProjectName");
                    String intro = rs.getNString("IntroductionContent");
                    String details = rs.getNString("Details");
                    String recap = rs.getNString("Recap");

                    ProjectDTO dto = new ProjectDTO(id, projectName, intro, details, recap);

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
    public String showProjectDetails(String projectId) { //Lỗi
        ProjectDAO projectDAO = new ProjectDAO();
        ProjectImageDAO projectImageDAO = new ProjectImageDAO();
        TeamMemberDAO teamMemberDAO = new TeamMemberDAO();
        SupervisorDAO supervisorDAO = new SupervisorDAO();

        ProjectDTO projectDTO = projectDAO.getProjectDetailsInPojectDetail(projectId);
        List<String> listUrlImages = projectImageDAO.getProjectImagesInPojectDetail(projectId);
        List<TeamMemberDTO> listTeamMemDTO = teamMemberDAO.getTeamMembersInPojectDetail(projectId);
        List<SupervisorDTO> listSupervisorDTO = supervisorDAO.getSupervisorsInPojectDetail(projectId);

        JSONArray jsArray = new JSONArray();
        JSONObject jsObject = new JSONObject();
        jsObject.put("projectId", projectDTO.getProjectId());
        jsObject.put("projectName", projectDTO.getProjectName());
        jsObject.put("projectIntro", projectDTO.getIntroductionContent());

        JSONArray jsArrTeamMem = new JSONArray();
        for (TeamMemberDTO teamMemberDTO : listTeamMemDTO) {
            JSONObject jsObjectMem = new JSONObject();
            jsObjectMem.put("memberName", teamMemberDTO.getMemberName());
            jsObjectMem.put("memberAva", teamMemberDTO.getMemberAvatar());
            jsObjectMem.put("memberEmail", teamMemberDTO.getUser().getEmail());
            jsObjectMem.put("memberPhone", teamMemberDTO.getPhone());

            jsArrTeamMem.add(jsObjectMem);
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

        jsObject.put("projectDetails", projectDTO.getDetails());
        jsObject.put("projectRecap", projectDTO.getRecap());

        JSONArray jsArrImage = new JSONArray();
        for (String imageUrl : listUrlImages) {
            JSONObject jsObjectImg = new JSONObject();
            jsObjectImg.put("imageUrl", imageUrl);

            jsArrImage.add(jsObjectImg);
        }
        jsObject.put("listUrlImage", jsArrImage);

        return jsObject.toJSONString();
    }
}
