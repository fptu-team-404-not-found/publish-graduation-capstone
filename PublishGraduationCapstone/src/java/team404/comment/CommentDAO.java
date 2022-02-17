package team404.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import team404.user.UserDAO;
import team404.user.UserDTO;
import team404.utils.DBHelpers;

public class CommentDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<CommentDTO> getCommentsOfProject(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Comment.CommentId, Comment.CommentDate, Comment.CommentContent, User_Table.UserId "
                        + "From Comment JOIN User_Table "
                        + "ON Comment.UserId = User_Table.UserId "
                        + "Where ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();

                List<CommentDTO> list = new ArrayList<>();
                while (rs.next()) {
                    int commentId = rs.getInt("CommentId");
                    Timestamp commentDate = rs.getTimestamp("CommentDate");
                    String commentContent = rs.getNString("CommentContent");
                    String userId = rs.getString("UserId");

                    UserDAO userDao = new UserDAO();
                    UserDTO userDTO = userDao.getUserNamePictureByUserId(userId);

                    CommentDTO commentDTO = new CommentDTO(commentId, commentDate, commentContent, userDTO);

                    list.add(commentDTO);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public int getNumberCommentsOfProject(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select COUNT(*) AS [Number Of Comments] "
                        + "From Comment "
                        + "Where ProjectId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int numberOfComments = rs.getInt("Number Of Comments");
                    return numberOfComments;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;
    }
}
