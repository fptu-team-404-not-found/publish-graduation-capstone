/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.teammember;

import java.util.List;

/**
 *
 * @author Admin
 */
public class TeamMemberList {
    List <TeamMemberDTO> list;

    public List<TeamMemberDTO> getList() {
        return list;
    }

    public void setList(List<TeamMemberDTO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TeamMemberList{" + "list=" + list + '}';
    }
}
