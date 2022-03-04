package team404.projectimage;

import javax.xml.bind.annotation.XmlRootElement;
import team404.project.ProjectDTO;

@XmlRootElement(name = "projectImage")
public class ProjectImageDTO {

    private int projectImageId;
    private String imageUrl;
    private ProjectDTO project;

    public ProjectImageDTO() {
        projectImageId = 0;
        imageUrl = "";
        project = null;
    }

    public ProjectImageDTO(int projectImageId, String imageUrl, ProjectDTO project) {
        this.projectImageId = projectImageId;
        this.imageUrl = imageUrl;
        this.project = project;
    }

    /**
     * @return the projectImageId
     */
    public int getProjectImageId() {
        return projectImageId;
    }

    /**
     * @param projectImageId the projectImageId to set
     */
    public void setProjectImageId(int projectImageId) {
        this.projectImageId = projectImageId;
    }

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        return "ProjectImageDTO{" + "projectImageId=" + projectImageId + ", imageUrl=" + imageUrl + ", project=" + project + '}';
    }
}
