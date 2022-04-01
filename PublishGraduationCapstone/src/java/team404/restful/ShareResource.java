package team404.restful;

import com.google.gson.Gson;
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
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.comment.CommentDAO;
import team404.comment.CommentDTO;
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;
import team404.sharepost.SharePostDAO;
import team404.sharepost.SharePostDTO;

@Path("share")
public class ShareResource {

    @Context
    private UriInfo context;

    public ShareResource() {
    }
  
    //-- TIENHUYNHTN --//
    @Path("/showCommentsOfShare")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCommentsOfShare(
            @QueryParam("shareId") String shareId) {
        CommentDAO dao = new CommentDAO();
        List<CommentDTO> list = dao.getCommentsOfShare(shareId);

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
        jsObj.put("commentsOfShare", jsArr);
        return jsObj.toJSONString();
    }
    
    //-- TIENHUYNHTN --// //OK
    @Path("/commentOnShare")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String commentOnProject(
            @FormDataParam("shareId") String shareId, 
            @FormDataParam("email") String email, 
            @FormDataParam("commentContent") String commentContent) {
        CommentDAO commentDAO = new CommentDAO();
        String result = commentDAO.commentOnShare(shareId, email, commentContent);
        
        return result;
    }

    @Path("/showSharePostList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showSharePostList(
            @QueryParam("projectId") String projectId) {
        SharePostDAO dao = new SharePostDAO();
        List<SharePostDTO> list = dao.getSharePostList(projectId);

        JSONArray jsArr = new JSONArray();
        for (SharePostDTO sharePostDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("postId", sharePostDTO.getPostId());
            jsObj.put("title", sharePostDTO.getTitle());
            jsObj.put("status", sharePostDTO.getState().getStateName());
            jsObj.put("projectTitle", sharePostDTO.getProject().getProjectName());
            if (sharePostDTO.getStudent() != null) {
                 jsObj.put("Avatar", sharePostDTO.getStudent().getMemberAvatar());
                 jsObj.put("authorName", sharePostDTO.getStudent().getMemberName());
            }
            if (sharePostDTO.getSupervisor() != null) {
                jsObj.put("Avatar", sharePostDTO.getSupervisor().getSupervisorImage());
                jsObj.put("authorName", sharePostDTO.getSupervisor().getSupervisorName());
            }

            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("showSharePostList", jsArr);
        return jsObj.toJSONString();
    }

    @Path("/sharePostDetail")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sharePostDetail(
            @QueryParam("postId") int postId) {
        ProjectDAO projectDao = new ProjectDAO();
        SharePostDAO dao = new SharePostDAO();
        SharePostDTO dto = dao.getSharePostDetail(postId);
        JSONObject jsObj = new JSONObject();
        jsObj.put("title", dto.getTitle());
        jsObj.put("details", dto.getDetails());
        jsObj.put("createDate", dto.getCreateDate().toString());
        if (dto.getStudent() != null) {
           jsObj.put("AuthorName", dto.getStudent().getMemberName());
        }
        if (dto.getSupervisor() != null) {
           jsObj.put("AuthorName", dto.getSupervisor().getSupervisorName());
        }
        ProjectDTO projectDto = projectDao.getSingleProject(dto.getProject().getProjectId());
        JSONObject jsObj2 = new JSONObject();
        jsObj2.put("ProjectId", projectDto.getProjectId());
        jsObj2.put("ProjectName", projectDto.getProjectName());
        jsObj2.put("ProjectAva", projectDto.getProjectAva());;
        jsObj2.put("ProjectIntroduction", projectDto.getIntroductionContent());;
        
        jsObj.put("project", jsObj2);
        JSONObject jsObjM = new JSONObject();
        jsObjM.put("sharePostDetail", jsObj);
        return jsObjM.toJSONString();
    }
    @Path("/addSharePost")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSharePost(String sharePost){
        Gson gson = new Gson();
        SharePostDTO dto = gson.fromJson(sharePost, SharePostDTO.class);
        SharePostDAO dao = new SharePostDAO();
        boolean result = dao.addSharePost(dto);
        if(result == false)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        return Response.status(Response.Status.CREATED).build();
    }
}
