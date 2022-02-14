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
    public String getHighlightProjects() {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.getProjectList();
        JSONArray jsArr = new JSONArray();
        for (ProjectDTO project : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", project.getProjectId());
            jsObj.put("projectName", project.getProjectName());
            jsObj.put("introductionContent", project.getIntroductionContent());
            jsObj.put("details", project.getDetails());
            jsObj.put("semester", project.getSemester());
            jsObj.put("productUrl", project.getProductUrl());
            jsObj.put("createDate", project.getCreateDate());
            jsObj.put("viewNumber", project.getViewNumber());
            jsObj.put("authorName", project.getAuthorName());
            jsObj.put("note", project.getNote());
            jsObj.put("teamId", project.getTeamId());
            jsObj.put("stateId", project.getStateId());
            jsArr.add(jsObj);
        }
        String result = jsArr.toJSONString();
        return result;
    }
    
    @Path("/getUpcomingProjects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUpcomingProjects(){
        UpcomingProjectDAO dao = new UpcomingProjectDAO();
        List<UpcomingProjectDTO> list = dao.getUpcomingProjectList();
        JSONArray jsArr = new JSONArray();
        for (UpcomingProjectDTO upcoming : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("upcomingProjectId", upcoming.getUpcomingProjectId());
            jsObj.put("projectName", upcoming.getProjectName());
            jsObj.put("location", upcoming.getLocation());
            jsObj.put("date", upcoming.getDate());
            jsObj.put("description", upcoming.getDescription());
            jsObj.put("image", upcoming.getImage());
            jsArr.add(jsObj);
        }
        String result = jsArr.toJSONString();
        return result;
    }

}
