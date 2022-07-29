package controller.observer;

import global.Enum.EnumTipoTerreno;

import java.util.HashMap;
import java.util.Set;

public interface ObserverTelaJogo {
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
    
    void redesenharMaquinas(HashMap<String, String> maquinasAtivas, HashMap<String, String> maquinasInativas);
    
    void desenharCampoDeAtaque(HashMap<String, String> maquinas);
    
    void desenharCamposDeMovimento(Set<String> posicoes);
    
    void mudarEstadoBtnAtacar(boolean estado);
    
    void mudarEstadoBtnSobrecarregar(boolean estado);
    
    void mudarEstadoBtnMover(boolean estado);
    
    void mudarEstadoBtnCorrer(boolean estado);
    
    void mudarEstadoBtnGirar(boolean estado);
    
    void desativarBotoes();
    
    void atualizarCardMaquinaAtacante(HashMap<String, String> informacoes);
    
    void atualizarCardMaquinaDefensora(HashMap<String, String> informacoes);
    
    void atualizarLblJogadorAtivo(String nome);
    
    void anunciarGanhador(String nome);
    
    void navegarParaOutraTela();
}
