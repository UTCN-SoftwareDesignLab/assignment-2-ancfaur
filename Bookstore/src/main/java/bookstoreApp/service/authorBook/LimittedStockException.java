package bookstoreApp.service.authorBook;

public class LimittedStockException extends Exception {

    public LimittedStockException(String message){
        super(message);
    }
}
