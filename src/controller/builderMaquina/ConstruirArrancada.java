package controller.builderMaquina;

import global.EnumResistencia;
import global.EnumTipoMaquinas;

public class ConstruirArrancada extends ConstruirMaquina {
    public void construirVida() {
        maquina.setVida(12);
    }

    public void construirAtaque() {
        maquina.setAtaque(4);
    }

    public void construirAlcance() {
        maquina.setAlcance(2);
    }

    public void construirNome() {
        maquina.setNome("Arrancada");
    }

    public void construirMovimento() {
        maquina.setMovimento(2);
    }

    public void construirPontosVitoria() {
        maquina.setPontosVitoria(8);
    }

    public void construirTipo() {
        maquina.setTipo(EnumTipoMaquinas.ARRANCADA);
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
