package controller.observer;

//GLOBAL
import global.EnumTipoTerreno;
import view.Tela;

//JAVA
import java.util.HashMap;

public interface ObserverTelaConfigurarJogo {
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    void desenharMaquina(String caminhoImagem, String posicao);
    void desenharBloquadoOuVazio(String nomeImagem, String posicao);
    void navegarParaOutraTela(Tela outraTela);
}
