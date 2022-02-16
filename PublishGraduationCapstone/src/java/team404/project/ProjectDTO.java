/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.project;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import team404.team.TeamDTO;


/**
 *
 * @author jike
 */
@XmlRootElement
public class ProjectDTO implements Serializable{
    private String projectId;
    private String projectName;
    private String introductionContent;
    private String details;
    private String semester;
    private String productUrl;
    private String createDate;
    private int viewNumber;
    private String authorName;
    private String note;
    private String projectAva;
//    private String teamName;
    private int teamId;
    private int stateId;

    public ProjectDTO() {
    }
    public ProjectDTO(int viewNumber){
        this.viewNumber = viewNumber;
    }
    public ProjectDTO(String projectId, String projectName, String introductionContent,String projectAva) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.introductionContent = introductionContent;
        this.projectAva = projectAva;
//        this.teamName = teamName;
    }
    public ProjectDTO(String projectId, String projectName,String projectAva) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectAva = projectAva;
    }
    public ProjectDTO(String projectId, String projectName, String introductionContent, String details, String semester, String productUrl, String createDate, int viewNumber, String authorName, String note, String projectAva, int teamId, int stateId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.introductionContent = introductionContent;
        this.details = details;
        this.semester = semester;
        this.productUrl = productUrl;
        this.createDate = createDate;
        this.viewNumber = viewNumber;
        this.authorName = authorName;
        this.note = note;
        this.projectAva = projectAva;
//        this.teamName = teamName;
        this.teamId = teamId;
        this.stateId = stateId;
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
     * @return the productUrl
     */
    public String getProductUrl() {
        return productUrl;
    }

    /**
     * @param productUrl the productUrl to set
     */
    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
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

//    /**
//     * @return the teamName
//     */
//    public String getTeamName() {
//        return teamName;
//    }
//
//    /**
//     * @param teamName the teamName to set
//     */
//    public void setTeamName(String teamName) {
//        this.teamName = teamName;
//    }

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

    
    
    
}
