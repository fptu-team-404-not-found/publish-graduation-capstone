package team404.team;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import team404.project.ProjectDAO;
import team404.utils.DBHelpers;

public class TeamDAO implements Serializable {

    private List<TeamDTO> teamList;
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<TeamDTO> getTeamList() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From Team ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<TeamDTO> list = new ArrayList<>();
                while (rs.next()) {
                    TeamDTO dto = new TeamDTO(rs.getInt("TeamId"), rs.getString("TeamName"));
                    list.add(dto);
                }
                return list;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public TeamDTO getTeam(String projectId) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From Project p inner join Team t "
                        + "On p.TeamID = t.TeamID "
                        + "Where p.ProjectId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, projectId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    TeamDTO dto = new TeamDTO(rs.getInt("TeamID"), rs.getNString("TeamName"));
                    return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TeamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TeamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
