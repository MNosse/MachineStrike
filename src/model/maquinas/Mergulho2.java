package model.maquinas;

import global.Enum.EnumDirecao;
import global.Enum.EnumTipoMaquinas;
import model.Jogador;
import model.Tabuleiro;
import model.Terreno;

import java.util.List;

public class Mergulho2 extends Maquina {
    
    public Mergulho2(int linha, int coluna, Jogador jogador) {
        super(9, linha, coluna, 3, 3, "Mergulho 2", 2, jogador, 5, -1, EnumTipoMaquinas.MERGULHO, 1, 0, 0);
    }
    
    @Override
    public void acaoAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        int linhaOutraMaquina = outraMaquina.getLinha();
        int colunaOutraMaquina = outraMaquina.getColuna();
        Terreno terrenoOutraMaquina = tabuleiro.getTerrenoPorPosicao(linhaOutraMaquina + "" + colunaOutraMaquina);
        Terreno terrenoEstaMaquina = tabuleiro.getTerrenoPorPosicao(linha + "" + coluna);
        int pontoDeAtaque = ataque + 1;
        if(terrenoEstaMaquina.getPontosDeCombate() > 0) {
            pontoDeAtaque += terrenoEstaMaquina.getPontosDeCombate();
        }
        int pontoDeDefesa = terrenoOutraMaquina.getPontosDeCombate() + outraMaquina.getPontosEscudo(linha, coluna);
        if(linha == linhaOutraMaquina) {
            if(coluna < colunaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.LESTE)) {
                coluna = colunaOutraMaquina - 1;
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                    if(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina + 1) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina + 1), tabuleiro);
                        coluna = colunaOutraMaquina;
                    } else if((colunaOutraMaquina + 1) <= 7) {
                        outraMaquina.setColuna(colunaOutraMaquina + 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            } else if(coluna > colunaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.OESTE)) {
                coluna = colunaOutraMaquina + 1;
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                    if(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina - 1) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina - 1), tabuleiro);
                        coluna = colunaOutraMaquina;
                    } else if((colunaOutraMaquina - 1) >= 0) {
                        outraMaquina.setColuna(colunaOutraMaquina - 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            }
        } else if(coluna == colunaOutraMaquina) {
            if(linha < linhaOutraMaquina && direcaoAtual.getDirecaoAtual().equals((EnumDirecao.SUL))) {
                linha = linhaOutraMaquina - 1;
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                    if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina + 1, coluna) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina + 1, coluna), tabuleiro);
                        linha = linhaOutraMaquina - 1;
                    } else if((linhaOutraMaquina + 1) <= 7) {
                        outraMaquina.setLinha(linhaOutraMaquina + 1);
                        linha = linhaOutraMaquina;
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                        linha = linhaOutraMaquina - 1;
                    }
                }
                
            } else if(linha > linhaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.NORTE)) {
                linha = linhaOutraMaquina + 1;
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                    if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina - 1, coluna) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina - 1, coluna), tabuleiro);
                        linha = linhaOutraMaquina;
                    } else if((linhaOutraMaquina - 1) >= 0) {
                        outraMaquina.setLinha(linhaOutraMaquina - 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            }
        }
    }
    
    @Override
    public boolean podeMover(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) {
        boolean continua = true;
        for(Maquina maquina : maquinasEmJogo) {
            if(maquina.getLinha() == novaLinha && maquina.getColuna() == novaColuna) {
                continua = false;
            }
        }
        if(continua) {
            int diferencaLinha = Math.abs(linha - novaLinha);
            int diferencaColuna = Math.abs(coluna - novaColuna);
            if((diferencaLinha + diferencaColuna) != 0 && (diferencaLinha + diferencaColuna) <= movimento) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean podeCorrer(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) {
        boolean continua = true;
        for(Maquina maquina : maquinasEmJogo) {
            if(maquina.getLinha() == novaLinha && maquina.getColuna() == novaColuna) {
                continua = false;
            }
        }
        if(continua) {
            int diferencaLinha = Math.abs(linha - novaLinha);
            int diferencaColuna = Math.abs(coluna - novaColuna);
            if((diferencaLinha + diferencaColuna) != 0 && (diferencaLinha + diferencaColuna) <= movimento + 1) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String caminhoImagemDirecaoFixa() {
        return "src/images/Mergulho2-Norte.png";
    }
}
