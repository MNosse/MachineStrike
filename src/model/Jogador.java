package model;

//JAVA
import java.util.*;

//GLOBAL
import global.EnumJogador;

public class Jogador {
    private EnumJogador nome;
    private int pontosVitoria;
    private boolean estaJogando;
    private List<Maquina> maquinas;

    public Jogador(EnumJogador nome) {
        pontosVitoria = 0;
        this.nome = nome;
        estaJogando = false;
        maquinas = new ArrayList<>();
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

    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public int contagemPVMaquinas() {
        int totalPV = 0;
        for (Maquina maquina : maquinas) {
            totalPV+=maquina.getPontosVitoria();
        }
        return totalPV;
    }

    public Maquina getMaquinaPorPosicao (int linha, int coluna) {
        for (Maquina maquina : maquinas) {
            if (maquina.getLinha() == linha && maquina.getColuna() == coluna) {
                return maquina;
            }
        }
        return null;
    }

    public boolean podeAdicionarMaquinas(Maquina maquina) {
       if (maquinas.size() < 10) {
           if (contagemPVMaquinas() + maquina.getPontosVitoria() <= 10) {
               return true;
           }
       }
       return false;
    }

    public boolean podeAdicionarMaquinaNaPosicao(int linha, int coluna) {
        for (Maquina maquina : maquinas) {
            if (maquina.getLinha() == linha && maquina.getColuna() == coluna) {
                return false;
            }
        }
        return true;
    }

    public boolean podeSubstituirMaquinas(Maquina antiga, Maquina nova) {
        if ((contagemPVMaquinas() - antiga.getPontosVitoria()) + nova.getPontosVitoria() <= 10) {
            return true;
        }
        return false;
    }

    public void addMaquinas(Maquina maquina) {
        if (podeAdicionarMaquinas(maquina)) {
            maquinas.add(maquina);
        }
    }

    public void removeMaquina(Maquina maquina) {
        maquinas.remove(maquina);
    }

    public void removeMaquina(int linha, int coluna) {
        for (Maquina maquina : maquinas) {
            if (maquina.getLinha() == linha && maquina.getColuna() == coluna) {
                maquinas.remove(maquina);
                break;
            }
        }
    }
}
