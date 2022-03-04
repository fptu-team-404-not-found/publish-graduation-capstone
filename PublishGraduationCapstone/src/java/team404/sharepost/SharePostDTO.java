package team404.sharepost;

import java.sql.Timestamp;
import team404.project.ProjectDTO;
import team404.states.StatesDTO;
import team404.supervisor.SupervisorDTO;
import team404.teammember.TeamMemberDTO;

public class SharePostDTO {
    private int postId;
    private String title;
    private String details;
    private Timestamp createDate;
    private String note;
    
    private TeamMemberDTO student;
    private SupervisorDTO supervisor;
    private StatesDTO state;
    private ProjectDTO project;

    public SharePostDTO() {
        postId = 0;
        title = "";
        details = "";
        createDate = null;
        note = "";
        student = null;
        supervisor = null;
        state = null;
        project = null;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the student
     */
    public TeamMemberDTO getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(TeamMemberDTO student) {
        this.student = student;
    }

    /**
     * @return the supervisor
     */
    public SupervisorDTO getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(SupervisorDTO supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * @return the state
     */
    public StatesDTO getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(StatesDTO state) {
        this.state = state;
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
