package controller.observer;

//GLOBAL
import global.EnumTipoTerreno;

//JAVA
import java.util.Vector;
import java.util.HashMap;

//VIEW
import view.Tela;

public interface ObserverTelaTabuleiros {
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    void iniciarListaTerrenosCriacao(HashMap<String, EnumTipoTerreno> terrenos);
    void mudarEstadoEditarDeletar(boolean estado);
    void atualizarListaDeTabuleiros(Vector<String> vector);
    void navegarParaOutraTela(Tela outraTela);
}
