package model.abstractFactoryMaquina;

import model.Jogador;
import model.maquinas.Maquina;
import model.maquinas.Mergulho2;
import model.state.stateDirecao.StateDirecaoNorte;
import model.state.stateDirecao.StateDirecaoSul;

public class ConcretFactoryMergulho2 extends AbstractFactoryMaquina {
    @Override
    public Maquina construirMaquinaJogador1(int linha, int coluna, Jogador jogador) {
        Maquina maquina = new Mergulho2(linha, coluna, jogador);
        maquina.setDirecaoAtual(new StateDirecaoNorte(maquina));
        return maquina;
    }
    
    @Override
    public Maquina construirMaquinaJogador2(int linha, int coluna, Jogador jogador) {
        Maquina maquina = new Mergulho2(linha, coluna, jogador);
        maquina.setDirecaoAtual(new StateDirecaoSul(maquina));
        return maquina;
    }
}
