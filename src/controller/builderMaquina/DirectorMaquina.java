package controller.builderMaquina;

import global.EnumDirecao;
import model.Jogador;

public class DirectorMaquina {
    private ConstruirMaquina construirMaquina;

    public DirectorMaquina(ConstruirMaquina construirMaquina){
        this.construirMaquina = construirMaquina;
    }

    public void construir(Jogador jogador, int linha, int coluna, EnumDirecao direcaoAtual){
        construirMaquina.reset();
        construirMaquina.construirJogador(jogador);
        construirMaquina.construirVida();
        construirMaquina.construirLinha(linha);
        construirMaquina.construirColuna(coluna);
        construirMaquina.construirAtaque();
        construirMaquina.construirAlcance();
        construirMaquina.construirNome();
        construirMaquina.construirMovimento();
        construirMaquina.construirPontosVitoria();
        construirMaquina.construirTipo();
        construirMaquina.construirTras();
        construirMaquina.construirFrente();
        construirMaquina.construirDireita();
        construirMaquina.construirEsquerda();
        construirMaquina.construirDirecaoAtual(direcaoAtual);
    }
}
