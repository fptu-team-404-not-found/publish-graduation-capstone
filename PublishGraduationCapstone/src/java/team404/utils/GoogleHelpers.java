/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import team404.user.UserDTO;

/**
 *
 * @author jike
 */
public class GoogleHelpers {
    public String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(MyApplicationConstants.GoogleLoginFeatures.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", MyApplicationConstants.GoogleLoginFeatures.GOOGLE_CLIENT_ID)
                        .add("client_secret", MyApplicationConstants.GoogleLoginFeatures.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", MyApplicationConstants.GoogleLoginFeatures.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", MyApplicationConstants.GoogleLoginFeatures.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public String getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = MyApplicationConstants.GoogleLoginFeatures.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
//        System.out.println("response: "+response);
//        UserDTO user = new Gson().fromJson(response, UserDTO.class);
//        System.out.println("user: "+user);
        return response;
    }
    public UserDTO getUserFromJson(String response){
        UserDTO user = new Gson().fromJson(response, UserDTO.class);
        return user;
    }
}
