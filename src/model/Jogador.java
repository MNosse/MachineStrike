package model;

//JAVA
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

//GLOBAL
import global.EnumJogador;

public class Jogador {
    private EnumJogador nome;
    private int pontosVitoria;
    private boolean estaJogando;
    private Map<String, Maquina> maquinas;

    public Jogador(EnumJogador nome) {
        pontosVitoria = 0;
        this.nome = nome;
        estaJogando = false;
        maquinas = new HashMap();
    }

    public EnumJogador getNome() {
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

    public int contagemPVMaquinas() {
        int totalPV = 0;
        Set<String> chaves = maquinas.keySet();
        for (String chave : chaves) {
            totalPV+=maquinas.get(chave).getPontosVitoria();
        }
        return totalPV;
    }

    public boolean podeAdicionarMaquinas(Maquina maquina) {
       if (maquinas.size() < 10) {
           if (contagemPVMaquinas() + maquina.getPontosVitoria() <= 10) {
               return true;
           }
       }
       return false;
    }

    public boolean podeSubstituirMaquinas(Maquina antiga, Maquina nova) {
        if ((contagemPVMaquinas() - antiga.getPontosVitoria()) + nova.getPontosVitoria() <= 10) {
            return true;
        }
        return false;
    }

    public void addMaquinas(String posicao, Maquina maquina) {
        if (podeAdicionarMaquinas(maquina)) {
            maquinas.put(posicao, maquina);
        }
    }

    public void removeMaquinas(String posicao) {
        maquinas.remove(posicao);
    }
}
