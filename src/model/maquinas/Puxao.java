package model.maquinas;

import global.Enum.EnumDirecao;
import global.Enum.EnumTipoMaquinas;
import global.Enum.EnumTipoTerreno;
import model.Jogador;
import model.Tabuleiro;
import model.Terreno;
import model.state.stateCorrer.StateCorrerInativo;
import model.state.stateMover.StateMoverInativo;

public class Puxao extends Maquina {
    
    public Puxao(int linha, int coluna, Jogador jogador) {
        super(10, linha, coluna, 4, 3, "Puxao", 2, jogador, 6, -1, EnumTipoMaquinas.PUXAO, 0, 1, 1);
    }
    
    @Override
    public void acaoAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        int linhaOutraMaquina = outraMaquina.getLinha();
        int colunaOutraMaquina = outraMaquina.getColuna();
        Terreno terrenoOutraMaquina = tabuleiro.getTerrenoPorPosicao(linhaOutraMaquina + "" + colunaOutraMaquina);
        Terreno terrenoDessaMaquina = tabuleiro.getTerrenoPorPosicao(linha + "" + coluna);
        int pontoDeAtaque = ataque;
        if(terrenoDessaMaquina.getTipo().equals(EnumTipoTerreno.PANTANO)) {
            pontoDeAtaque = pontoDeAtaque + 1;
        } else {
            pontoDeAtaque = pontoDeAtaque + terrenoDessaMaquina.getPontosDeCombate();
        }
        int pontoDeDefesa = terrenoOutraMaquina.getPontosDeCombate() + outraMaquina.getPontosEscudo(linha, coluna);
        if(linha == linhaOutraMaquina) {
            if(coluna < colunaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.LESTE)) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                    if(Math.abs(coluna - colunaOutraMaquina) > 1) {
                        outraMaquina.setColuna(colunaOutraMaquina - 1);
                    }
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                    if(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina + 1) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina + 1), tabuleiro);
                    } else if((colunaOutraMaquina + 1) <= 7) {
                        outraMaquina.setColuna(colunaOutraMaquina + 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            } else if(coluna > colunaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.OESTE)) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                    if(Math.abs(coluna - colunaOutraMaquina) > 1) {
                        outraMaquina.setColuna(colunaOutraMaquina + 1);
                    }
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                    if(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina - 1) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina - 1), tabuleiro);
                    } else if((colunaOutraMaquina - 1) >= 0) {
                        outraMaquina.setColuna(colunaOutraMaquina - 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            }
        } else if(coluna == colunaOutraMaquina) {
            if(linha < linhaOutraMaquina && direcaoAtual.getDirecaoAtual().equals((EnumDirecao.SUL))) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                    if(Math.abs(linha - linhaOutraMaquina) > 1) {
                        outraMaquina.setLinha(linhaOutraMaquina - 1);
                    }
                } else {
                    vida--;
                    if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina + 1, coluna) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina + 1, coluna), tabuleiro);
                    } else if((linhaOutraMaquina + 1) <= 7) {
                        outraMaquina.setLinha(linhaOutraMaquina + 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            } else if(linha > linhaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.NORTE)) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                    if(Math.abs(linha - linhaOutraMaquina) > 1) {
                        outraMaquina.setLinha(linhaOutraMaquina + 1);
                    }
                } else {
                    vida--;
                    if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina - 1, coluna) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina - 1, coluna), tabuleiro);
                    } else if((linhaOutraMaquina - 1) >= 0) {
                        outraMaquina.setLinha(linhaOutraMaquina - 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            }
        }
    }
    
    public void acaoMover(int novaLinha, int novaColuna, Terreno terrenoNaPosicao) {
        linha = novaLinha;
        coluna = novaColuna;
        moverAtual = new StateMoverInativo(this);
        correrAtual = new StateCorrerInativo(this);
    }
    
    @Override
    public String caminhoImagemDirecaoFixa() {
        return "src/images/Puxao-Norte.png";
    }
}

