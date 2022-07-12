package model.builderMaquina;

//GLOBAL
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
