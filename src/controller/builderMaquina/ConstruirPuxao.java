package controller.builderMaquina;

//GLOBAL
import global.EnumResistencia;
import global.EnumTipoMaquinas;

public class ConstruirPuxao extends ConstruirMaquina {

    public void construirVida() {
        maquina.setVida(10);
    }

    public void construirAtaque() {
        maquina.setAtaque(4);
    }

    public void construirAlcance() {
        maquina.setAlcance(3);
    }

    public void construirNome() {
        maquina.setNome("Puxao");
    }

    public void construirMovimento() {
        maquina.setMovimento(2);
    }

    public void construirPontosVitoria() {
        maquina.setPontosVitoria(6);
    }

    public void construirTipo() {
        maquina.setTipo(EnumTipoMaquinas.PUXAO);
    }

    public void construirTras() {
        maquina.setTras(EnumResistencia.FRACO);
    }

    public void construirFrente() {
        maquina.setFrente(EnumResistencia.NEUTRO);
    }

    public void construirDireita() {
        maquina.setDireita(EnumResistencia.FORTE);
    }

    public void construirEsquerda() {
        maquina.setEsquerda(EnumResistencia.FORTE);
    }

}
