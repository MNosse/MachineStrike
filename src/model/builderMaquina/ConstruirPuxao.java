package model.builderMaquina;

//GLOBAL
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
        maquina.setResistenciaTras(-1);
    }

    public void construirFrente() {
        maquina.setResistenciaFrente(0);
    }

    public void construirDireita() {
        maquina.setResistenciaDireita(1);
    }

    public void construirEsquerda() {
        maquina.setResistenciaEsquerda(1);
    }

}
