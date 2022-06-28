package controller.builderMaquina;

//GLOBAL
import global.EnumResistencia;
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