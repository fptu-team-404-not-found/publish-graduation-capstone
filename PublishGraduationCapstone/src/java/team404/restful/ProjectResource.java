package team404.restful;

import com.sun.jersey.multipart.FormDataParam;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import team404.favorite.FavoriteDAO;
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

    @Path("/getUpcomingProjects")
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
            jsObj.put("projectImage", upcomingProjectDTO.getImage());
            jsArr.add(jsObj);
        }

        return jsArr.toJSONString();
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

    //-- TIENHUYNHTN --// //OK
    @Path("/showProjectDetails")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showProjectDetails(
            @QueryParam("projectId") String projectId) {
        ProjectDAO dao = new ProjectDAO();
        return dao.showProjectDetails(projectId);
    }

    //-- TIENHUYNHTN --// //OK
    @Path("/showCommentsOfProject")
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
            jsObj.put("commentDate", commentDTO.getCommentDate().toString());
            jsObj.put("commentContent", commentDTO.getCommentContent());
            jsObj.put("userAva", commentDTO.getUser().getPicture());
            jsObj.put("userName", commentDTO.getUser().getName());

            jsArr.add(jsObj);
        }

        JSONObject jsObj = new JSONObject();
        jsObj.put("commentsOfProject", jsArr);
        return jsObj.toJSONString();
    }

    //-- TIENHUYNHTN --// //OK
    @Path("/bookmark")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String bookmark(
            @QueryParam("projectId") String projectId, 
            @QueryParam("email") String email) {
        FavoriteDAO favoriteDAO = new FavoriteDAO();
        boolean isExistedBookmark = favoriteDAO.findBookmark(projectId, email);
        boolean status;
        String message;
        if (isExistedBookmark) {
            status = favoriteDAO.deleteBookmark(projectId, email);
            message = "Removed form favorite";
        } else {
            status = favoriteDAO.bookmark(projectId, email);
            message = "Added to favorite";
        }
        return message;
    }

    //-- TIENHUYNHTN --// //OK
    @Path("/commentOnProject")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String commentOnProject(
            @FormDataParam("projectId") String projectId,
            @FormDataParam("email") String email,
            @FormDataParam("commentContent") String commentContent) {
        CommentDAO commentDAO = new CommentDAO();
        String result = commentDAO.commentOnProject(projectId, email, commentContent);

        return result;
    }
    /*
    @Path("/showFilterInSearch")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showFilterInSearch(
            @QueryParam("keyword") String keyword) {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> listProject = dao.searchInFilter(keyword);
        JSONArray jsArr = new JSONArray();
        JSONObject jsObjM = new JSONObject();
        for (ProjectDTO projectDTO : listProject) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", projectDTO.getProjectId());
            jsObj.put("projectName", projectDTO.getProjectName());
            jsObj.put("projectAva", projectDTO.getProjectAva());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        
        TeamMemberDAO teamDao = new TeamMemberDAO();
        List<TeamMemberDTO> listTeam = teamDao.searchInFilter(keyword);
        JSONArray jsArr2 = new JSONArray();
        for (TeamMemberDTO teamMemberDTO : listTeam) {
            JSONObject jsObjTeam = new JSONObject();
            jsObjTeam.put("memberId", teamMemberDTO.getMemberId());
            jsObjTeam.put("memberName", teamMemberDTO.getMemberName());
            jsObjTeam.put("projectId", teamMemberDTO.getProject().getProjectId());
            jsObjTeam.put("projectName", teamMemberDTO.getProject().getProjectName());
            jsObjTeam.put("projectAva", teamMemberDTO.getProject().getProjectAva());
            jsArr2.add(jsObjTeam);
        }
        
        SupervisorDAO superDao = new SupervisorDAO();
        List<SupervisorDTO> listSup = superDao.searchInFilter(keyword);
        JSONArray jsArr3 = new JSONArray();
        for (SupervisorDTO supervisorDTO : listSup) {
            JSONObject jsObjSup = new JSONObject();
            jsObjSup.put("supervisorId", supervisorDTO.getSupervisorId());
            jsObjSup.put("supervisorName", supervisorDTO.getSupervisorName());
            jsObjSup.put("projectId", supervisorDTO.getProject().getProjectId());
            jsObjSup.put("projectName", supervisorDTO.getProject().getProjectName());
            jsObjSup.put("projectAva", supervisorDTO.getProject().getProjectAva());
            jsArr3.add(jsObjSup);
        }
        List jsArrM = new ArrayList();
        List arrayM = new ArrayList();
        if(jsArr.size() > jsArr2.size() && jsArr.size() > jsArr3.size() && jsArr3.size() > jsArr2.size()){
            jsArrM.add(jsArr);
            jsArrM.add(jsArr3);
            jsArrM.add(jsArr2);
            for (ProjectDTO projectDTO : listProject) {
                boolean bien = true;
                for (Object object : jsArrM) {
                    if(projectDTO.getProjectId().equals(object.toString())){
                        bien = false;
                    }
                }
                if (bien) {
                    
                }
            }
            
            for (Object object : jsArr2) {
                for (Object objectMain : jsArrM) {
                    if (objectMain.)
                        object.
                }
            }
            for (Object object : jsArr3) {
                for (Object objectMain : jsArrM) {
                    if (objectMain.)
                        object.
                }
            }
            
            
        }
        jsObjM.put("showFilterInSearch", jsArrM);
        return jsObjM.toJSONString();
    }
*/
}
