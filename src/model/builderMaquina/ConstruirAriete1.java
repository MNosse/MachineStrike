package model.builderMaquina;

//GLOBAL
import global.EnumResistencia;
import global.EnumTipoMaquinas;

public class ConstruirAriete1 extends ConstruirMaquina {

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
        maquina.setNome("Ariete 1");
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
