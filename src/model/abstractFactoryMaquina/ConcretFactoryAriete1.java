package model.abstractFactoryMaquina;

import model.Jogador;
import model.maquinas.Ariete1;
import model.maquinas.Maquina;
import model.state.stateDirecao.StateDirecaoNorte;
import model.state.stateDirecao.StateDirecaoSul;

public class ConcretFactoryAriete1 extends AbstractFactoryMaquina {
    @Override
    public Maquina construirMaquinaJogador1(int linha, int coluna, Jogador jogador) {
        Maquina maquina = new Ariete1(linha, coluna, jogador);
        maquina.setDirecaoAtual(new StateDirecaoNorte(maquina));
        return maquina;
    }
    
    @Override
    public Maquina construirMaquinaJogador2(int linha, int coluna, Jogador jogador) {
        Maquina maquina = new Ariete1(linha, coluna, jogador);
        maquina.setDirecaoAtual(new StateDirecaoSul(maquina));
        return maquina;
    }
}
