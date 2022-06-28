package model;

//JAVA
import java.util.Map;
import java.util.HashMap;

public class Jogador {
    private String nome;
    private int pontosVitoria;
    private boolean estaJogando;
    private Map<String, Maquina> maquinas;

    public Jogador(String nome) {
        pontosVitoria = 0;
        this.nome = nome;
        estaJogando = false;
        maquinas = new HashMap();
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

    public Map<String, Maquina> getMaquinas() {
        return maquinas;
    }

    public void addMaquinas(String posicao, Maquina maquina) {
        maquinas.put(posicao, maquina);
    }

    public void removeMaquinas(String posicao) {
        maquinas.remove(posicao);
    }
}
