package controller.observer;

//GLOBAL
import global.EnumTipoTerreno;
import view.Tela;

//JAVA
import java.util.Vector;
import java.util.HashMap;

public interface ObserverTelaTabuleiros {
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    void iniciarListaTerrenosCriacao(HashMap<String, EnumTipoTerreno> terrenos);
    void mudarEstadoEditarDeletar(boolean estado);
    void atualizarListaDeTabuleiros(Vector<String> vector);
    void navegarParaOutraTela(Tela outraTela);
}
