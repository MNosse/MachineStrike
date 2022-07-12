package model.builderMaquina;

//GLOBAL
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
