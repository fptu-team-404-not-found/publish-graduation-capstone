package team404.sensitiveword;

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
import team404.utils.DBHelpers;

public class SensitiveWordDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    private List<SensitiveWordDTO> sensitiveWordList;

    public List<SensitiveWordDTO> getSensitiveWordList() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From Sensitive_word ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<SensitiveWordDTO> list = new ArrayList<>();
                while (rs.next()) {
                    int wordId = rs.getInt("wordID");
                    String bannedWord = rs.getNString("banned_word");
                    SensitiveWordDTO dto = new SensitiveWordDTO(wordId, bannedWord);
                    list.add(dto);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<String> getBannedWordList() {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select banned_word "
                        + "From Sensitive_word ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<String> list = new ArrayList<>();
                while (rs.next()) {
                    String word = rs.getNString("banned_word");
                    list.add(word);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public SensitiveWordDTO checkExist(String banWord) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From Sensitive_word "
                        + "Where banned_word = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, banWord);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String banWord2 = rs.getString("banned_word");
                    SensitiveWordDTO dto = new SensitiveWordDTO();
                    dto.setBannedWord(banWord2);
                    return dto;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void insertSensitiveWord(String banWord) {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Sensitive_word "
                        + "Values(?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, banWord);
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SensitiveWordDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
