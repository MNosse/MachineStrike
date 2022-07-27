package global.Exception;

public class MinimoDeMovimentoException extends RuntimeException {
    
    public MinimoDeMovimentoException() {
        super("Voce deve mover duas maquinas para poder encerrar seu turno");
    }
    
    public MinimoDeMovimentoException(String message) {
        super(message);
    }
    
}
