package model.builderMaquina;

//GLOBAL
import global.EnumTipoMaquinas;

public class ConstruirMergulho2 extends ConstruirMaquina {

    public void construirVida() {
        maquina.setVida(9);
    }

    public void construirAtaque() {
        maquina.setAtaque(3);
    }

    public void construirAlcance() {
        maquina.setAlcance(3);
    }

    public void construirNome() {
        maquina.setNome("Mergulho 2");
    }

    public void construirMovimento() {
        maquina.setMovimento(2);
    }

    public void construirPontosVitoria() {
        maquina.setPontosVitoria(5);
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
