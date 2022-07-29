package controller.state.stateAcaoAtiva;

import controller.ControladorTelaJogo;
import controller.command.Command;
import global.Exception.ForaDoCampoCorridaException;
import model.visitor.VisitorJogadorAtivoGanhador;
import model.visitor.VisitorJogadorDefensorGanhador;
import model.visitor.VisitorMaquinasMortas;

public class StateAcaoAtivaCorrer extends StateAcaoAtiva {
    
    public StateAcaoAtivaCorrer(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }
    
    @Override
    public void fazerAcao(String posicao) throws Exception {
        if(controladorTelaJogo.getCampoDeCorrida().contains(posicao)) {
            int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            Command comm = cf.getComando("correr", new Object[]{controladorTelaJogo.getMaquinaSelecionada(), linha, coluna, controladorTelaJogo.getJogo().getTabuleiro().getTerrenoPorPosicao(linha + "" + coluna), controladorTelaJogo.getJogo().getTabuleiro().getMaquinas()});
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
            throw new ForaDoCampoCorridaException();
        }
    }
    
    @Override
    public boolean estaCorrendo() {
        return true;
    }
}
