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
import team404.account.AccountDAO;
import team404.account.AccountDTO;
import team404.utils.DBHelpers;

public class CommentDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<CommentDTO> getCommentsOfProject(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Comment.CommentId, Comment.CommentDate, Comment.CommentContent, Account.UserId "
                        + "From Comment JOIN Account "
                        + "ON Comment.UserId = Account.UserId "
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

                    AccountDAO userDAO = new AccountDAO();
                    AccountDTO userDTO = userDAO.getUserNamePictureByUserId(userId);

                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setCommentId(commentId);
                    commentDTO.setCommentDate(commentDate);
                    commentDTO.setCommentContent(commentContent);
                    commentDTO.setUser(userDTO);

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
    
    public List<CommentDTO> getCommentsOfShare(String postId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Comment.CommentId, Comment.CommentDate, Comment.CommentContent, Account.UserId "
                        + "From Comment JOIN Account "
                        + "ON Comment.UserId = Account.UserId "
                        + "Where PostId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, postId);
                rs = stm.executeQuery();

                List<CommentDTO> list = new ArrayList<>();
                while (rs.next()) {
                    int commentId = rs.getInt("CommentId");
                    Timestamp commentDate = rs.getTimestamp("CommentDate");
                    String commentContent = rs.getNString("CommentContent");
                    String userId = rs.getString("UserId");

                    AccountDAO userDAO = new AccountDAO();
                    AccountDTO userDTO = userDAO.getUserNamePictureByUserId(userId);

                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setCommentId(commentId);
                    commentDTO.setCommentDate(commentDate);
                    commentDTO.setCommentContent(commentContent);
                    commentDTO.setUser(userDTO);

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
}
