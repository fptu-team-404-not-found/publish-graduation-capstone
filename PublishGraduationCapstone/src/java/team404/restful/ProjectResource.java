/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.restful;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
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

/**
 * REST Web Service
 *
 * @author tienhltse151104
 */
@Path("project")
public class ProjectResource {
     @Resource
    private WebServiceContext wsContext;
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
    public String getHighlightProjects(){
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
//    @Path("/getViewProjects")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public int getViewProjects(
//            @QueryParam("ProjectId") String ProjectId)
//    throws SQLException, NamingException{
//        MessageContext mc = wsContext.getMessageContext();
//        HttpSession session = ((HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();
//        ProjectDAO dao = new ProjectDAO();
//        if(session.isNew()){
//            dao.updateView(ProjectId);
//        }
//        int view = dao.showView(ProjectId);
//        return view;
//    }
}
