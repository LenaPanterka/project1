package exceptions;

public class BrowserNotSupprotMineException extends Exception{
    public BrowserNotSupprotMineException(String s){
        super("Not supported browser");
    }
}
