package team404.sensitiveword;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sensitiveWord")
public class SensitiveWordDTO implements Serializable {

    private int wordId;
    private String bannedWord;

    public SensitiveWordDTO() {
        wordId = 0;
        bannedWord = "";
    }

    public SensitiveWordDTO(int wordId, String bannedWord) {
        this.wordId = wordId;
        this.bannedWord = bannedWord;
    }

    /**
     * @return the wordId
     */
    public int getWordId() {
        return wordId;
    }

    /**
     * @param wordId the wordId to set
     */
    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    /**
     * @return the bannedWord
     */
    public String getBannedWord() {
        return bannedWord;
    }

    /**
     * @param bannedWord the bannedWord to set
     */
    public void setBannedWord(String bannedWord) {
        this.bannedWord = bannedWord;
    }

    @Override
    public String toString() {
        return "SensitiveWordDTO{" + "wordId=" + wordId + ", bannedWord=" + bannedWord + '}';
    }
}
