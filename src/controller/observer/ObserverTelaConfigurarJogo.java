package controller.observer;

import global.Enum.EnumTipoTerreno;

import java.util.HashMap;

public interface ObserverTelaConfigurarJogo {
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    
    void desenharMaquina(String caminhoImagem, String posicao);
    
    void desenharBloqueadosOuVazios(HashMap<String, String> valores);
    
    void navegarParaOutraTela();
}
