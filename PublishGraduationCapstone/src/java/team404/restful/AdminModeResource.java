package team404.restful;

import java.sql.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.roles.RolesDAO;
import team404.roles.RolesDTO;
import team404.account.AccountDAO;
import team404.account.AccountDTO;
import team404.comment.CommentDAO;
import team404.comment.CommentDTO;
import team404.supervisor.SupervisorDAO;
import team404.supervisor.SupervisorDTO;

@Path("admin")
public class AdminModeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdminModeResource
     */
    public AdminModeResource() {
    }
    
    @Path("/showSupervisorList") 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showSupervisorList(){
        SupervisorDAO dao = new SupervisorDAO();
        List<SupervisorDTO> list = dao.getAllSupervisors();
        JSONArray jsArr = new JSONArray();
        for (SupervisorDTO supervisorDTO :  list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("supervisorId", supervisorDTO.getSupervisorId());
            jsObj.put("supervisorName", supervisorDTO.getSupervisorName());
            jsObj.put("status", supervisorDTO.isStatus());
            jsObj.put("email", supervisorDTO.getUser().getEmail());
            
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("showSupervisorList", jsArr);
        return jsObj.toJSONString();
    }
    @Path("/showRoleForAccount") 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showRoleForAccount() {
        RolesDAO dao = new RolesDAO();
        List<RolesDTO> list = dao.getRolesList();
        JSONArray jsArr = new JSONArray();
        for (RolesDTO rolesDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("roleId", rolesDTO.getRoleId());
            jsObj.put("roleName", rolesDTO.getRoleName());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("Roles", jsArr);
        String result = jsObj.toJSONString();
        return result;
    }
    
    
    @Path("/updateRoleForAccount") 
    @GET
    public void updateRoleForAccount(
            @QueryParam("email") String email,
            @QueryParam("RoleId") int RoleId) {
        AccountDAO dao = new AccountDAO();
        dao.updateRole(email, RoleId);
    }
    
    //-- TIENHUYNHTN --// //OK
    @Path("/showAccountList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showAccountList() {
        AccountDAO accountDAO = new AccountDAO();
        List<AccountDTO> list = accountDAO.showAccountList();
        JSONArray jsArr = new JSONArray();
        for (AccountDTO accountDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("email", accountDTO.getEmail());
            jsObj.put("role", accountDTO.getRole().getRoleName());
            jsArr.add(jsObj);
        }
        return jsArr.toJSONString();
    }
    @Path("/searchAccountList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String searchAccountList(
            @QueryParam("keyword") String keyword){
        AccountDAO dao = new AccountDAO();
        List<AccountDTO> list = dao.searchAccountInAdmin(keyword);
        JSONArray jsArr = new JSONArray();
        for (AccountDTO accountDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("email", accountDTO.getEmail());
            jsObj.put("role", accountDTO.getRole().getRoleName());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("searchAccountList", jsArr);
        return jsObj.toJSONString();
    }
//    Dat show comment theo date 
    @Path("/showCommentByDate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showCommentByDate(){
        CommentDAO dao = new CommentDAO();
        List<CommentDTO> list = dao.showCommentInAdminMode();
        JSONArray jsArr = new JSONArray();
        for (CommentDTO commentDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("commentDate", commentDTO.getCommentDate().toString());
            jsObj.put("commentContent", commentDTO.getCommentContent());
            jsObj.put("email", commentDTO.getUser().getEmail());
            if(commentDTO.getPost() != null){
                jsObj.put("sharePostTitle", commentDTO.getPost().getTitle());
            }
            if(commentDTO.getProject() != null){
                jsObj.put("projectName", commentDTO.getProject().getProjectName());
            }
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("showCommentByDate", jsArr);
        return jsObj.toJSONString();
    }
    @Path("/countCommentInDate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String countCommentInDate(){
        CommentDAO dao = new CommentDAO();
        List<CommentDTO> list = dao.countCommentInAdminMode();
        JSONArray jsArr = new JSONArray();
        for (CommentDTO commentDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("total", commentDTO.getTotal());
            jsObj.put("commentDate", commentDTO.getCommentDate().toString());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("countCommentInDate", jsArr);
        return jsObj.toJSONString();
    }
}
