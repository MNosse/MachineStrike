package model.maquinas;

import global.Enum.EnumDirecao;
import global.Enum.EnumTipoMaquinas;
import model.Jogador;
import model.Tabuleiro;
import model.Terreno;

public class Ariete2 extends Maquina {
    
    public Ariete2(int linha, int coluna, Jogador jogador) {
        super(5, linha, coluna, 2, 2, "Ariete 2", 2, jogador, 2, 0, EnumTipoMaquinas.ARIETE, 1, -1, -1);
    }
    
    public void acaoAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        int linhaOutraMaquina = outraMaquina.getLinha();
        int colunaOutraMaquina = outraMaquina.getColuna();
        Terreno terrenoOutraMaquina = tabuleiro.getTerrenoPorPosicao(linhaOutraMaquina + "" + colunaOutraMaquina);
        Terreno terrenoDessaMaquina = tabuleiro.getTerrenoPorPosicao(linha + "" + coluna);
        int pontoDeAtaque = ataque + terrenoDessaMaquina.getPontosDeCombate();
        int pontoDeDefesa = terrenoOutraMaquina.getPontosDeCombate() + outraMaquina.getPontosEscudo(linha, coluna);
        if(linha == linhaOutraMaquina) {
            if(coluna < colunaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.LESTE)) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
                if(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina + 1) != null) {
                    encadearAtaque(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina + 1), tabuleiro);
                } else if((colunaOutraMaquina + 1) <= 7) {
                    outraMaquina.setColuna(colunaOutraMaquina + 1);
                    coluna = colunaOutraMaquina;
                } else {
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
            } else if(coluna > colunaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.OESTE)) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
                if(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina - 1) != null) {
                    encadearAtaque(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina - 1), tabuleiro);
                } else if((colunaOutraMaquina - 1) >= 0) {
                    outraMaquina.setColuna(colunaOutraMaquina - 1);
                    coluna = colunaOutraMaquina;
                } else {
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
            }
        } else if(coluna == colunaOutraMaquina) {
            if(linha < linhaOutraMaquina && direcaoAtual.getDirecaoAtual().equals((EnumDirecao.SUL))) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
                if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina + 1, coluna) != null) {
                    encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina + 1, coluna), tabuleiro);
                } else if((linhaOutraMaquina + 1) <= 7) {
                    outraMaquina.setLinha(linhaOutraMaquina + 1);
                    linha = linhaOutraMaquina;
                } else {
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
            } else if(linha > linhaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.NORTE)) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
                if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina - 1, coluna) != null) {
                    encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina - 1, coluna), tabuleiro);
                } else if((linhaOutraMaquina - 1) >= 0) {
                    outraMaquina.setLinha(linhaOutraMaquina - 1);
                    linha = linhaOutraMaquina;
                } else {
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
            }
        }
    }
    
    @Override
    public String caminhoImagemDirecaoFixa() {
        return "src/images/Ariete2-Norte.png";
    }
}
