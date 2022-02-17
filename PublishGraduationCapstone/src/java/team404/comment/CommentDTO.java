package team404.comment;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;
import team404.project.ProjectDTO;
import team404.user.UserDTO;

@XmlRootElement(name = "comment")
public class CommentDTO {
    private int commentId;
    private Timestamp commentDate;
    private String commentContent;
    
    private int postId;
    private UserDTO user;
    private ProjectDTO project;

    public CommentDTO() {
    }

    public CommentDTO(int commentId, Timestamp commentDate, String commentContent, int postId, UserDTO user, ProjectDTO project) {
        this.commentId = commentId;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.postId = postId;
        this.user = user;
        this.project = project;
    }

    public CommentDTO(int commentId, Timestamp commentDate, String commentContent, UserDTO user) {
        this.commentId = commentId;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.user = user;
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
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }

    /**
     * @return the project
     */
    public ProjectDTO getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    
}
