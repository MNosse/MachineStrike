package global.Exception;

public class JaCorreuException extends RuntimeException {
    
    public JaCorreuException() {
        super("Essa maquina ja realizou a acao de corrida");
    }
    
}
