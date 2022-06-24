package controller.builderMaquina;

import global.EnumResistencia;
import global.EnumTipoMaquinas;

public class ConstruirAriete2 extends ConstruirMaquina {
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
        maquina.setNome("Ariete 2");
    }

    public void construirMovimento() {
        maquina.setMovimento(2);
    }

    public void construirPontosVitoria() {
        maquina.setPontosVitoria(2);
    }

    public void construirTipo() {
        maquina.setTipo(EnumTipoMaquinas.ARIETE);
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
