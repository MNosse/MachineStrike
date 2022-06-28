package controller.observer;

//GLOBAL
import global.EnumTipoTerreno;

//JAVA
import java.util.Vector;
import java.util.HashMap;

public interface ObserverTelaTabuleiros {
    void mostrarTela();
    void ocultarTela();
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    void iniciarListaTerrenosCriacao(HashMap<String, EnumTipoTerreno> terrenos);
    void mudarEstadoEditarDeletar(boolean estado);
    void atualizarListaDeTabuleiros(Vector<String> vector);
}
