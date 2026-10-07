public class Machine{
    private int machineId;
    private String machineName;
    private boolean available;

    public Machine(int machineId, String machineName){
        this.machineId = machineId;
        this.machineName = machineName;
        this.available = true;
    }

    public int getMachineId(){
        return machineId;
    }

    public String getMachineName(){
        return machineName;
    }

    public boolean isAvailable(){
        return available;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }

    public String toString(){
        return "Machine ID: " + machineId + ", Name: " + machineName + ", Available: " + available;
    }
}