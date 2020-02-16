package piecetour.board.exception;

/**
 *
 * Exception used during validation of invalid args
 *
 * @author Ricardo Pereira Ramalho
 */
public class InvalidPositionException extends Exception {

    /**
     * Default class constructor
     *
     * @param message
     */
    public InvalidPositionException(String message){
        super(message);
    }
}
