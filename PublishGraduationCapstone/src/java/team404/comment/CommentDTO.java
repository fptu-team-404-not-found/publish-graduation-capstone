package team404.comment;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;
import team404.project.ProjectDTO;
import team404.sharepost.SharePostDTO;
import team404.account.AccountDTO;

@XmlRootElement(name = "comment")
public class CommentDTO {

    private int commentId;
    private Timestamp commentDate;
    private String commentContent;
    private SharePostDTO post;
    private AccountDTO user;
    private ProjectDTO project;

    public CommentDTO() {
        commentId = 0;
        commentDate = null;
        commentContent = "";
        post = null;
        user = null;
        project = null;
    }

    public CommentDTO(int commentId, Timestamp commentDate, String commentContent, SharePostDTO post, AccountDTO user, ProjectDTO project) {
        this.commentId = commentId;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.post = post;
        this.user = user;
        this.project = project;
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
     * @return the post
     */
    public SharePostDTO getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(SharePostDTO post) {
        this.post = post;
    }

    /**
     * @return the user
     */
    public AccountDTO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(AccountDTO user) {
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

    @Override
    public String toString() {
        return "CommentDTO{" + "commentId=" + commentId + ", commentDate=" + commentDate + ", commentContent=" + commentContent + ", post=" + post + ", user=" + user + ", project=" + project + '}';
    }
}
