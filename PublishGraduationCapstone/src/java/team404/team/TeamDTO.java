/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.team;

import java.io.Serializable;

/**
 *
 * @author jike
 */
public class TeamDTO implements Serializable{
    private int teamId;
    private String teamName;

    public TeamDTO() {
    }

    public TeamDTO(int teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
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
     * @return the teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    
}
