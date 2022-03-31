package team404.restful;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;
import team404.sharepost.SharePostDAO;
import team404.sharepost.SharePostDTO;
import team404.supervisor.SupervisorDAO;
import team404.supervisor.SupervisorDTO;
import team404.teammember.TeamMemberDAO;
import team404.teammember.TeamMemberDTO;
import team404.teammember.TeamMemberList;
import team404.upcomingproject.UpcomingProjectDAO;
import team404.upcomingproject.UpcomingProjectDTO;

@Path("admin")
public class AdminModeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdminModeResource
     */
    public AdminModeResource() {
    }

    @Path("/saveSupervisor")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveSupervisor(String object) {
        Gson gson = new Gson();
        SupervisorDTO supervisor = gson.fromJson(object, SupervisorDTO.class);
        AccountDAO accountDao = new AccountDAO();
        AccountDTO accountDto = new AccountDTO();

        SupervisorDAO supDao = new SupervisorDAO();
        SupervisorDTO supDto = new SupervisorDTO();

        System.out.println("in thu: ");
        System.out.println(supervisor);

        accountDto = accountDao.getEmail(supervisor.getEmail());
        if (accountDto != null) {
            supDto = supDao.getInforSupWithMail(supervisor.getEmail());
            if (supDto == null) {
                supDao.insertSupervisor(supervisor.getEmail(), supervisor);
            }
            accountDao.updateRoleInAdminMode(supervisor.getEmail());
        } else {
            accountDao.createSupervisorAccountInAdminMode(supervisor.getEmail(), supervisor);
        }

    }

    @Path("/saveSupervisorsList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveSupervisorsList(String object) {
        Type listType = new TypeToken<ArrayList<SupervisorDTO>>() {
        }.getType();
        ArrayList<SupervisorDTO> list = new Gson().fromJson(object, listType);
        AccountDAO accountDao = new AccountDAO();
        AccountDTO accountDto = new AccountDTO();

        SupervisorDAO supDao = new SupervisorDAO();
        SupervisorDTO supDto = new SupervisorDTO();
        for (SupervisorDTO supervisorDTO : list) {
            System.out.println("in ne: ");
            System.out.println(supervisorDTO);
            accountDto = accountDao.getEmail(supervisorDTO.getEmail());
            if (accountDto != null) {
                supDto = supDao.getInforSupWithMail(supervisorDTO.getEmail());
                if (supDto == null) {
                    supDao.insertSupervisor(supervisorDTO.getEmail(), supervisorDTO);
                }
                accountDao.updateRoleInAdminMode(supervisorDTO.getEmail());
            } else {
                accountDao.createSupervisorAccountInAdminMode(supervisorDTO.getEmail(), supervisorDTO);
            }
        }
    }

    @Path("/saveUpcomingList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveUpcomingList(String object) {
        Type listType = new TypeToken<ArrayList<UpcomingProjectDTO>>() {
        }.getType();
        ArrayList<UpcomingProjectDTO> list = new Gson().fromJson(object, listType);
        UpcomingProjectDAO upcomingDao = new UpcomingProjectDAO();
        UpcomingProjectDTO upcomingDto = new UpcomingProjectDTO();
        for (UpcomingProjectDTO upcomingProjectDTO : list) {
            System.out.println("in nha: ");
            System.out.println(upcomingProjectDTO);
            upcomingDto = upcomingDao.checkUpcoming(upcomingProjectDTO.getUpcomingProjectId());
            if (upcomingDto == null) {
                upcomingDao.insertUpcomingProject(upcomingProjectDTO);
            }

        }
    }

    @Path("/deleteUpcoming")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUpcoming(String object) {
        Gson gson = new Gson();
        UpcomingProjectDTO upcoming = gson.fromJson(object, UpcomingProjectDTO.class);
        System.out.println("in thu: ");
        System.out.println(upcoming);
        //upcoming la con Upcoming da lay ve. Can xoa no trong database
        //code day ne Dat
    }

    @Path("/saveAccountList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveAccountList(String object) {
        Gson g = new Gson();
        Type listType = new TypeToken<ArrayList<TeamMemberDTO>>() {
        }.getType();
        ArrayList<TeamMemberDTO> list = new Gson().fromJson(object, listType);
        AccountDAO accountDao = new AccountDAO();
        AccountDTO accountDto = new AccountDTO();

        TeamMemberDAO teamDao = new TeamMemberDAO();
        TeamMemberDTO teamDto = new TeamMemberDTO();
        for (TeamMemberDTO teamMemberDTO : list) {
            System.out.println(teamMemberDTO.toString());
            accountDto = accountDao.getEmail(teamMemberDTO.getEmail());
//            System.out.println("email: "+teamMemberDTO.getEmail());
            if (accountDto != null) {
                teamDto = teamDao.getInforMemberWithEmail(teamMemberDTO.getEmail());
                if (teamDto == null) {
                    teamDao.insertMember(teamMemberDTO.getEmail(), teamMemberDTO);
                }
                accountDao.updateRoleInAdminMode(teamMemberDTO.getEmail());
            } else {
                accountDao.createNewAccountAdminMode(teamMemberDTO.getEmail(), teamMemberDTO);
            }
        }
    }

    @Path("/showSupervisorList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showSupervisorList() {
        SupervisorDAO dao = new SupervisorDAO();
        List<SupervisorDTO> list = dao.getAllSupervisors();
        JSONArray jsArr = new JSONArray();
        for (SupervisorDTO supervisorDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("supervisorId", supervisorDTO.getSupervisorId());
            jsObj.put("supervisorName", supervisorDTO.getSupervisorName());
            jsObj.put("status", supervisorDTO.isStatus());
//            jsObj.put("email", supervisorDTO.getUser().getEmail());

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
            @QueryParam("keyword") String keyword) {
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

    @Path("/searchSupervisorList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String searchSupervisorList(
            @QueryParam("keyword") String keyword) {
        SupervisorDAO dao = new SupervisorDAO();
        List<SupervisorDTO> list = dao.searchSupervisor(keyword);
        JSONArray jsArr = new JSONArray();
        for (SupervisorDTO supervisorDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("supervisorId", supervisorDTO.getSupervisorId());
            jsObj.put("supervisorName", supervisorDTO.getSupervisorName());
            jsObj.put("status", supervisorDTO.isStatus());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("searchSupervisorList", jsArr);
        return jsObj.toJSONString();
    }

    // show post list with approving and approved
    @Path("/showPostListWithApproving")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showPostListWithApproving() {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.getAllPostWithApproving();
        JSONArray jsArr = new JSONArray();
        for (ProjectDTO projectDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", projectDTO.getProjectId());
            jsObj.put("projectName", projectDTO.getProjectName());
            jsObj.put("createDate", projectDTO.getCreateDate().toString());
            jsObj.put("authorName", projectDTO.getAuthorName());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("showPostListWithApproving", jsArr);
        return jsObj.toJSONString();
    }

    @Path("/showPostListWithApproved")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showPostListWithApproved() {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.getAllPostWithApproved();
        JSONArray jsArr = new JSONArray();
        for (ProjectDTO projectDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", projectDTO.getProjectId());
            jsObj.put("projectName", projectDTO.getProjectName());
            jsObj.put("createDate", projectDTO.getCreateDate().toString());
            jsObj.put("authorName", projectDTO.getAuthorName());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("showPostListWithApproved", jsArr);
        return jsObj.toJSONString();
    }

    @Path("/showSharePostListWithApproving")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showSharePostListWithApproving() {
        SharePostDAO dao = new SharePostDAO();
        List<SharePostDTO> list = dao.showSharePostWithApproving();
        JSONArray jsArr = new JSONArray();
        for (SharePostDTO sharePostDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("postId", sharePostDTO.getPostId());
            jsObj.put("title", sharePostDTO.getTitle());
            jsObj.put("createDate", sharePostDTO.getCreateDate().toString());
            if (sharePostDTO.getStudent() != null) {
                jsObj.put("memberName", sharePostDTO.getStudent().getMemberName());
            }
            if (sharePostDTO.getSupervisor() != null) {
                jsObj.put("supervisorName", sharePostDTO.getSupervisor().getSupervisorName());
            }
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("showSharePostListWithApproving", jsArr);
        return jsObj.toJSONString();
    }

    @Path("/showSharePostListWithApproved")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showSharePostListWithApproved() {
        SharePostDAO dao = new SharePostDAO();
        List<SharePostDTO> list = dao.showSharePostWithApproved();
        JSONArray jsArr = new JSONArray();
        for (SharePostDTO sharePostDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("postId", sharePostDTO.getPostId());
            jsObj.put("title", sharePostDTO.getTitle());
            jsObj.put("createDate", sharePostDTO.getCreateDate().toString());
            if (sharePostDTO.getStudent() != null) {
                jsObj.put("memberName", sharePostDTO.getStudent().getMemberName());
            }
            if (sharePostDTO.getSupervisor() != null) {
                jsObj.put("supervisorName", sharePostDTO.getSupervisor().getSupervisorName());
            }
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("showSharePostListWithApproved", jsArr);
        return jsObj.toJSONString();
    }
//    Dat show comment theo date 

    @Path("/showCommentByDateWithParameter")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showCommentByDateWithParameter(
            @QueryParam("commentDate") Date commentDate) {
        CommentDAO dao = new CommentDAO();
        List<CommentDTO> list = dao.showCommentInAdminModeWithParameter(commentDate);
        JSONArray jsArr = new JSONArray();
        for (CommentDTO commentDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("commentDate", commentDTO.getCommentDate().toString());
            jsObj.put("commentContent", commentDTO.getCommentContent());
            jsObj.put("email", commentDTO.getUser().getEmail());
            if (commentDTO.getPost() != null) {
                jsObj.put("sharePostId", commentDTO.getPost().getPostId());
                jsObj.put("sharePostTitle", commentDTO.getPost().getTitle());
            }
            if (commentDTO.getProject() != null) {
                jsObj.put("projectId", commentDTO.getProject().getProjectId());
                jsObj.put("projectName", commentDTO.getProject().getProjectName());
            }
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("showCommentByDateWithParameter", jsArr);
        return jsObj.toJSONString();
    }

    @Path("/showCommentByDate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showCommentByDate() {
        CommentDAO dao = new CommentDAO();
        List<CommentDTO> list = dao.showCommentInAdminMode();
        JSONArray jsArr = new JSONArray();
        for (CommentDTO commentDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("commentDate", commentDTO.getCommentDate().toString());
            jsObj.put("commentContent", commentDTO.getCommentContent());
            jsObj.put("email", commentDTO.getUser().getEmail());
            if (commentDTO.getPost() != null) {
                jsObj.put("Id", commentDTO.getPost().getPostId());
                jsObj.put("sharePostTitle", commentDTO.getPost().getTitle());
            }
            if (commentDTO.getProject() != null) {
                jsObj.put("Id", commentDTO.getProject().getProjectId());
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
    public String countCommentInDate() {
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
