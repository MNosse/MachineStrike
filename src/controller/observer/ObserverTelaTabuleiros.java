package controller.observer;

import global.Enum.EnumTipoTerreno;

import java.util.HashMap;
import java.util.Vector;

public interface ObserverTelaTabuleiros {
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    
    void iniciarListaTerrenosCriacao(HashMap<String, EnumTipoTerreno> terrenos);
    
    void mudarEstadoEditarDeletar(boolean estado);
    
    void atualizarListaDeTabuleiros(Vector<String> vector);
    
    void navegarParaOutraTela();
}
