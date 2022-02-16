package team404.comment;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comment")
public class CommentDTO {
    private int commentId;
    private Timestamp commentDate;
    private String commentContent;
    private String userId;
    private int postId;
    private String projectId;

    public CommentDTO() {
    }

    public CommentDTO(int commentId, Timestamp commentDate, String commentContent, String userId, String projectId) {
        this.commentId = commentId;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.userId = userId;
        this.projectId = projectId;
    }

    public CommentDTO(int commentId, Timestamp commentDate, String commentContent, String userId, int postId) {
        this.commentId = commentId;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.userId = userId;
        this.postId = postId;
    }

    public CommentDTO(int commentId, Timestamp commentDate, String commentContent, String userId, int postId, String projectId) {
        this.commentId = commentId;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.userId = userId;
        this.postId = postId;
        this.projectId = projectId;
    }
    
    /**
     * @return the commentId
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    /**
     * @return the commentDate
     */
    public Timestamp getCommentDate() {
        return commentDate;
    }

    /**
     * @param commentDate the commentDate to set
     */
    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    /**
     * @return the commentContent
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * @param commentContent the commentContent to set
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the postId
     */
    public int getPostId() {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    
}
