package model;

//CONTROLLER
import model.state.stateJogadorAtivo.StateJogador1;
import model.state.stateJogadorAtivo.StateJogadorAtivo;

//GLOBAL
import global.EnumJogador;

//JAVA
import java.util.LinkedHashMap;

public class Jogo {
    private Tabuleiro tabuleiro;
    private LinkedHashMap<EnumJogador, Jogador> jogadores;
    private StateJogadorAtivo jogadorAtivo;

    public EnumJogador nomeJogadorAtivo() {
        return jogadorAtivo.getJogadorAtivo();
    }

    public Jogador jogadorAtivo() {
        return jogadores.get(jogadorAtivo.getJogadorAtivo());
    }

    public Jogo(Tabuleiro tabuleiro, LinkedHashMap<EnumJogador, Jogador> jogadores) {
        this.tabuleiro = tabuleiro;
        this.jogadores = jogadores;
        jogadorAtivo = new StateJogador1();
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public LinkedHashMap<EnumJogador, Jogador> getJogadores() {
        return jogadores;
    }

    public Jogador getJogador(EnumJogador enumJogador) {
        return jogadores.get(enumJogador);
    }

    private StateJogadorAtivo getJogadorAtivo() {
        return jogadorAtivo;
    }

    public void setJogadorAtivo(StateJogadorAtivo jogadorAtivo) {
        this.jogadorAtivo = jogadorAtivo;
    }
}
