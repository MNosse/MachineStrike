package model;

//GLOBAL
import global.Enum.EnumJogador;

//JAVA
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashMap;

//MODEL
import model.state.stateJogadorAtivo.StateJogador1;
import model.state.stateJogadorAtivo.StateJogadorAtivo;

public class Jogo {
    private Tabuleiro tabuleiro;
    private LinkedHashMap<EnumJogador, Jogador> jogadores;
    private Set<Maquina> maquinasQueAtacaram;
    private StateJogadorAtivo jogadorAtivo;

    public EnumJogador nomeJogadorAtivo() {
        return jogadorAtivo.getJogadorAtivo();
    }

    public Jogador jogadorAtivo() {
        return jogadores.get(jogadorAtivo.getJogadorAtivo());
    }

    public Jogador jogadorDefensor() {
        if (jogadorAtivo().getNome().equals(EnumJogador.JOGADOR1)) {
            return jogadores.get(EnumJogador.JOGADOR2);
        } else {
            return jogadores.get(EnumJogador.JOGADOR1);
        }
    }

    public Jogo(Tabuleiro tabuleiro, LinkedHashMap<EnumJogador, Jogador> jogadores) {
        this.tabuleiro = tabuleiro;
        this.jogadores = jogadores;
        jogadorAtivo = new StateJogador1(this);
        maquinasQueAtacaram = new HashSet<>();
    }

    public void passarTurno() {
        verificarMaquinasJogadorAtivo();
        verificarMaquinasJogadorDefensor();
        for (Maquina maquina : jogadorAtivo().getMaquinas()) {
            maquina.resetarMaquina();
        }
        maquinasQueAtacaram.clear();
        jogadorAtivo.passarTurno();
    }

    public void verificarMaquinasJogadorAtivo() {
        for (int i = jogadorAtivo().getMaquinas().size()-1; i >= 0; i--) {
            if (jogadorAtivo().getMaquinas().get(i).getVida() < 0) {
                jogadorDefensor().addPontosVitoria(jogadorAtivo().getMaquinas().get(i).getPontosVitoria());
                tabuleiro.removerMaquina(jogadorAtivo().getMaquinas().get(i));
                jogadorAtivo().removeMaquina(jogadorAtivo().getMaquinas().get(i));
                if (jogadorDefensor().getPontosVitoria() >= 7 | jogadorAtivo().getMaquinas().size() == 0) {
                    System.out.println("abrir option pane falando que acabaou");
                }
            }
        }
    }

    public void verificarMaquinasJogadorDefensor() {
        for (int i = jogadorDefensor().getMaquinas().size()-1; i >= 0; i--) {
            if (jogadorDefensor().getMaquinas().get(i).getVida() < 0) {
                jogadorAtivo().addPontosVitoria(jogadorDefensor().getMaquinas().get(i).getPontosVitoria());
                tabuleiro.removerMaquina(jogadorDefensor().getMaquinas().get(i));
                jogadorDefensor().removeMaquina(jogadorDefensor().getMaquinas().get(i));
                if (jogadorAtivo().getPontosVitoria() >= 7 | jogadorDefensor().getMaquinas().size() == 0) {
                    System.out.println("abrir option pane falando que acabaou");
                }
            }
        }
    }

    public void addMaquinaQueAtacou(Maquina maquina) {
        maquinasQueAtacaram.add(maquina);
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

    public Set<Maquina> getMaquinasQueAtacaram() {
        return maquinasQueAtacaram;
    }

    public void setMaquinasQueAtacaram(Set<Maquina> maquinasQueAtacaram) {
        this.maquinasQueAtacaram = maquinasQueAtacaram;
    }
}
