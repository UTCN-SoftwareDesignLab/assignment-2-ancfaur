package bookstoreApp.service.author;

public class LimittedStockException extends Exception {

    public LimittedStockException(String message){
        super(message);
    }
}
