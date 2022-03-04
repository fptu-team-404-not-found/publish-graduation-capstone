package team404.restful;

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

@Path("admin")
public class AdminModeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdminModeResource
     */
    public AdminModeResource() {
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
            @QueryParam("UserId") String UserId,
            @QueryParam("RoleId") int RoleId) {
        AccountDAO dao = new AccountDAO();
        dao.updateRole(UserId, RoleId);
    }
}
