package model.maquinas;

import global.Enum.EnumTipoMaquinas;
import model.Jogador;
import model.Tabuleiro;


public class Atirador1 extends Maquina {
    
    public Atirador1(int linha, int coluna, Jogador jogador) {
        super(5, linha, coluna, 2, 2, "Atirador 1", 2, jogador, 3, 0, EnumTipoMaquinas.ATIRADOR, -1, 1, 1);
    }
    
    public boolean podeAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        if(!outraMaquina.getJogador().equals(jogador)) {
            int outraLinha = outraMaquina.getLinha();
            int outraColuna = outraMaquina.getColuna();
            if(linha == outraLinha && Math.abs(coluna - outraColuna) == alcance) {
                return true;
            } else if(coluna == outraColuna && Math.abs(linha - outraLinha) == alcance) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String caminhoImagemDirecaoFixa() {
        return "src/images/Atirador1-Norte.png";
    }
}
