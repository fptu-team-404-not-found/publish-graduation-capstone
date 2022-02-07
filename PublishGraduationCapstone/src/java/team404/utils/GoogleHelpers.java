package team404.utils;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import team404.user.UserDTO;

public class GoogleHelpers {

    public String getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = MyApplicationConstants.GoogleLoginFeatures.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        return response;
    }

    public UserDTO getUserFromJson(String response) {
        UserDTO user = new Gson().fromJson(response, UserDTO.class);
        return user;
    }
}
