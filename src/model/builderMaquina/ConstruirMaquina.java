package model.builderMaquina;

//MODEL
import model.Jogador;
import model.Maquina;
import model.state.stateMover.StateMoverAtivo;
import model.state.stateCorrer.StateCorrerAtivo;
import model.state.stateDirecao.StateDirecaoNorte;

public abstract class ConstruirMaquina {

    protected Maquina maquina;

    public void construirJogador(Jogador jogador) {
        maquina.setJogador(jogador);
    }

    public void construirVida() {}

    public void construirLinha(int linha) {
        maquina.setLinha(linha);
    }

    public void construirColuna(int coluna) {
        maquina.setColuna(coluna);
    }

    public void construirAtaque() {}

    public void construirAlcance() {}

    public void construirNome() {}

    public void construirMovimento() {}

    public void construirPontosVitoria() {}

    public void construirTipo() {}

    public void construirTras() {}

    public void construirFrente() {}

    public void construirDireita() {}

    public void construirEsquerda() {}

    public void construirDirecaoAtual() {
        maquina.setDirecaoAtual(new StateDirecaoNorte(maquina));
    }

    public void construirMoverAtual() {
        maquina.setMoverAtual(new StateMoverAtivo(maquina));
    }

    public void construirCorrerAtual() {
        maquina.setCorrerAtual(new StateCorrerAtivo(maquina));
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void reset() {
        maquina = new Maquina();
    }
}
