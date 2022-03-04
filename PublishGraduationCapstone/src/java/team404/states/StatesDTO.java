package team404.states;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "states")
public class StatesDTO {

    private int stateId;
    private String stateName;

    public StatesDTO() {
        stateId = 0;
        stateName = "";
    }

    public StatesDTO(int stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
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

    /**
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "StatesDTO{" + "stateId=" + stateId + ", stateName=" + stateName + '}';
    }
}
