package model.maquinas;

import global.Enum.EnumTipoMaquinas;
import model.Jogador;
import model.Tabuleiro;
import model.Terreno;
import model.state.StateSobrecarregar.StateSobrecarregarAtivo;
import model.state.stateCorrer.StateCorrerInativo;
import model.state.stateMover.StateMoverInativo;

import java.util.List;

public class Puxao extends Maquina {
    
    public Puxao(int linha, int coluna, Jogador jogador) {
        super(10, linha, coluna, 4, 3, "Puxao", 2, jogador, 6, -1, EnumTipoMaquinas.PUXAO, 0, 1, 1);
    }
    
    @Override
    public void acaoMover(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) {
        linha = novaLinha;
        coluna = novaColuna;
        moverAtual = new StateMoverInativo(this);
        correrAtual = new StateCorrerInativo(this);
        if(!isAtacarAtivo() && !jaSobrecarregou) {
            sobrecarregarAtual = new StateSobrecarregarAtivo(this);
        }
    }
    
    public void acaoAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
    
    }
    
    public boolean podeAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        return false;
    }
}
