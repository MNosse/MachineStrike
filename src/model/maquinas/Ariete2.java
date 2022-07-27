package model.maquinas;


import global.Enum.EnumDirecao;
import global.Enum.EnumTipoMaquinas;
import model.Jogador;
import model.Tabuleiro;
import model.Terreno;

import java.util.ArrayList;
import java.util.List;

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
        boolean atacandoMesmaLinhaEsquerda = false;
        boolean atacandoMesmaLinhaDireita = false;
        boolean atacandoMesmaColunaCima = false;
        boolean atavandoMemaColunaBaixo = false;
        List<Maquina> maquinasAtras = new ArrayList<>();
        if(linha == linhaOutraMaquina) {
            if(coluna < colunaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.LESTE)) {
                atacandoMesmaLinhaEsquerda = true;
                if(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina++) != null) {
                    for(int i = colunaOutraMaquina++; i <= 7; i++) {
                        Maquina maquinaDeTras = tabuleiro.getMaquinaPorPosicao(linha, i);
                        if(maquinaDeTras != null) {
                            maquinasAtras.add(maquinaDeTras);
                        } else {
                            break;
                        }
                    }
                }
            } else if(coluna > colunaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.OESTE)) {
                atacandoMesmaLinhaDireita = true;
                if(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina--) != null) {
                    for(int i = colunaOutraMaquina--; i >= 0; i--) {
                        Maquina maquinaDeTras = tabuleiro.getMaquinaPorPosicao(linha, i);
                        if(maquinaDeTras != null) {
                            maquinasAtras.add(maquinaDeTras);
                        } else {
                            break;
                        }
                    }
                }
            }
        } else if(coluna == colunaOutraMaquina) {
            if(linha < linhaOutraMaquina && direcaoAtual.getDirecaoAtual().equals((EnumDirecao.SUL))) {
                atacandoMesmaColunaCima = true;
                if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina++, coluna) != null) {
                    for(int i = linhaOutraMaquina++; i <= 7; i++) {
                        Maquina maquinaDeTras = tabuleiro.getMaquinaPorPosicao(i, coluna);
                        if(maquinaDeTras != null) {
                            maquinasAtras.add(maquinaDeTras);
                        } else {
                            break;
                        }
                    }
                }
            } else if(linha > linhaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.NORTE)) {
                atavandoMemaColunaBaixo = true;
                if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina--, coluna) != null) {
                    for(int i = linhaOutraMaquina--; i >= 0; i--) {
                        Maquina maquinaDeTras = tabuleiro.getMaquinaPorPosicao(i, coluna);
                        if(maquinaDeTras != null) {
                            maquinasAtras.add(maquinaDeTras);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if(pontoDeAtaque > pontoDeDefesa) {
            outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
            
        } else if(pontoDeAtaque == pontoDeDefesa) {
            vida--;
            outraMaquina.setVida(outraMaquina.getVida() - 1);
            
        }
        if(pontoDeAtaque >= pontoDeDefesa) {
            if((outraMaquina.getLinha() == 0 && atavandoMemaColunaBaixo) || (outraMaquina.getLinha() == 7 && atacandoMesmaColunaCima) || (outraMaquina.getColuna() == 0 && atacandoMesmaLinhaDireita) || (outraMaquina.getColuna() == 7 && atacandoMesmaLinhaEsquerda)) {
                outraMaquina.setVida(outraMaquina.getVida() - 1);
            } else if(maquinasAtras.size() > 0) {
                outraMaquina.setVida(outraMaquina.getVida() - 1);
                for(Maquina m : maquinasAtras) {
                    m.setVida(m.getVida() - 1);
                }
                Maquina ultima = maquinasAtras.get(maquinasAtras.size()-1);
                if(atacandoMesmaColunaCima) {
                    ultima.setLinha(ultima.getLinha() + 1);
                } else if(atavandoMemaColunaBaixo) {
                    ultima.setLinha(ultima.getLinha() - 1);
                } else if(atacandoMesmaLinhaEsquerda) {
                    ultima.setColuna(ultima.getColuna() + 1);
                } else if(atacandoMesmaLinhaDireita) {
                    ultima.setColuna(ultima.getColuna() - 1);
                }
            } else {
                if(atacandoMesmaColunaCima) {
                    outraMaquina.setLinha(outraMaquina.getLinha() + 1);
                } else if(atavandoMemaColunaBaixo) {
                    outraMaquina.setLinha(outraMaquina.getLinha() - 1);
                } else if(atacandoMesmaLinhaEsquerda) {
                    outraMaquina.setColuna(outraMaquina.getColuna() + 1);
                } else if(atacandoMesmaLinhaDireita) {
                    outraMaquina.setColuna(outraMaquina.getColuna() - 1);
                }
            }
        }
    }
}
