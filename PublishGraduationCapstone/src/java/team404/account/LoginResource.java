/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.account;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.json.simple.JSONObject;
import team404.utils.GoogleHelpers;

/**
 * REST Web Service
 *
 * @author thang
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    @Path("/getLoginAccountInfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLoginAccountInfo(
            @QueryParam("accessToken") String accessToken) {
        try{
            GoogleHelpers googleHelper = new GoogleHelpers();
            String userJSON = googleHelper.getUserInfo(accessToken);
            AccountDTO accountDTO = googleHelper.getUserFromJson(userJSON);
            
            AccountDAO accountDAO = new AccountDAO();
            String email = accountDTO.getEmail();
            int role = accountDAO.getRole(email);
            
            JSONObject jsObj = new JSONObject();
            jsObj.put("email", accountDTO.getEmail());
            jsObj.put("name", accountDTO.getName());
            jsObj.put("picture", accountDTO.getPicture());
            jsObj.put("roleId", role);
            
            return jsObj.toJSONString();
       } catch (IOException ex) {
            Logger.getLogger(LoginResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }
}
