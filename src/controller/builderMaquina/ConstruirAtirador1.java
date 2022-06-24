package controller.builderMaquina;

import global.EnumResistencia;
import global.EnumTipoMaquinas;

public class ConstruirAtirador1 extends ConstruirMaquina {
    public void construirVida() {
        maquina.setVida(5);
    }

    public void construirAtaque() {
        maquina.setAtaque(2);
    }

    public void construirAlcance() {
        maquina.setAlcance(2);
    }

    public void construirNome() {
        maquina.setNome("Atirador 1");
    }

    public void construirMovimento() {
        maquina.setMovimento(2);
    }

    public void construirPontosVitoria() {
        maquina.setPontosVitoria(3);
    }

    public void construirTipo() {
        maquina.setTipo(EnumTipoMaquinas.ATIRADOR);
    }

    public void construirTras() {
        maquina.setTras(EnumResistencia.NEUTRO);
    }

    public void construirFrente() {
        maquina.setFrente(EnumResistencia.FRACO);
    }

    public void construirDireita() {
        maquina.setDireita(EnumResistencia.NEUTRO);
    }

    public void construirEsquerda() {
        maquina.setEsquerda(EnumResistencia.NEUTRO);
    }
}
