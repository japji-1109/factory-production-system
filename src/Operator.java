public class Operator{
    private int operatorId;
    private String operatorName;
    private String skill;

    public Operator(int operatorId, String operatorName, String skill){
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.skill = skill;
    }

    public int getOperatorId(){
        return operatorId;
    }

    public String getOperatorName(){
        return operatorName;
    }

    public String getSkill(){
        return skill;
    }


    public String toString(){
        return "Operator ID: " + operatorId + ", Name: " + operatorName + ", Skill: " + skill;
    }
}