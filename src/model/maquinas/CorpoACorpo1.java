package model.maquinas;

import global.Enum.EnumTipoMaquinas;
import model.Jogador;

public class CorpoACorpo1 extends Maquina {
    
    public CorpoACorpo1(int linha, int coluna, Jogador jogador) {
        super(3, linha, coluna, 1, 1, "Corpo A Corpo 1", 4, jogador, 1, -1, EnumTipoMaquinas.CORPO_A_CORPO, 1, 0, 0);
    }
    
    @Override
    public String caminhoImagemDirecaoFixa() {
        return "src/images/CorpoACorpo1-Norte.png";
    }
}
