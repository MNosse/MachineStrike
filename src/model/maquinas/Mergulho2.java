package model.maquinas;

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
    
    public void acaoAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
    
    }
    
    public boolean podeAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        return false;
    }
}
