package controller.observer;

//GLOBAL
import global.EnumTipoTerreno;

//JAVA
import java.util.HashMap;

//VIEW
import view.Tela;

public interface ObserverTelaConfigurarJogo {
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    void desenharMaquina(String caminhoImagem, String posicao);
    void desenharBloqueadosOuVazios(HashMap<String, String> valores);
    void navegarParaOutraTela(Tela outraTela);
}
