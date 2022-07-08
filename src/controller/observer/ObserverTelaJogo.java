package controller.observer;

//GLOBAL
import global.EnumTipoTerreno;

//JAVA
import java.util.HashMap;
import java.util.Set;

//VIEW
import view.Tela;

public interface ObserverTelaJogo {
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    void desenharQuadrado(String posicao);
    void desenharQuadrado(String posicao, String caminhoImagem);
    void desenharQuadrados(HashMap<String, String> maquinas);
    void desenharQuadrados(Set<String> posicoes);
    void desenharQuadradosSelecionados(Set<String> antigos, Set<String> novos);
    void atualizarCardMaquinaAtacante(HashMap<String, String> informacoes);
    void navegarParaOutraTela(Tela outraTela);
}
