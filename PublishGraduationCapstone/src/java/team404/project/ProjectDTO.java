package team404.project;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "project")
public class ProjectDTO implements Serializable {

    private String projectId;
    private String projectName;
    private String projectAva;
    private String semester;
    private String introductionContent;
    private String details;
    private String recap;
    private String createDate;
    private int viewNumber;
    private String authorName;
    private String note;
    private int teamId;
    private int stateId;

    public ProjectDTO() {
        projectId = "";
        projectName = "";
        projectAva = "";
        semester = "";
        introductionContent = "";
        details = "";
        recap = "";
        createDate = "";
        viewNumber = 0;
        authorName = "";
        note = "";
        teamId = 0;
        stateId = 0;
    }

    public ProjectDTO(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public ProjectDTO(String projectId, String projectName, String introductionContent, String projectAva) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.introductionContent = introductionContent;
        this.projectAva = projectAva;
    }

    public ProjectDTO(String projectId, String projectName, String projectAva) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectAva = projectAva;
    }

    public ProjectDTO(String projectId, String projectName, String introductionContent, String details, String recap) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.introductionContent = introductionContent;
        this.details = details;
        this.recap = recap;
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

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the projectAva
     */
    public String getProjectAva() {
        return projectAva;
    }

    /**
     * @param projectAva the projectAva to set
     */
    public void setProjectAva(String projectAva) {
        this.projectAva = projectAva;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * @return the introductionContent
     */
    public String getIntroductionContent() {
        return introductionContent;
    }

    /**
     * @param introductionContent the introductionContent to set
     */
    public void setIntroductionContent(String introductionContent) {
        this.introductionContent = introductionContent;
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
     * @return the recap
     */
    public String getRecap() {
        return recap;
    }

    /**
     * @param recap the recap to set
     */
    public void setRecap(String recap) {
        this.recap = recap;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the viewNumber
     */
    public int getViewNumber() {
        return viewNumber;
    }

    /**
     * @param viewNumber the viewNumber to set
     */
    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    /**
     * @return the authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * @param authorName the authorName to set
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
     * @return the teamId
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * @param teamId the teamId to set
     */
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    /**
     * @return the stateId
     */
    public int getStateId() {
        return stateId;
    }

    /**
     * @param stateId the stateId to set
     */
    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" + "projectId=" + projectId + ", projectName=" + projectName + ", projectAva=" + projectAva + ", semester=" + semester + ", introductionContent=" + introductionContent + ", details=" + details + ", recap=" + recap + ", createDate=" + createDate + ", viewNumber=" + viewNumber + ", authorName=" + authorName + ", note=" + note + ", teamId=" + teamId + ", stateId=" + stateId + '}';
    }
    
    
}
