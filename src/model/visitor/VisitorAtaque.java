package model.visitor;

//GLOBAl
import global.EnumDirecao;

//MODEL
import model.Maquina;
import model.Terreno;

public abstract class VisitorAtaque {
    protected int linha;
    protected int coluna;
    protected int pontosTerreno;
    protected EnumDirecao direcaoAtual;

    protected VisitorAtaque(Maquina maquina, Terreno terreno) {
        linha = maquina.getLinha();
        coluna = maquina.getColuna();
        pontosTerreno = terreno.getPontosDeCombate();
        direcaoAtual = maquina.direcaoAtualDaMaquina();
    }

    public abstract void atacar(Maquina outraMaquina, Terreno outroTerreno);

    //FACTORY METHOD
    public static VisitorAtaque criarVisitorAtaque(Maquina maquina, Terreno terrno) {
        if (maquina.getNome().equals("Ariete 1")){
            return new VisitorAtaqueAriete1(maquina, terrno);
        } else if (maquina.getNome().equals("Ariete 2")){
            return new VisitorAtaqueAriete2(maquina, terrno);
        } else if (maquina.getNome().equals("Arrancada")){
            return new VisitorAtaqueArrancada(maquina, terrno);
        } else if (maquina.getNome().equals("Atirador 1")){
            return new VisitorAtaqueAtirador1(maquina, terrno);
        } else if (maquina.getNome().equals("Atirador 2")){
            return new VisitorAtaqueAtirador2(maquina, terrno);
        } else if (maquina.getNome().equals("Corpo A Corpo 1")){
            return new VisitorAtaqueCorpoACorpo1(maquina, terrno);
        } else if (maquina.getNome().equals("Corpo A Corpo 2")){
            return new VisitorAtaqueCorpoACorpo2(maquina, terrno);
        } else if (maquina.getNome().equals("Mergulho 1")){
            return new VisitorAtaqueMergulho1(maquina, terrno);
        } else if (maquina.getNome().equals("Mergulho 2")){
            return new VisitorAtaqueMergulho2(maquina, terrno);
        } else {
            return new VisitorAtaquePuxao(maquina, terrno);
        }
    }
}
