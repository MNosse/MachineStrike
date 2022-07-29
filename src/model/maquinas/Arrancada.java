package model.maquinas;

import global.Enum.EnumTipoMaquinas;
import model.Jogador;
import model.Tabuleiro;
import model.Terreno;

public class Arrancada extends Maquina {
    
    public Arrancada(int linha, int coluna, Jogador jogador) {
        super(12, linha, coluna, 4, 2, "Arrancada", 2, jogador, 8, -1, EnumTipoMaquinas.ARRANCADA, 1, 0, 0);
    }
    
    @Override
    public String caminhoImagemDirecaoFixa() {
        return "src/images/Arrancada-Norte.png";
    }
}
