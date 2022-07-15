package model.builderMaquina;

//GLOBAL
import global.Enum.EnumTipoMaquinas;

public class ConstruirArrancada extends ConstruirMaquina {

    public void construirVida() {
        maquina.setVida(12);
    }

    public void construirAtaque() {
        maquina.setAtaque(4);
    }

    public void construirAlcance() {
        maquina.setAlcance(2);
    }

    public void construirNome() {
        maquina.setNome("Arrancada");
    }

    public void construirMovimento() {
        maquina.setMovimento(2);
    }

    public void construirPontosVitoria() {
        maquina.setPontosVitoria(8);
    }

    public void construirTipo() {
        maquina.setTipo(EnumTipoMaquinas.ARRANCADA);
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
