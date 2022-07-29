package global.Exception;

public class JaSobrecarregouException extends RuntimeException {
    
    public JaSobrecarregouException() {
        super("Essa maquina ja sobrecarregou");
    }
    
}
