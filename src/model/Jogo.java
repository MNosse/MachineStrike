package model;

import global.Enum.EnumJogador;
import model.maquinas.Maquina;
import model.state.stateJogadorAtivo.StateJogador1;
import model.state.stateJogadorAtivo.StateJogadorAtivo;
import model.visitor.Visitor;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class Jogo {
    private Tabuleiro tabuleiro;
    private LinkedHashMap<EnumJogador, Jogador> jogadores;
    private Set<Maquina> maquinasQueRealizaramAcoes;
    private int contagemMovimentos = 0;
    private StateJogadorAtivo jogadorAtivo;
    
    public Jogo(Tabuleiro tabuleiro, LinkedHashMap<EnumJogador, Jogador> jogadores) {
        this.tabuleiro = tabuleiro;
        this.jogadores = jogadores;
        jogadorAtivo = new StateJogador1(this);
        maquinasQueRealizaramAcoes = new HashSet<>();
    }
    
    public EnumJogador nomeJogadorAtivo() {
        return jogadorAtivo.getJogadorAtivo();
    }
    
    public Jogador jogadorAtivo() {
        return jogadores.get(jogadorAtivo.getJogadorAtivo());
    }
    
    public Jogador jogadorDefensor() {
        if(jogadorAtivo().getEnumNome().equals(EnumJogador.JOGADOR1)) {
            return jogadores.get(EnumJogador.JOGADOR2);
        } else {
            return jogadores.get(EnumJogador.JOGADOR1);
        }
    }
    
    public void passarTurno() {
        for(Maquina maquina : jogadorAtivo().getMaquinas()) {
            maquina.resetarMaquina();
        }
        maquinasQueRealizaramAcoes.clear();
        contagemMovimentos = 0;
        jogadorAtivo.passarTurno();
    }
    
    public boolean accept(Visitor visitor) {
        return visitor.visit(this);
    }
    
    public void addMaquinaQueRealizouAcao(Maquina maquina) {
        maquinasQueRealizaramAcoes.add(maquina);
    }
    
    public void addContagemMovimentos() {
        contagemMovimentos++;
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
    
    public Set<Maquina> getMaquinasQueRealizaramAcoes() {
        return maquinasQueRealizaramAcoes;
    }
    
    public void setMaquinasQueRealizaramAcoes(Set<Maquina> maquinasQueRealizaramAcoes) {
        this.maquinasQueRealizaramAcoes = maquinasQueRealizaramAcoes;
    }
    
    public void setJogadores(LinkedHashMap<EnumJogador, Jogador> jogadores) {
        this.jogadores = jogadores;
    }
    
    public int getContagemMovimentos() {
        return contagemMovimentos;
    }
    
    public void setContagemMovimentos(int contagemMovimentos) {
        this.contagemMovimentos = contagemMovimentos;
    }
}
