package exceptions;

public class MachineNotAvailableException extends Exception{
    public MachineNotAvailableException(String message) {
        super(message);
    }
}