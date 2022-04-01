package team404.restful;

import com.google.gson.Gson;
import java.util.ArrayList;
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
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;
import team404.projectimage.ProjectImageDTO;
import team404.sharepost.SharePostDAO;
import team404.sharepost.SharePostDTO;
import team404.supervisor.SupervisorDAO;
import team404.supervisor.SupervisorDTO;
import team404.teammember.TeamMemberDAO;
import team404.teammember.TeamMemberDTO;

@Path("staff")
public class StaffModeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StaffModeResource
     */
    public StaffModeResource() {
    }

    //-- TIENHUYNHTN --// --OK
    @Path("/loadSupervisor")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public String loadSupervisor() {
        SupervisorDAO dao = new SupervisorDAO();
        List<SupervisorDTO> list = dao.getAllSupervisors();
        JSONArray jsArr = new JSONArray();
        for (SupervisorDTO supervisorDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("supervisorId", supervisorDTO.getSupervisorId());
            jsObj.put("supervisorName", supervisorDTO.getSupervisorName());
            jsArr.add(jsObj);
        }
        return jsArr.toJSONString();
    }
    
    //-- TIENHUYNHTN --// --OK
    @Path("/loadStudentId")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String loadStudentId() {
        TeamMemberDAO dao = new TeamMemberDAO();
        List<String> list = dao.getStudentId();
        JSONArray jsArr = new JSONArray();
        for (String studentId : list) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("studentId", studentId);
            jsArr.add(jSONObject);
        }
        return jsArr.toJSONString();
    }
    
    //-- TIENHUYNHTN --// --OK
    @Path("/loadTeamMemberInfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String loadTeamMemberInfo(
            @QueryParam("studentId") String studentId) {
        TeamMemberDAO dao = new TeamMemberDAO();
        TeamMemberDTO dto = dao.getInforMember(studentId);
        JSONObject jsObj = new JSONObject();
        jsObj.put("name", dto.getMemberName());
        jsObj.put("mail", dto.getUser().getEmail());
        jsObj.put("phone", dto.getPhone());
        return jsObj.toJSONString();
    }
    
    //-- TIENHUYNHTN --// --OK
    @Path("/loadApprovingProject")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String loadApprovingProject() {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.getAllPostWithApproving();
        JSONArray jsArr = new JSONArray();
        for (ProjectDTO projectDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", projectDTO.getProjectId());
            jsObj.put("projectName", projectDTO.getProjectName());
            jsObj.put("projectDate", projectDTO.getCreateDate().toString());
            jsArr.add(jsObj);
        }
        return jsArr.toJSONString();
    }
    
    //-- TIENHUYNHTN --// --OK
    @Path("/loadApprovedProject")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String loadApprovedProject() {
        ProjectDAO dao = new ProjectDAO();
        List<ProjectDTO> list = dao.getAllPostWithApproved();
        JSONArray jsArr = new JSONArray();
        for (ProjectDTO projectDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("projectId", projectDTO.getProjectId());
            jsObj.put("projectName", projectDTO.getProjectName());
            jsObj.put("projectDate", projectDTO.getCreateDate().toString());
            jsArr.add(jsObj);
        }
        return jsArr.toJSONString();
    }
    
    //-- TIENHUYNHTN --//
    @Path("/insertProject")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertProject(String project){
//    public Response insertProject(String project){
        Gson gson = new Gson();
        ProjectDTO dto = gson.fromJson(project, ProjectDTO.class);
        System.out.println("dto nene: " + dto);
//        ProjectDAO dao = new ProjectDAO();
//        boolean result = dao.insertProject(dto);
//        if(result == false)
//            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
//        return Response.status(Response.Status.CREATED).build();
    }
}
