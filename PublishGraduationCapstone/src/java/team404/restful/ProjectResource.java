package team404.restful;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;
import team404.team.TeamDAO;
import team404.team.TeamDTO;
import team404.upcomingproject.UpcomingProjectDAO;
import team404.upcomingproject.UpcomingProjectDTO;

@Path("project")

public class ProjectResource {

    @Context
    private UriInfo context;

    public ProjectResource() {
    }

    @Path("/getUpcomingProjects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UpcomingProjectDTO> getUpcomingProjects() {
        UpcomingProjectDAO dao = new UpcomingProjectDAO();
        List<UpcomingProjectDTO> list = dao.getUpcomingProjectList();
        return list;
    }

    @Path("/getHighlightProjects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighlightProjects() {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.getHighlightProjectList();
        JSONArray jsArr = new JSONArray();
        TeamDAO teamDao = new TeamDAO();
        for (ProjectDTO project : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", project.getProjectId());
            jsObj.put("projectName", project.getProjectName());
            jsObj.put("introductionContent", project.getIntroductionContent());
            jsObj.put("projectAva", project.getProjectAva());
            TeamDTO team = teamDao.getTeam(project.getProjectId());
            jsObj.put("teamName", team.getTeamName());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("getHighlightProjects", jsArr);
        String result = jsObj.toJSONString();
        return result;
    }

    //-- TIENHUYNHTN --//
    @Path("/showOtherProjects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showOtherProjects() {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.showOtherProjects();
        JSONArray jsArr = new JSONArray();

        for (ProjectDTO projectDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", projectDTO.getProjectId());
            jsObj.put("projectName", projectDTO.getProjectName());
            jsObj.put("projectAva", projectDTO.getProjectAva());

            jsArr.add(jsObj);
        }

        JSONObject jsObj = new JSONObject();
        jsObj.put("otherProject", jsArr);

        String result = jsObj.toJSONString();
        return result;
    }

    @Path("/searchProject")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String searchProject(
            @QueryParam("keyword") String keyword) {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.getSearchProjectList(keyword);
        JSONArray jsArr = new JSONArray();
        for (ProjectDTO projectDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", projectDTO.getProjectId());
            jsObj.put("projectName", projectDTO.getProjectName());
            jsObj.put("projectAva", projectDTO.getProjectAva());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("searchProject", jsArr);
        String result = jsObj.toJSONString();
        return result;
    }

    //-- TIENHUYNHTN --//
    @Path("/filterSearchSemesterNumberOfResults")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String filterSearchSemesterNumberOfResults(
            @QueryParam("semester") String semester) {
        ProjectDAO dao = new ProjectDAO();
        return String.valueOf(dao.filterSearchSemesterNumberOfResults(semester));
    }

    //-- TIENHUYNHTN --//
    @Path("/filterSearchSemesterGetProjects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String filterSearchSemesterGetProjects(
            @QueryParam("semester") String semester) {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.filterSearchSemesterGetProjects(semester);
        JSONArray jsArr = new JSONArray();

        for (ProjectDTO projectDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", projectDTO.getProjectId());
            jsObj.put("projectName", projectDTO.getProjectName());
            jsObj.put("projectAva", projectDTO.getProjectAva());

            jsArr.add(jsObj);
        }

        JSONObject jsObj = new JSONObject();
        jsObj.put("filterSearchSemesterProject", jsArr);

        String result = jsObj.toJSONString();
        return result;
    }

    @Path("/filterProjectsBySemester")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String filterProjectsBySemester(
            @QueryParam("semester") String semester) {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.getFilterSemesterList(semester);
        JSONArray jsArr = new JSONArray();
        for (ProjectDTO projectDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", projectDTO.getProjectId());
            jsObj.put("projectName", projectDTO.getProjectName());
            jsObj.put("projectAva", projectDTO.getProjectAva());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("filterProjectsBySemester", jsArr);
        String result = jsObj.toJSONString();
        return result;

    }
}
