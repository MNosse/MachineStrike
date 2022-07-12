package model.builderMaquina;

//GLOBAL
import global.EnumTipoMaquinas;

public class ConstruirCorpoACorpo2 extends ConstruirMaquina {

    public void construirVida() {
        maquina.setVida(5);
    }

    public void construirAtaque() {
        maquina.setAtaque(2);
    }

    public void construirAlcance() {
        maquina.setAlcance(1);
    }

    public void construirNome() {
        maquina.setNome("Corpo A Corpo 2");
    }

    public void construirMovimento() {
        maquina.setMovimento(3);
    }

    public void construirPontosVitoria() {
        maquina.setPontosVitoria(1);
    }

    public void construirTipo() {
        maquina.setTipo(EnumTipoMaquinas.CORPO_A_CORPO);
    }

    public void construirTras() {
        maquina.setResistenciaTras(0);
    }

    public void construirFrente() {
        maquina.setResistenciaFrente(1);
    }

    public void construirDireita() {
        maquina.setResistenciaDireita(-1);
    }

    public void construirEsquerda() {
        maquina.setResistenciaEsquerda(-1);
    }

}
