package model.maquinas;

import global.Enum.EnumTipoMaquinas;
import model.Jogador;
import model.Tabuleiro;


public class Atirador1 extends Maquina {
    
    public Atirador1(int linha, int coluna, Jogador jogador) {
        super(5, linha, coluna, 2, 2, "Atirador 1", 2, jogador, 3, 0, EnumTipoMaquinas.ATIRADOR, -1, 1, 1);
    }
    
    public void acaoAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
    
    }
    
    public boolean podeAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        return false;
    }
}
