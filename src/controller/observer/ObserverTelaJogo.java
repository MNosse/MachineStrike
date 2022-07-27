package controller.observer;

import global.Enum.EnumTipoTerreno;

import java.util.HashMap;
import java.util.Set;

public interface ObserverTelaJogo {
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    
    void desenharQuadrado(String posicao, String caminhoImagem);
    
    void desenharQuadrados(HashMap<String, String> maquinas);
    
    void apagarCamposSelecionados(Set<String> posicoes);
    
    void desenharCamposSelecionados(Set<String> posicoes);
    
    void mudarEstadoBtnAtacar(boolean estado);
    
    void mudarEstadoBtnSobrecarregar(boolean estado);
    
    void mudarEstadoBtnMover(boolean estado);
    
    void mudarEstadoBtnCorrer(boolean estado);
    
    void mudarEstadoBtnGirar(boolean estado);
    
    void desativarBotoes();
    
    void atualizarCardMaquinaAtacante(HashMap<String, String> informacoes);
    
    void atualizarLblJogadorAtivo(String nome);
    
    void anunciarGanhador(String nome);
    
    void navegarParaOutraTela();
}
