package controller.state.stateAcaoAtiva;

import controller.ControladorTelaJogo;
import controller.command.Command;
import global.Exception.ForaDoCampoMovimentoException;
import model.visitor.VisitorJogadorAtivoGanhador;
import model.visitor.VisitorJogadorDefensorGanhador;
import model.visitor.VisitorMaquinasMortas;

public class StateAcaoAtivaMover extends StateAcaoAtiva {
    
    public StateAcaoAtivaMover(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }
    
    @Override
    public void fazerAcao(String posicao) throws Exception {
        if(controladorTelaJogo.getCampoDeMovimento().contains(posicao)) {
            int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            Command comm = cf.getComando("mover", new Object[]{controladorTelaJogo.getMaquinaSelecionada(), linha, coluna, controladorTelaJogo.getJogo().getTabuleiro().getTerrenoPorPosicao(linha + "" + coluna), controladorTelaJogo.getJogo().getTabuleiro().getMaquinas()});
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
            controladorTelaJogo.getJogo().addMaquinaQueRealizouAcao(controladorTelaJogo.getMaquinaSelecionada());
            controladorTelaJogo.getJogo().addMaquinaQueMoveu(controladorTelaJogo.getMaquinaSelecionada());
            controladorTelaJogo.desativarPainelDefensor();
            controladorTelaJogo.ativarPainelAtacante();
            ativarNeutro();
        } else {
            controladorTelaJogo.redesenharMaquinas();
            ativarNeutro();
            throw new ForaDoCampoMovimentoException();
        }
    }
    
    @Override
    public boolean estaMovendo() {
        return true;
    }
}
