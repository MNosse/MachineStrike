package global.Exception;

public class MaquinaEmTerrenoInvalidoException extends RuntimeException {
    
    public MaquinaEmTerrenoInvalidoException() {
        super("Apenas maquinas do tipo Mergulho podem ser posicionadas no terreno de Abismo");
    }
    
}
