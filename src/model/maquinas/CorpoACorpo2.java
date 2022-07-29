package model.maquinas;

import global.Enum.EnumTipoMaquinas;
import model.Jogador;

public class CorpoACorpo2 extends Maquina {
    
    public CorpoACorpo2(int linha, int coluna, Jogador jogador) {
        super(5, linha, coluna, 2, 1, "Corpo A Corpo 2", 3, jogador, 1, 0, EnumTipoMaquinas.CORPO_A_CORPO, 1, -1, -1);
    }
    
    @Override
    public String caminhoImagemDirecaoFixa() {
        return "src/images/CorpoACorpo2-Norte.png";
    }
}
