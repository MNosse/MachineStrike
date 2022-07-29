package controller.state.stateAcaoAtiva;

import controller.ControladorTelaJogo;
import controller.command.Command;
import global.Exception.ForaDoCampoAtaqueException;
import model.maquinas.Maquina;
import model.visitor.VisitorJogadorAtivoGanhador;
import model.visitor.VisitorJogadorDefensorGanhador;
import model.visitor.VisitorMaquinasMortas;

public class StateAcaoAtivaAtacar extends StateAcaoAtiva {
    
    public StateAcaoAtivaAtacar(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }
    
    @Override
    public void fazerAcao(String posicao) throws Exception {
        int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
        int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
        Maquina maquinaDefensora = controladorTelaJogo.getJogo().getTabuleiro().getMaquinaPorPosicao(linha, coluna);
        if(controladorTelaJogo.getCampoDeAtaque().contains(posicao)) {
            Command comm = cf.getComando("atacar", new Object[]{controladorTelaJogo.getMaquinaSelecionada(), maquinaDefensora, controladorTelaJogo.getJogo().getTabuleiro()});
            ci.execute(comm);
            if(controladorTelaJogo.getJogo().accept(new VisitorMaquinasMortas())) {
                controladorTelaJogo.atualizarLblJogadorAtivo();
                controladorTelaJogo.redesenharMaquinas();
            }
            controladorTelaJogo.getJogo().addMaquinaQueRealizouAcao(controladorTelaJogo.getMaquinaSelecionada());
            if(controladorTelaJogo.getJogo().accept(new VisitorJogadorAtivoGanhador())) {
                controladorTelaJogo.anunciarGanhador(controladorTelaJogo.getJogo().jogadorAtivo());
            } else if(controladorTelaJogo.getJogo().accept(new VisitorJogadorDefensorGanhador())) {
                controladorTelaJogo.anunciarGanhador(controladorTelaJogo.getJogo().jogadorDefensor());
            }
            controladorTelaJogo.desativarPainelDefensor();
            controladorTelaJogo.ativarPainelAtacante();
            ativarNeutro();
        } else {
            controladorTelaJogo.redesenharMaquinas();
            ativarNeutro();
            throw new ForaDoCampoAtaqueException();
        }
    }
    
    @Override
    public boolean estaAtacando() {
        return true;
    }
}
