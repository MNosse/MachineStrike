package model.maquinas;

import global.Enum.EnumTipoMaquinas;
import model.Jogador;
import model.Tabuleiro;

public class Atirador2 extends Maquina {
    
    public Atirador2(int linha, int coluna, Jogador jogador) {
        super(10, linha, coluna, 3, 2, "Atirador 2", 2, jogador, 5, 0, EnumTipoMaquinas.ATIRADOR, 1, -1, -1);
    }
    
    public void acaoAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
    
    }
    
    public boolean podeAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        return false;
    }
}
