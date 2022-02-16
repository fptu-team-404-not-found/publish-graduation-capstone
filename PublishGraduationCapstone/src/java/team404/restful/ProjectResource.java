/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.restful;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;
import team404.team.TeamDAO;
import team404.team.TeamDTO;
import team404.upcomingproject.UpcomingProjectDAO;
import team404.upcomingproject.UpcomingProjectDTO;

/**
 * REST Web Service
 *
 * @author tienhltse151104
 */
@Path("project")

public class ProjectResource {


    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProjectResource
     */
    public ProjectResource() {
    }

    @Path("/getHighlightProjects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighlightProjects() 
    throws SQLException, NamingException{
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
        String result = jsArr.toJSONString();
        return result;
    }
    
    @Path("/getUpcomingProjects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UpcomingProjectDTO> getUpcomingProjects(){
        UpcomingProjectDAO dao = new UpcomingProjectDAO();
        List<UpcomingProjectDTO> list = dao.getUpcomingProjectList();
        return list;
    }
    
    @Path("/searchProject")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectDTO> searchProject(
            @QueryParam("keyword") String keyword)
    throws SQLException, NamingException{
        ProjectDAO dao = new ProjectDAO();
        dao.searchProject(keyword);
        List<ProjectDTO> list = dao.getSearchProjectList();
        return list;
    }
    @Path("/filterProjectsBySemester")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectDTO> filterProjectsBySemester(
            @QueryParam("semester") String semester)
    throws SQLException, NamingException{
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.getFilterSemesterList(semester);
        return list;
    }
}
