package global.Exception;

public class JaMovimentouException extends RuntimeException {
    
    public JaMovimentouException() {
        super("Essa maquina ja realizou um movimento");
    }
    
}
