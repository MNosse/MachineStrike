package model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private int pontosVitoria;
    private String nome;
    private boolean estaJogando;
    private List<Maquina> maquinas;

    public Jogador(String nome) {
        pontosVitoria = 0;
        this.nome = nome;
        estaJogando = false;
        maquinas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getPontosVitoria() {
        return pontosVitoria;
    }

    public void setPontosVitoria(int pontosVitoria) {
        this.pontosVitoria = pontosVitoria;
    }

    public boolean isEstaJogando() {
        return estaJogando;
    }

    public void setEstaJogando(boolean estaJogando) {
        this.estaJogando = estaJogando;
    }

    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<Maquina> maquinas) {
        this.maquinas = maquinas;
    }
}
