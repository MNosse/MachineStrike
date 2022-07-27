package model.abstractFactoryMaquina;

import model.Jogador;
import model.maquinas.Atirador2;
import model.maquinas.Maquina;
import model.state.stateDirecao.StateDirecaoNorte;
import model.state.stateDirecao.StateDirecaoSul;

public class ConcretFactoryAtirador2 extends AbstractFactoryMaquina {
    @Override
    public Maquina construirMaquinaJogador1(int linha, int coluna, Jogador jogador) {
        Maquina maquina = new Atirador2(linha, coluna, jogador);
        maquina.setDirecaoAtual(new StateDirecaoNorte(maquina));
        return maquina;
    }
    
    @Override
    public Maquina construirMaquinaJogador2(int linha, int coluna, Jogador jogador) {
        Maquina maquina = new Atirador2(linha, coluna, jogador);
        maquina.setDirecaoAtual(new StateDirecaoSul(maquina));
        return maquina;
    }
}
