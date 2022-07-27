package global.Exception;

public class LimiteDeMaquinasException extends RuntimeException {
    
    public LimiteDeMaquinasException() {
        super("Voce atingiu o limite de numero de maquinas e/ou PV");
    }
    
}
