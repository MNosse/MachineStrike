package model.builderMaquina;

//GLOBAL
import global.Enum.EnumTipoMaquinas;

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
