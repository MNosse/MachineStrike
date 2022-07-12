package model.builderMaquina;

//GLOBAL
import global.EnumTipoMaquinas;

public class ConstruirMergulho1 extends ConstruirMaquina {

    public void construirVida() {
        maquina.setVida(6);
    }

    public void construirAtaque() {
        maquina.setAtaque(2);
    }

    public void construirAlcance() {
        maquina.setAlcance(3);
    }

    public void construirNome() {
        maquina.setNome("Mergulho 1");
    }

    public void construirMovimento() {
        maquina.setMovimento(2);
    }

    public void construirPontosVitoria() {
        maquina.setPontosVitoria(2);
    }

    public void construirTipo() {
        maquina.setTipo(EnumTipoMaquinas.MERGULHO);
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
