package team404.semester;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SemesterDTO implements Serializable{
    private int semesterId;
    private String semesterName;

    public SemesterDTO() {
        semesterId = 0;
        semesterName = "";
    }

    public SemesterDTO(int semesterId, String semesterName) {
        this.semesterId = semesterId;
        this.semesterName = semesterName;
    }
    
    
    /**
     * @return the semesterId
     */
    public int getSemesterId() {
        return semesterId;
    }

    /**
     * @param semesterId the semesterId to set
     */
    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    /**
     * @return the semesterName
     */
    public String getSemesterName() {
        return semesterName;
    }

    /**
     * @param semesterName the semesterName to set
     */
    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    @Override
    public String toString() {
        return "SemesterDTO{" + "semesterId=" + semesterId + ", semesterName=" + semesterName + '}';
    }
    
}
