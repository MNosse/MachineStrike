package controller.builderMaquina;

import global.EnumResistencia;
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
        maquina.setNome("Corpo a corpo 1");
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
        maquina.setTras(EnumResistencia.FRACO);
    }

    public void construirFrente() {
        maquina.setFrente(EnumResistencia.FORTE);
    }

    public void construirDireita() {
        maquina.setDireita(EnumResistencia.NEUTRO);
    }

    public void construirEsquerda() {
        maquina.setEsquerda(EnumResistencia.NEUTRO);
    }
}
