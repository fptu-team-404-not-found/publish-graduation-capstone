/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.restful;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.sensitiveword.SensitiveWordDAO;
import team404.sensitiveword.SensitiveWordDTO;

/**
 * REST Web Service
 *
 * @author jike
 */
@Path("sensitiveword")
public class SensitivewordResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SensitivewordResource
     */
    public SensitivewordResource() {
    }

    /**
     * Retrieves representation of an instance of team404.restful.SensitivewordResource
     * @return an instance of java.lang.String
     */
    @Path("/showSensitiveWord")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showSensitiveWord() {
        SensitiveWordDAO dao = new SensitiveWordDAO();
        List<SensitiveWordDTO> list = dao.getSensitiveWordList();
        JSONArray jsArr = new JSONArray();
        for (SensitiveWordDTO sensitiveWordDTO : list) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("wordId", sensitiveWordDTO.getWordId());
            jsObj.put("bannedWord", sensitiveWordDTO.getBannedWord());
            jsArr.add(jsObj);
        }
        JSONObject jsObj = new JSONObject();
        jsObj.put("showSensitiveWord", jsArr);
        return jsObj.toJSONString();
    }

    
}
