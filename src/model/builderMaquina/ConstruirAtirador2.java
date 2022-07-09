package model.builderMaquina;

//GLOBAL
import global.EnumResistencia;
import global.EnumTipoMaquinas;

public class ConstruirAtirador2 extends ConstruirMaquina {

    public void construirVida() {
        maquina.setVida(10);
    }

    public void construirAtaque() {
        maquina.setAtaque(3);
    }

    public void construirAlcance() {
        maquina.setAlcance(2);
    }

    public void construirNome() {
        maquina.setNome("Atirador 2");
    }

    public void construirMovimento() {
        maquina.setMovimento(2);
    }

    public void construirPontosVitoria() {
        maquina.setPontosVitoria(5);
    }

    public void construirTipo() {
        maquina.setTipo(EnumTipoMaquinas.ATIRADOR);
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