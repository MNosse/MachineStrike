package controller.observer;

//GLOBAL
import global.Enum.EnumTipoTerreno;

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

    void apagarCampoDeMovimento(Set<String> posicoes);
    void desenharCampoDeMovimento(Set<String> posicoes);

    void mudarEstadoBtnAtacar(boolean estado);
    void mudarEstadoBtnSobrecarregar(boolean estado);
    void mudarEstadoBtnMover(boolean estado);
    void mudarEstadoBtnCorrer(boolean estado);
    void mudarEstadoBtnGirar(boolean estado);
    void desativarBotoes();

    void atualizarCardMaquinaAtacante(HashMap<String, String> informacoes);
    void atualizarLblJogadorAtivo(String nome);
    void navegarParaOutraTela(Tela outraTela);
}
