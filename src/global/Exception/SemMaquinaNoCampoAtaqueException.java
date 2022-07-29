package global.Exception;

public class SemMaquinaNoCampoAtaqueException extends RuntimeException {
    
    public SemMaquinaNoCampoAtaqueException() {
        super("Nao tem maquinas no seu campo de ataque");
    }
    
}
