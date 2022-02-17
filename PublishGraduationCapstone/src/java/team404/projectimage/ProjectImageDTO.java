package team404.projectimage;

public class ProjectImageDTO {
    
    private int projectImageId;
    private String imageUrl;
    private String ProjectId;

    public ProjectImageDTO() {
    }

    public ProjectImageDTO(int projectImageId, String imageUrl, String ProjectId) {
        this.projectImageId = projectImageId;
        this.imageUrl = imageUrl;
        this.ProjectId = ProjectId;
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
     * @return the ProjectId
     */
    public String getProjectId() {
        return ProjectId;
    }

    /**
     * @param ProjectId the ProjectId to set
     */
    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }
    
    
}
