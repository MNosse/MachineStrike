package controller.observer;

import global.EnumTipoTerreno;

import java.util.HashMap;

public interface ObserverTelaConfigurarJogo {
    void mostrarTela();
    void ocultarTela();
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
}
