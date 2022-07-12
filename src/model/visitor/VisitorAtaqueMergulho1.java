package model.visitor;

//GLOBAl
import global.EnumDirecao;

//MODEL
import model.Maquina;
import model.Terreno;

public class VisitorAtaqueMergulho1 extends VisitorAtaque{

    public VisitorAtaqueMergulho1(Maquina maquina, Terreno terreno) {
        super(maquina, terreno);
    }

    @Override
    public void atacar(Maquina outraMaquina, Terreno outroTerreno) {
        int ataque = 2 + pontosTerreno;
        int defesa = outroTerreno.getPontosDeCombate();
        int outraLinha = outraMaquina.getLinha();
        int outraColuna = outraMaquina.getColuna();
        if (linha == outraLinha) {
            if (coluna < outraColuna && direcaoAtual.equals(EnumDirecao.LESTE)) {
                defesa += outraMaquina.pontosCombateEsquerdaAtual();
            } else if (coluna > outraColuna && direcaoAtual.equals(EnumDirecao.OESTE)) {
                defesa += outraMaquina.pontosCombateDireitaAtual();
            }
        } else if (coluna == outraColuna) {
            if (linha < outraLinha && direcaoAtual.equals(EnumDirecao.SUL)) {
                defesa += outraMaquina.pontosCombateCimaAtual();
            } else if (linha > outraLinha && direcaoAtual.equals(EnumDirecao.NORTE)) {
                defesa += outraMaquina.pontosCombateBaixoAtual();
            }
        }
        if (ataque >= defesa) {
            outraMaquina.setVida(outraMaquina.getVida()-(ataque-defesa));
        }
    }
}
