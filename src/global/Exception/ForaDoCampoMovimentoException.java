package global.Exception;

public class ForaDoCampoMovimentoException extends RuntimeException {
    
    public ForaDoCampoMovimentoException() {
        super("Selecione um terreno do campo de movimento");
    }
    
}
