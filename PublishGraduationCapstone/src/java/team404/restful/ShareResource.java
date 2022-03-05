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

@Path("share")
public class ShareResource {

    @Context
    private UriInfo context;

    public ShareResource() {
    }

    @Path("showCommentsOfShare")
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
            jsObj.put("commentDate", commentDTO.getCommentDate());
            jsObj.put("commentContent", commentDTO.getCommentContent());
            jsObj.put("userAva", commentDTO.getUser().getPicture());
            jsObj.put("userName", commentDTO.getUser().getName());
            
            jsArr.add(jsObj);
        }
        
        JSONObject jsObj = new JSONObject();
        jsObj.put("commentsOfShare", jsArr);
        return jsObj.toJSONString();
    }
}
