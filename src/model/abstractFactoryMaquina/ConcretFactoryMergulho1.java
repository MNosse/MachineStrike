package model.abstractFactoryMaquina;

import model.Jogador;
import model.maquinas.Maquina;
import model.maquinas.Mergulho1;
import model.state.stateDirecao.StateDirecaoNorte;
import model.state.stateDirecao.StateDirecaoSul;

public class ConcretFactoryMergulho1 extends AbstractFactoryMaquina {
    @Override
    public Maquina construirMaquinaJogador1(int linha, int coluna, Jogador jogador) {
        Maquina maquina = new Mergulho1(linha, coluna, jogador);
        maquina.setDirecaoAtual(new StateDirecaoNorte(maquina));
        return maquina;
    }
    
    @Override
    public Maquina construirMaquinaJogador2(int linha, int coluna, Jogador jogador) {
        Maquina maquina = new Mergulho1(linha, coluna, jogador);
        maquina.setDirecaoAtual(new StateDirecaoSul(maquina));
        return maquina;
    }
}
