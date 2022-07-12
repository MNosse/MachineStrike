package model.builderMaquina;

//GLOBAL
import global.EnumTipoMaquinas;

public class ConstruirCorpoACorpo1 extends ConstruirMaquina {

    public void construirVida() {
        maquina.setVida(3);
    }

    public void construirAtaque() {
        maquina.setAtaque(1);
    }

    public void construirAlcance() {
        maquina.setAlcance(1);
    }

    public void construirNome() {
        maquina.setNome("Corpo A Corpo 1");
    }

    public void construirMovimento() {
        maquina.setMovimento(4);
    }

    public void construirPontosVitoria() {
        maquina.setPontosVitoria(1);
    }

    public void construirTipo() {
        maquina.setTipo(EnumTipoMaquinas.CORPO_A_CORPO);
    }

    public void construirTras() {
        maquina.setResistenciaTras(-1);
    }

    public void construirFrente() {
        maquina.setResistenciaFrente(1);
    }

    public void construirDireita() {
        maquina.setResistenciaDireita(0);
    }

    public void construirEsquerda() {
        maquina.setResistenciaEsquerda(0);
    }

}
