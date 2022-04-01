package team404.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import team404.projectimage.ProjectImageDTO;
import team404.semester.SemesterDTO;
import team404.states.StatesDTO;

@XmlRootElement(name = "project")
public class ProjectDTO implements Serializable {

    private String projectId;
    private String projectName;
    private String projectAva;
    private String videoUrl;
    private String introductionContent;
    private String details;
    private String recap;
    private Date createDate;
    private String authorName;
    private int viewNumber;
    private String note;
    private StatesDTO state;
    private SemesterDTO semester;
    private List<ProjectImageDTO> listImages;

    public ProjectDTO() {
        projectId = "";
        projectName = "";
        projectAva = "";
        videoUrl = "";
        introductionContent = "";
        details = "";
        recap = "";
        createDate = null;
        authorName = "";
        viewNumber = 0;
        note = "";
        state = null;
        semester = null;
    }

    public ProjectDTO(String projectId, String projectName, String projectAva, String videoUrl, String introductionContent, String details, String recap, Date createDate, String authorName, int viewNumber, String note, StatesDTO state, SemesterDTO semester) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectAva = projectAva;
        this.videoUrl = videoUrl;
        this.introductionContent = introductionContent;
        this.details = details;
        this.recap = recap;
        this.createDate = createDate;
        this.authorName = authorName;
        this.viewNumber = viewNumber;
        this.note = note;
        this.state = state;
        this.semester = semester;
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
     * @return the videoUrl
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * @param videoUrl the videoUrl to set
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
     * @return the semester
     */
    public SemesterDTO getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(SemesterDTO semester) {
        this.semester = semester;
    }

    public List<ProjectImageDTO> getListImages() {
        return listImages;
    }

    public void setListImages(List<ProjectImageDTO> listImages) {
        this.listImages = listImages;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" + "projectId=" + projectId + ", projectName=" + projectName + ", projectAva=" + projectAva + ", videoUrl=" + videoUrl + ", introductionContent=" + introductionContent + ", details=" + details + ", recap=" + recap + ", createDate=" + createDate + ", authorName=" + authorName + ", viewNumber=" + viewNumber + ", note=" + note + ", state=" + state + ", semester=" + semester + ", listImages=" + listImages + '}';
    }
}
