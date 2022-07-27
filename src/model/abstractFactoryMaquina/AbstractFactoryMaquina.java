package model.abstractFactoryMaquina;

import model.Jogador;
import model.maquinas.Maquina;

public abstract class AbstractFactoryMaquina {
    public abstract Maquina construirMaquinaJogador1(int linha, int coluna, Jogador jogador);
    
    public abstract Maquina construirMaquinaJogador2(int linha, int coluna, Jogador jogador);
    
}
