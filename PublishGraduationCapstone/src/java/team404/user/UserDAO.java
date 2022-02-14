package team404.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import team404.utils.DBHelpers;

public class UserDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public boolean checkId(String id)
            throws SQLException, NamingException {
        try {
            //1. make connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Select UserId "
                        + "From User_Table "
                        + "Where UserId = ?";
                //3. Create statement obj to load SQL string
                //and set value to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public boolean createNewAcccount(UserDTO dto)
            throws SQLException, NamingException {

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert Into User_Table("
                        + "sub, email, name, picture"
                        + ") Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getSub());
                stm.setString(2, dto.getEmail());
                stm.setString(3, dto.getName());
                stm.setString(4, dto.getPicture());
                int affectedRow = stm.executeUpdate();
                if (affectedRow > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
