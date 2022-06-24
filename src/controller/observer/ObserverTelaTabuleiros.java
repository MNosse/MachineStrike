package controller.observer;

import global.EnumTipoTerreno;

import java.util.HashMap;
import java.util.Vector;

public interface ObserverTelaTabuleiros {
    void mostrarTela();
    void ocultarTela();
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    void iniciarListaTerrenosCriacao(HashMap<String, EnumTipoTerreno> terrenos);
    void mudarEstadoEditarDeletar(boolean estado);
    void atualizarListaDeTabuleiros(Vector<String> vector);
}
