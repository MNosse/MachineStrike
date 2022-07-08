package controller.singleton;

//JAVA
import java.util.LinkedHashMap;

//GLOBAL
import global.EnumJogador;

//MODEL
import model.Jogador;
import model.Tabuleiro;

public class SingletonConfiguracaoJogo {
    private static SingletonConfiguracaoJogo instancia;
    private Tabuleiro tabuleiro;
    private LinkedHashMap<EnumJogador, Jogador> jogadores;

    private SingletonConfiguracaoJogo() {}

    public synchronized static SingletonConfiguracaoJogo getInstancia() {
        if (instancia == null) {
            instancia = new SingletonConfiguracaoJogo();
        }
        return instancia;
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

    public void setJogadores(LinkedHashMap<EnumJogador, Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}