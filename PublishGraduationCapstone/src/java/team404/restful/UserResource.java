/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.restful;

import com.google.gson.Gson;
import com.sun.jersey.multipart.FormDataParam;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.account.AccountDAO;
import team404.account.AccountDTO;
import team404.favorite.FavoriteDAO;
import team404.project.ProjectDTO;
import team404.supervisor.SupervisorDAO;
import team404.supervisor.SupervisorDTO;
import team404.teammember.TeamMemberDAO;
import team404.teammember.TeamMemberDTO;

/**
 * REST Web Service
 *
 * @author thang
 */
@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    @Path("/addAlternativeMail")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void saveSupervisor(
            @FormDataParam("email") String email,
            @FormDataParam("backupEmail") String backup) {

        TeamMemberDAO teamDao = new TeamMemberDAO();
        TeamMemberDTO teamDto = new TeamMemberDTO();

        teamDao.insertBackupMail(email, backup);

    }

    @Path("/checkTeamMember")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String checkTeamMember(
            @QueryParam("email") String email) {
        TeamMemberDAO teamDao = new TeamMemberDAO();
        TeamMemberDTO teamDto = teamDao.checkTeamMember(email);
        if(teamDto != null){
            return "Yes";
        }else{
            return "No";
        }
        
    }
    @Path("/showFavoriteProject")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showFavoriteProject(
            @QueryParam("email") String email){
        FavoriteDAO fDao = new FavoriteDAO();
        List<ProjectDTO> list = fDao.getProjectFavorite(email);
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
        jsObj.put("showFavoriteProject", jsArr);
        String result = jsObj.toJSONString();
        return result;
    }
}
