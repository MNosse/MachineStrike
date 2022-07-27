package global.Exception;

public class MinimoMaquinasException extends RuntimeException {
    
    public MinimoMaquinasException() {
        super("Devem haver no minimo duas maquinas por jogador");
    }
    
}
