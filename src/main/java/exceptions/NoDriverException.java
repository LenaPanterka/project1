package exceptions;

public class NoDriverException extends Exception{
    public NoDriverException(){
        super("Driver wasn't found");
    }
}
