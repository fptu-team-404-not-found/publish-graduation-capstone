package team404.supervisor;

import team404.user.AccountDTO;

public class SupervisorDTO {
    
    private String supervisorId;
    private String supervisorName;
    private String supervisorImage;
    private String information;
    private String postion;
    private boolean status;
    private AccountDTO user;

    public SupervisorDTO() {
        supervisorId = "";
        supervisorName = "";
        supervisorImage = "";
        information = "";
        postion = "";
        status = false;
        user = null;
    }

    public SupervisorDTO(String supervisorId, String supervisorName, String supervisorImage, String information, String postion, boolean status, AccountDTO user) {
        this.supervisorId = supervisorId;
        this.supervisorName = supervisorName;
        this.supervisorImage = supervisorImage;
        this.information = information;
        this.postion = postion;
        this.status = status;
        this.user = user;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AccountDTO getUser() {
        return user;
    }

    public void setUser(AccountDTO user) {
        this.user = user;
    }
    
    
}
