package controller.builderMaquina;

import global.EnumDirecao;
import model.Jogador;
import model.Maquina;

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

    public void construirDirecaoAtual(EnumDirecao direcaoAtual) {
        maquina.setDirecaoAtual(direcaoAtual);
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void reset() {
        maquina = new Maquina();
    }
}
