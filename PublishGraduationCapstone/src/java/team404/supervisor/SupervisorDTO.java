package team404.supervisor;

public class SupervisorDTO {
    
    private String supervisorId;
    private String supervisorName;
    private String supervisorImage;
    private String information;
    private String postion;

    public SupervisorDTO() {
    }

    public SupervisorDTO(String supervisorName, String supervisorImage, String information, String postion) {
        this.supervisorName = supervisorName;
        this.supervisorImage = supervisorImage;
        this.information = information;
        this.postion = postion;
    }

    /**
     * @return the supervisorId
     */
    public String getSupervisorId() {
        return supervisorId;
    }

    /**
     * @param supervisorId the supervisorId to set
     */
    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    /**
     * @return the supervisorName
     */
    public String getSupervisorName() {
        return supervisorName;
    }

    /**
     * @param supervisorName the supervisorName to set
     */
    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    /**
     * @return the supervisorImage
     */
    public String getSupervisorImage() {
        return supervisorImage;
    }

    /**
     * @param supervisorImage the supervisorImage to set
     */
    public void setSupervisorImage(String supervisorImage) {
        this.supervisorImage = supervisorImage;
    }

    /**
     * @return the information
     */
    public String getInformation() {
        return information;
    }

    /**
     * @param information the information to set
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * @return the postion
     */
    public String getPostion() {
        return postion;
    }

    /**
     * @param postion the postion to set
     */
    public void setPostion(String postion) {
        this.postion = postion;
    }
    
    
}
