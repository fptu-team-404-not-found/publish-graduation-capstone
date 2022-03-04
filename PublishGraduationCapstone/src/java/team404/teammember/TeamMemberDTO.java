package team404.teammember;

import team404.project.ProjectDTO;
import team404.user.AccountDTO;

public class TeamMemberDTO {
    
    private String memberId;
    private String memberName;
    private String memberAvatar;
    private String phone;
    private String backupEmail;
    private AccountDTO user;
    private ProjectDTO project;

    public TeamMemberDTO() {
        memberId = "";
        memberName = "";
        memberAvatar = "";
        phone = "";
        backupEmail = "";
        user = null;
        project = null;
    }

    public TeamMemberDTO(String memberId, String memberName, String memberAvatar, String phone, String backupEmail, AccountDTO user, ProjectDTO project) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberAvatar = memberAvatar;
        this.phone = phone;
        this.backupEmail = backupEmail;
        this.user = user;
        this.project = project;
    }
    
    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the memberName
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * @param memberName the memberName to set
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * @return the memberAvatar
     */
    public String getMemberAvatar() {
        return memberAvatar;
    }

    /**
     * @param memberAvatar the memberAvatar to set
     */
    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the backupEmail
     */
    public String getBackupEmail() {
        return backupEmail;
    }

    /**
     * @param backupEmail the backupEmail to set
     */
    public void setBackupEmail(String backupEmail) {
        this.backupEmail = backupEmail;
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

    
    
    
}
