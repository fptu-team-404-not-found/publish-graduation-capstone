package team404.restful;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.comment.CommentDAO;
import team404.comment.CommentDTO;
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;
import team404.upcomingproject.UpcomingProjectDAO;
import team404.upcomingproject.UpcomingProjectDTO;

@Path("project")

public class ProjectResource {

    @Context
    private UriInfo context;

    public ProjectResource() {
    }

    @Path("/getUpcomingProjects") //Lỗi
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUpcomingProjects() {
        UpcomingProjectDAO dao = new UpcomingProjectDAO();
        JSONArray jsArr = new JSONArray();
        List<UpcomingProjectDTO> list = dao.getUpcomingProjectList();
        for (UpcomingProjectDTO upcomingProjectDTO : list) {
           JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", upcomingProjectDTO.getUpcomingProjectId());
            jsObj.put("projectName", upcomingProjectDTO.getProjectName());
            jsObj.put("projectLocation", upcomingProjectDTO.getLocation());
            jsObj.put("projectDate", upcomingProjectDTO.getDate());
            jsObj.put("projectDescription", upcomingProjectDTO.getDescription());
//            jsObj.put("teamName", upcomingProjectDTO.getTeamName());
            jsObj.put("projectImage", upcomingProjectDTO.getImage());
            jsArr.add(jsObj); 
        }
        
        JSONObject jsObj = new JSONObject();
        jsObj.put("getUpcomingProjects", jsArr);
        
        String result = jsObj.toJSONString();
        return result;
    }

    @Path("/getHighlightProjects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighlightProjects() {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.getHighlightProjectList();
        JSONArray jsArr = new JSONArray();
        for (ProjectDTO project : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", project.getProjectId());
            jsObj.put("projectName", project.getProjectName());
            jsObj.put("introductionContent", project.getIntroductionContent());
            jsObj.put("projectAva", project.getProjectAva());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("getHighlightProjects", jsArr);
        String result = jsObj.toJSONString();
        return result;
    }
    
    //-- TIENHUYNHTN --// //OK
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
            jsObj.put("introductionContent", projectDTO.getIntroductionContent());

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
            jsObj.put("semester", projectDTO.getSemester().getSemesterName());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("searchProject", jsArr);
        String result = jsObj.toJSONString();
        return result;
    }
    
    //-- TIENHUYNHTN --// //Lỗi
    @Path("/showProjectDetails") 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showProjectDetails(
            @QueryParam("projectId") String projectId) {
        ProjectDAO dao = new ProjectDAO();
        return dao.showProjectDetails(projectId);
    }
    
    //-- TIENHUYNHTN --// //OK
    @Path("/getCommentsOfProject") 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCommentsOfProject(
            @QueryParam("projectId") String projectId) {
        CommentDAO dao = new CommentDAO();
        List<CommentDTO> list = dao.getCommentsOfProject(projectId);
        
        JSONArray jsArr = new JSONArray();
        for (CommentDTO commentDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("commentId", commentDTO.getCommentId());
            jsObj.put("commentDate", commentDTO.getCommentDate());
            jsObj.put("commentContent", commentDTO.getCommentContent());
            jsObj.put("userAva", commentDTO.getUser().getPicture());
            jsObj.put("userName", commentDTO.getUser().getName());
            
            jsArr.add(jsObj);
        }
        
        JSONObject jsObj = new JSONObject();
        jsObj.put("commentsOfProject", jsArr);
        return jsObj.toJSONString();
    }
}
