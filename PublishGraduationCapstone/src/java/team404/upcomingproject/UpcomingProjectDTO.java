package team404.upcomingproject;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "upcomingProject")
public class UpcomingProjectDTO implements Serializable {

    private int upcomingProjectId;
    private String projectName;
    private String location;
    private String date;
    private String description;
    private String image;

    public UpcomingProjectDTO() {
    }

    public UpcomingProjectDTO(int upcomingProjectId, String projectName, String location, String date, String description, String image) {
        this.upcomingProjectId = upcomingProjectId;
        this.projectName = projectName;
        this.location = location;
        this.date = date;
        this.description = description;
        this.image = image;
    }

    /**
     * @return the upcomingProjectId
     */
    public int getUpcomingProjectId() {
        return upcomingProjectId;
    }

    /**
     * @param upcomingProjectId the upcomingProjectId to set
     */
    public void setUpcomingProjectId(int upcomingProjectId) {
        this.upcomingProjectId = upcomingProjectId;
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
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

}
