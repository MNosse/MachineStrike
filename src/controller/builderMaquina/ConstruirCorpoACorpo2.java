package controller.builderMaquina;

//GLOBAL
import global.EnumResistencia;
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
        maquina.setTras(EnumResistencia.NEUTRO);
    }

    public void construirFrente() {
        maquina.setFrente(EnumResistencia.FORTE);
    }

    public void construirDireita() {
        maquina.setDireita(EnumResistencia.FRACO);
    }

    public void construirEsquerda() {
        maquina.setEsquerda(EnumResistencia.FRACO);
    }

}
