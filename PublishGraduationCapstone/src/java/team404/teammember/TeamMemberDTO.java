package team404.teammember;

public class TeamMemberDTO {
    
    private String memberId;
    private String memberName;
    private String memberAvatar;
    private String email;
    private String phone;
    private String backupEmail;
    private int teamId;

    public TeamMemberDTO() {
    }

    public TeamMemberDTO(String memberName, String memberAvatar, String email, String phone) {
        this.memberName = memberName;
        this.memberAvatar = memberAvatar;
        this.email = email;
        this.phone = phone;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
    
    
}
