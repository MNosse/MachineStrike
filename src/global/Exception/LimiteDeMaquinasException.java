package global.Exception;

public class LimiteDeMaquinasException extends RuntimeException {
    
    public LimiteDeMaquinasException() {
        super("Voce atingiu o limite de numero de maquinas, PV, ou repeticao de maquinas");
    }
    
}
