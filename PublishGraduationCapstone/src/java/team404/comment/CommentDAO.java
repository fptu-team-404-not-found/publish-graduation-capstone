package team404.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import team404.account.AccountDAO;
import team404.account.AccountDTO;
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;
import team404.sensitiveword.SensitiveWordDAO;
import team404.sharepost.SharePostDAO;
import team404.sharepost.SharePostDTO;
import team404.utils.DBHelpers;

public class CommentDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<CommentDTO> getCommentsOfProject(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Comment.CommentId, Comment.CommentDate, Comment.CommentContent, Account.Email "
                        + "From Comment JOIN Account "
                        + "ON Comment.Account = Account.Email "
                        + "Where ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();

                List<CommentDTO> list = new ArrayList<>();
                while (rs.next()) {
                    int commentId = rs.getInt("CommentId");
                    Date commentDate = rs.getDate("CommentDate");
                    String commentContent = rs.getNString("CommentContent");
                    String email = rs.getString("Email");

                    AccountDAO userDAO = new AccountDAO();
                    AccountDTO userDTO = userDAO.getUserNamePictureByEmail(email);

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
                String sql = "Select Comment.CommentId, Comment.CommentDate, Comment.CommentContent, Account.Email "
                        + "From Comment JOIN Account "
                        + "ON Comment.Account = Account.Email "
                        + "Where PostId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, postId);
                rs = stm.executeQuery();

                List<CommentDTO> list = new ArrayList<>();
                while (rs.next()) {
                    int commentId = rs.getInt("CommentId");
                    Date commentDate = rs.getDate("CommentDate");
                    String commentContent = rs.getNString("CommentContent");
                    String email = rs.getString("Email");

                    AccountDAO userDAO = new AccountDAO();
                    AccountDTO userDTO = userDAO.getUserNamePictureByEmail(email);

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

    public String containBannedWords(String commentContent) {
        SensitiveWordDAO sensitiveWordDAO = new SensitiveWordDAO();
        List<String> list = sensitiveWordDAO.getBannedWordList();
        for (String bannedWord : list) {
            if (commentContent.contains(bannedWord)) {
                return bannedWord;
            }
        }

        return "";
    }

    public String commentOnProject(String projectId, String email, String commentContent) {
        try {
            String bannedWord = containBannedWords(commentContent);
            if (!"".equals(bannedWord)) {
                return bannedWord;
            }

            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Comment(CommentContent, Account, ProjectId) "
                        + "Values(?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setNString(1, commentContent);
                stm.setString(2, email);
                stm.setString(3, projectId);
                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    return "";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (NamingException ex) {
            Logger.getLogger(CommentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CommentDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    public String commentOnShare(String shareId, String email, String commentContent) {
        try {
            String bannedWord = containBannedWords(commentContent);
            if (!"".equals(bannedWord)) {
                return bannedWord;
            }

            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Comment(CommentContent, Account, PostId) "
                        + "Values(?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setNString(1, commentContent);
                stm.setString(2, email);
                stm.setString(3, shareId);
                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    return "";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (NamingException ex) {
            Logger.getLogger(CommentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CommentDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    public List<CommentDTO> showDateOfComment() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select c.CommentDate "
                        + "From Comment c "
                        + "Group by c.CommentDate ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<CommentDTO> list = new ArrayList<>();
                while(rs.next()){
                    Date commentDate = rs.getDate("CommentDate");
                    CommentDTO dto = new CommentDTO();
                    dto.setCommentDate(commentDate);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (NamingException ex) {
            Logger.getLogger(CommentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CommentDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public List<CommentDTO> showCommentByDate(Date commentDate){
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select c.CommentContent, acc.Email, c.PostId, c.ProjectId "
                        + "From Comment c inner join Account acc "
                        + "On c.Account = acc.Email "
                        + "Where c.CommentDate = ? ";
                stm = con.prepareStatement(sql);
                stm.setDate(1, commentDate);
                rs = stm.executeQuery();
                List<CommentDTO> list = new ArrayList<>();
                
                AccountDAO accountDao = new AccountDAO();
                AccountDTO accountDto = new AccountDTO();
                
                SharePostDAO sharePostDao = new SharePostDAO();
                SharePostDTO sharePostDto = new SharePostDTO();
                
                ProjectDAO projectDao = new ProjectDAO();
                ProjectDTO projectDto = new ProjectDTO();
                while(rs.next()){
                    String commentContent = rs.getNString("CommentContent");
                    accountDto = accountDao.getEmail(rs.getString("Email"));
                    sharePostDto = sharePostDao.getSharePostDetail(rs.getInt("PostId"));
                    projectDto = projectDao.getSingleProject(rs.getString("ProjectId"));
                    CommentDTO dto = new CommentDTO();
                    dto.setCommentContent(commentContent);
                    dto.setUser(accountDto);
                    dto.setPost(sharePostDto);
                    dto.setProject(projectDto);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (NamingException ex) {
            Logger.getLogger(CommentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CommentDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
