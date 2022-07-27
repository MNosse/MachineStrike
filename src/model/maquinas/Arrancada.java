package model.maquinas;

import global.Enum.EnumTipoMaquinas;
import model.Jogador;
import model.Tabuleiro;
import model.Terreno;

public class Arrancada extends Maquina {
    
    public Arrancada(int linha, int coluna, Jogador jogador) {
        super(12, linha, coluna, 4, 2, "Arrancada", 2, jogador, 8, -1, EnumTipoMaquinas.ARRANCADA, 1, 0, 0);
    }
    
    public void acaoAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        int outraLinha = outraMaquina.getLinha();
        int outraColuna = outraMaquina.getColuna();
        Terreno terrenoDessaMaquina = tabuleiro.getTerrenoPorPosicao(linha + "" + coluna);
        int pontoDeAtaque = ataque + terrenoDessaMaquina.getPontosDeCombate();
        int distanciaCaminhar = alcance;
        if(linha == outraLinha) {
            if(coluna < outraColuna) {
                distanciaCaminhar = ((coluna + alcance) <= 7) ? alcance : 7;
                for(int i = coluna++; i < distanciaCaminhar; i++) {
                    Maquina maquinaDefensora = tabuleiro.getMaquinaPorPosicao(linha, i);
                    if(maquinaDefensora != null) {
                        int pontoDeDefesa = tabuleiro.getTerrenoPorPosicao(linha + "" + i).getPontosDeCombate() + maquinaDefensora.getPontosEscudo(linha, coluna);
                        if(pontoDeAtaque > pontoDeDefesa) {
                            maquinaDefensora.setVida(maquinaDefensora.getVida() - (pontoDeAtaque - pontoDeDefesa));
                            maquinaDefensora.girar();
                            maquinaDefensora.girar();
                        }
                    }
                }
            } else if(coluna > outraColuna) {
                distanciaCaminhar = ((coluna - alcance) >= 0) ? alcance : 0;
                for(int i = coluna--; i > distanciaCaminhar; i--) {
                    Maquina maquinaDefensora = tabuleiro.getMaquinaPorPosicao(linha, i);
                    if(maquinaDefensora != null) {
                        int pontoDeDefesa = tabuleiro.getTerrenoPorPosicao(linha + "" + i).getPontosDeCombate() + maquinaDefensora.getPontosEscudo(linha, coluna);
                        if(pontoDeAtaque > pontoDeDefesa) {
                            maquinaDefensora.setVida(maquinaDefensora.getVida() - (pontoDeAtaque - pontoDeDefesa));
                            maquinaDefensora.girar();
                            maquinaDefensora.girar();
                        }
                    }
                }
            }
            coluna = distanciaCaminhar;
        } else if(coluna == outraColuna) {
            if(linha < outraLinha) {
                distanciaCaminhar = ((linha + alcance) <= 7) ? alcance : 7;
                for(int i = linha++; i < distanciaCaminhar; i++) {
                    Maquina maquinaDefensora = tabuleiro.getMaquinaPorPosicao(i, coluna);
                    if(maquinaDefensora != null) {
                        int pontoDeDefesa = tabuleiro.getTerrenoPorPosicao(i + "" + coluna).getPontosDeCombate() + maquinaDefensora.getPontosEscudo(linha, coluna);
                        if(pontoDeAtaque > pontoDeDefesa) {
                            maquinaDefensora.setVida(maquinaDefensora.getVida() - (pontoDeAtaque - pontoDeDefesa));
                            maquinaDefensora.girar();
                            maquinaDefensora.girar();
                        }
                    }
                }
            } else if(linha > outraLinha) {
                distanciaCaminhar = ((linha - alcance) >= 0) ? alcance : 0;
                for(int i = linha--; i > distanciaCaminhar; i--) {
                    Maquina maquinaDefensora = tabuleiro.getMaquinaPorPosicao(i, coluna);
                    if(maquinaDefensora != null) {
                        int pontoDeDefesa = tabuleiro.getTerrenoPorPosicao(i + "" + coluna).getPontosDeCombate() + maquinaDefensora.getPontosEscudo(linha, coluna);
                        if(pontoDeAtaque > pontoDeDefesa) {
                            maquinaDefensora.setVida(maquinaDefensora.getVida() - (pontoDeAtaque - pontoDeDefesa));
                            maquinaDefensora.girar();
                            maquinaDefensora.girar();
                        }
                    }
                }
            }
            linha = distanciaCaminhar;
        }
        
    }
    
    public boolean podeAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        int outraLinha = outraMaquina.getLinha();
        int outraColuna = outraMaquina.getColuna();
        int distanciaCaminhar = alcance;
        if(linha == outraLinha) {
            if(coluna < outraColuna) {
                distanciaCaminhar = ((coluna + alcance) <= 7) ? alcance : 7;
            } else if(coluna > outraColuna) {
                distanciaCaminhar = ((coluna - alcance) >= 0) ? alcance : 0;
            }
            if(tabuleiro.getMaquinaPorPosicao(linha, distanciaCaminhar) == null && ((outraColuna < distanciaCaminhar))) {
                return true;
            }
            return false;
        } else if(coluna == outraColuna) {
            if(linha < outraLinha) {
                distanciaCaminhar = ((linha + alcance) <= 7) ? alcance : 7;
            } else if(linha > outraLinha) {
                distanciaCaminhar = ((linha - alcance) >= 0) ? alcance : 0;
            }
            if((tabuleiro.getMaquinaPorPosicao(distanciaCaminhar, coluna) == null && (outraLinha < distanciaCaminhar))) {
                return true;
            }
            return false;
        }
        return false;
    }
}
