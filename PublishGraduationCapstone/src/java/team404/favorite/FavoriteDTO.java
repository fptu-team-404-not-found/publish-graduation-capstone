/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.favorite;

import java.io.Serializable;
import team404.project.ProjectDTO;
import team404.user.AccountDTO;

/**
 *
 * @author jike
 */
public class FavoriteDTO implements Serializable{
    private int favoriteId;
    private AccountDTO user;
    private ProjectDTO project;

    public FavoriteDTO() {
        favoriteId = 0;
        user = null;
        project = null;
    }

    public FavoriteDTO(int favoriteId, AccountDTO user, ProjectDTO project) {
        this.favoriteId = favoriteId;
        this.user = user;
        this.project = project;
    }
    
    /**
     * @return the favoriteId
     */
    public int getFavoriteId() {
        return favoriteId;
    }

    /**
     * @param favoriteId the favoriteId to set
     */
    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
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
