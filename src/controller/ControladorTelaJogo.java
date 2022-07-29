package controller;

import controller.command.Command;
import controller.command.CommandFactory;
import controller.command.CommandInvoker;
import controller.observer.ObserverCommand;
import controller.observer.ObserverTelaJogo;
import controller.singleton.SingletonConfiguracaoJogo;
import controller.state.stateAcaoAtiva.StateAcaoAtiva;
import controller.state.stateAcaoAtiva.StateAcaoAtivaNeutro;
import global.Enum.EnumTipoTerreno;
import global.Exception.LimiteDeAcoesException;
import global.Exception.MinimoDeMovimentoException;
import global.Exception.SemMaquinaNoCampoAtaqueException;
import model.Jogador;
import model.Jogo;
import model.Tabuleiro;
import model.Terreno;
import model.maquinas.Maquina;
import view.abstractFactoryTela.AbstractFactoryTela;

import java.util.*;

public class ControladorTelaJogo implements ObserverCommand {
    
    private Jogo jogo;
    private List<ObserverTelaJogo> observers;
    private Set<String> campoDeMovimento;
    private Set<String> campoDeCorrida;
    private Set<String> campoDeAtaque;
    private Maquina maquinaSelecionada;
    private StateAcaoAtiva stateAcaoAtiva;
    
    public ControladorTelaJogo() {
        jogo = new Jogo(SingletonConfiguracaoJogo.getInstancia().getTabuleiro(), SingletonConfiguracaoJogo.getInstancia().getJogadores());
        observers = new ArrayList<>();
        campoDeMovimento = new HashSet<>();
        campoDeCorrida = new HashSet<>();
        campoDeAtaque = new HashSet<>();
        maquinaSelecionada = null;
        stateAcaoAtiva = new StateAcaoAtivaNeutro(this);
    }
    
    public void attach(ObserverTelaJogo observer) {
        observers.add(observer);
    }
    
    public void desenharJogoInicial() {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        HashMap<String, Terreno> terrenos = tabuleiro.getTerrenos();
        Set<String> posicoes = terrenos.keySet();
        HashMap<String, EnumTipoTerreno> tiposTerrenos = new HashMap<>();
        for(String posicao : posicoes) {
            tiposTerrenos.put(posicao, terrenos.get(posicao).getTipo());
        }
        HashMap<String, String> desenhosMaquinasJogadorAtivo = new HashMap<>();
        for(Maquina maquina : jogo.jogadorAtivo().getMaquinas()) {
            desenhosMaquinasJogadorAtivo.put(maquina.getLinha() + "" + maquina.getColuna(), maquina.caminhoImagemDirecaoAtual());
        }
        HashMap<String, String> desenhosMaquinasJogadorInativo = new HashMap<>();
        for(Maquina maquina : jogo.jogadorDefensor().getMaquinas()) {
            desenhosMaquinasJogadorInativo.put(maquina.getLinha() + "" + maquina.getColuna(), maquina.caminhoImagemDirecaoAtual());
        }
        for(ObserverTelaJogo observer : observers) {
            observer.desenharTabuleiro(tiposTerrenos);
            observer.redesenharMaquinas(desenhosMaquinasJogadorAtivo, desenhosMaquinasJogadorInativo);
        }
    }
    
    @Override
    public void redesenharMaquinas() {
        limparCampos();
        HashMap<String, String> desenhosMaquinasJogadorAtivo = new HashMap<>();
        for(Maquina maquina : jogo.jogadorAtivo().getMaquinas()) {
            desenhosMaquinasJogadorAtivo.put(maquina.getLinha() + "" + maquina.getColuna(), maquina.caminhoImagemDirecaoAtual());
        }
        HashMap<String, String> desenhosMaquinasJogadorInativo = new HashMap<>();
        for(Maquina maquina : jogo.jogadorDefensor().getMaquinas()) {
            desenhosMaquinasJogadorInativo.put(maquina.getLinha() + "" + maquina.getColuna(), maquina.caminhoImagemDirecaoAtual());
        }
        for(ObserverTelaJogo observer : observers) {
            observer.redesenharMaquinas(desenhosMaquinasJogadorAtivo, desenhosMaquinasJogadorInativo);
        }
    }
    
    public void limparCampos() {
        campoDeAtaque = new HashSet<>();
        campoDeCorrida = new HashSet<>();
        campoDeMovimento = new HashSet<>();
    }
    
    private void desenharCampoDeMovimento(Maquina maquina) {
        redesenharMaquinas();
        gerarSetCampoDeMovimento(maquina);
        for(ObserverTelaJogo observer : observers) {
            observer.desenharCamposDeMovimento(campoDeMovimento);
        }
    }
    
    private void desenharCampoDeCorrida(Maquina maquina) {
        redesenharMaquinas();
        gerarSetCampoDeCorrida(maquina);
        for(ObserverTelaJogo observer : observers) {
            observer.desenharCamposDeMovimento(campoDeCorrida);
        }
    }
    
    private void desenharCampoDeAtaque(Maquina maquina) throws SemMaquinaNoCampoAtaqueException {
        redesenharMaquinas();
        gerarSetCampoDeAtaque(maquina);
        if(campoDeAtaque.size() > 0) {
            HashMap<String, String> caminhoImagens = new HashMap<>();
            for(String posicao : campoDeAtaque) {
                int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
                int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
                caminhoImagens.put(posicao, jogo.getTabuleiro().getMaquinaPorPosicao(linha, coluna).caminhoImagemDirecaoAtual());
            }
            for(ObserverTelaJogo observer : observers) {
                observer.desenharCampoDeAtaque(caminhoImagens);
            }
        } else {
            throw new SemMaquinaNoCampoAtaqueException();
        }
    }
    
    public void atualizarLblJogadorAtivo() {
        for(ObserverTelaJogo observer : observers) {
            observer.atualizarLblJogadorAtivo(getInformacoesHeaderJogadorAtual());
        }
    }
    
    public void ativarPainelAtacante() {
        if(maquinaSelecionada != null) {
            for(ObserverTelaJogo observer : observers) {
                observer.mudarEstadoBtnMover(maquinaSelecionada.isMoverAtivo());
                observer.mudarEstadoBtnCorrer(maquinaSelecionada.isCorrerAtivo());
                observer.mudarEstadoBtnAtacar(maquinaSelecionada.isAtacarAtivo());
                observer.mudarEstadoBtnSobrecarregar(maquinaSelecionada.isSobrecarregarAtivo());
                observer.mudarEstadoBtnGirar(true);
                observer.atualizarCardMaquinaAtacante(getInformacoesMaquina(maquinaSelecionada));
            }
        }
    }
    
    public void ativarPainelDefensor(Maquina maquina) {
        if(maquina != null) {
            for(ObserverTelaJogo observer : observers) {
                observer.atualizarCardMaquinaDefensora(getInformacoesMaquina(maquina));
            }
        }
    }
    
    public void desativarPainelAtacante() {
        for(ObserverTelaJogo observer : observers) {
            observer.desativarBotoes();
            observer.atualizarCardMaquinaAtacante(getInformacoesMaquina());
        }
    }
    
    public void desativarPainelDefensor() {
        for(ObserverTelaJogo observer : observers) {
            observer.atualizarCardMaquinaDefensora(getInformacoesMaquina());
        }
    }
    
    public void anunciarGanhador(Jogador jogador) {
        for(ObserverTelaJogo observer : observers) {
            observer.anunciarGanhador(jogador.getEnumNome().getNome());
        }
    }
    
    public void selecionarQuadrado(String posicao) throws Exception {
        stateAcaoAtiva.fazerAcao(posicao);
    }
    
    public void clicarBotaoAtacar() throws LimiteDeAcoesException, SemMaquinaNoCampoAtaqueException {
        if(!stateAcaoAtiva.estaAtacando()) {
            if(jogo.getMaquinasQueRealizaramAcoes().contains(maquinaSelecionada) || jogo.getMaquinasQueRealizaramAcoes().size() < 2) {
                try {
                    desenharCampoDeAtaque(maquinaSelecionada);
                    stateAcaoAtiva.ativarAtacar();
                } catch(SemMaquinaNoCampoAtaqueException ex) {
                    throw new SemMaquinaNoCampoAtaqueException();
                }
            } else {
                throw new LimiteDeAcoesException();
            }
        } else {
            redesenharMaquinas();
            stateAcaoAtiva.ativarNeutro();
        }
    }
    
    public void clicarBotaoCorrer() throws LimiteDeAcoesException {
        if(!stateAcaoAtiva.estaCorrendo()) {
            if(jogo.getMaquinasQueRealizaramAcoes().contains(maquinaSelecionada) || jogo.getMaquinasQueRealizaramAcoes().size() < 2) {
                stateAcaoAtiva.ativarCorrer();
                desenharCampoDeCorrida(maquinaSelecionada);
            } else {
                throw new LimiteDeAcoesException();
            }
        } else {
            redesenharMaquinas();
            stateAcaoAtiva.ativarNeutro();
        }
    }
    
    public void clicarBotaoEncerrar() throws MinimoDeMovimentoException {
        if(jogo.getMaquinasQueMoveram().size() == 2 || (jogo.jogadorAtivo().getMaquinas().size() < 2 && jogo.getMaquinasQueMoveram().size() > 0)) {
            jogo.passarTurno();
            stateAcaoAtiva.ativarNeutro();
            maquinaSelecionada = null;
            desativarPainelAtacante();
            desativarPainelDefensor();
            redesenharMaquinas();
            for(ObserverTelaJogo observer : observers) {
                observer.atualizarLblJogadorAtivo(getInformacoesHeaderJogadorAtual());
            }
        } else if(jogo.jogadorAtivo().getMaquinas().size() < 2) {
            throw new MinimoDeMovimentoException("Voce deve mover sua maquina para poder encerrar seu turno");
        } else {
            throw new MinimoDeMovimentoException();
        }
    }
    
    public void clicarBotaoGirar() throws Exception {
        if(jogo.getMaquinasQueRealizaramAcoes().contains(maquinaSelecionada) || jogo.getMaquinasQueRealizaramAcoes().size() < 2) {
            CommandFactory cf = CommandFactory.getInstancia();
            CommandInvoker ci = CommandInvoker.getInstancia();
            cf.setObserver(this);
            Command comm = cf.getComando("girar", new Object[]{maquinaSelecionada});
            ci.execute(comm);
        } else {
            throw new LimiteDeAcoesException();
        }
    }
    
    public void clicarBotaoMover() throws LimiteDeAcoesException {
        if(!stateAcaoAtiva.estaMovendo()) {
            if(jogo.getMaquinasQueRealizaramAcoes().contains(maquinaSelecionada) || jogo.getMaquinasQueRealizaramAcoes().size() < 2) {
                stateAcaoAtiva.ativarMover();
                desenharCampoDeMovimento(maquinaSelecionada);
            } else {
                throw new LimiteDeAcoesException();
            }
        } else {
            redesenharMaquinas();
            stateAcaoAtiva.ativarNeutro();
        }
    }
    
    public void clicarBotaoSobrecarregar() throws Exception {
        if(jogo.getMaquinasQueRealizaramAcoes().contains(maquinaSelecionada) || jogo.getMaquinasQueRealizaramAcoes().size() < 2) {
            stateAcaoAtiva.ativarSobrecarregar();
            stateAcaoAtiva.fazerAcao(null);
        } else {
            throw new LimiteDeAcoesException();
        }
    }
    
    private void gerarSetCampoDeMovimento(Maquina maquina) {
        Set<String> todasChaves = jogo.getTabuleiro().getTerrenos().keySet();
        for(String chave : todasChaves) {
            int linha = Integer.parseInt(String.valueOf(chave.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(chave.charAt(1)));
            if(maquina.podeMover(linha, coluna, jogo.getTabuleiro().getTerrenoPorPosicao(linha + "" + coluna), jogo.getTabuleiro().getMaquinas())) {
                campoDeMovimento.add(chave);
            }
        }
        for(Maquina m : jogo.getTabuleiro().getMaquinas()) {
            int linAtq = maquinaSelecionada.getLinha();
            int linDef = m.getLinha();
            int colAtq = maquinaSelecionada.getColuna();
            int colDef = m.getColuna();
            int movAtq = maquinaSelecionada.getMovimento();
            if(!(linAtq == linDef && colAtq == colDef)) {
                if(((Math.abs(linAtq - linDef) - Math.abs(colAtq - colDef)) <= movAtq)) {
                    if(linAtq == linDef) {
                        if(colAtq < colDef) {
                            for(int i = (colDef + 1); i <= 7; i++) {
                                if(Math.abs(colAtq - i) >= (movAtq - 1)) {
                                    campoDeMovimento.remove(linDef + "" + i);
                                }
                            }
                        } else {
                            for(int i = (colDef - 1); i >= 0; i--) {
                                if(Math.abs(colAtq - i) >= (movAtq - 1)) {
                                    campoDeMovimento.remove(linDef + "" + i);
                                }
                            }
                        }
                    } else if(colAtq == colDef) {
                        if(linAtq < linDef) {
                            for(int i = (linDef + 1); i <= 7; i++) {
                                if(Math.abs(linAtq - i) >= (movAtq - 1)) {
                                    campoDeMovimento.remove(i + "" + colDef);
                                }
                            }
                        } else {
                            for(int i = (linDef - 1); i >= 0; i--) {
                                if(Math.abs(linAtq - i) >= (movAtq - 1)) {
                                    campoDeMovimento.remove(i + "" + colDef);
                                }
                            }
                        }
                    }
                }
            }
        }
        HashSet<String> remover = new HashSet<>();
        for(String posicao : campoDeMovimento) {
            int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            if (maquinaSelecionada.getLinha() != linha && maquinaSelecionada.getColuna() != coluna) {
                Maquina baixo = jogo.getTabuleiro().getMaquinaPorPosicao(linha + 1, coluna);
                Maquina cima = jogo.getTabuleiro().getMaquinaPorPosicao(linha - 1, coluna);
                Maquina direita = jogo.getTabuleiro().getMaquinaPorPosicao(linha, coluna + 1);
                Maquina esquerda = jogo.getTabuleiro().getMaquinaPorPosicao(linha, coluna - 1);
                if(maquinaSelecionada.getLinha() < linha) {
                    if(maquinaSelecionada.getColuna() < coluna) {
                        if((cima != null) && (esquerda != null)) {
                            if (!cima.equals(maquinaSelecionada) && !esquerda.equals(maquinaSelecionada)) {
                                remover.add(posicao);
                            }
                        }
                    } else {
                        if((cima != null) && (direita != null)) {
                            if (!cima.equals(maquinaSelecionada) && !direita.equals(maquinaSelecionada)) {
                                remover.add(posicao);
                            }
                        }
                    }
                } else {
                    if(maquinaSelecionada.getColuna() < coluna) {
                        if((baixo != null) && (esquerda != null)) {
                            if (!baixo.equals(maquinaSelecionada) && !esquerda.equals(maquinaSelecionada)) {
                                remover.add(posicao);
                            }
                        }
                    } else {
                        if((baixo != null) && (direita != null)) {
                            if (!cima.equals(maquinaSelecionada) && !direita.equals(maquinaSelecionada)) {
                                remover.add(posicao);
                            }
                        }
                    }
                }
            }
        }
        if(remover.size() > 0) {
            campoDeMovimento.removeAll(remover);
        }
    }
    
    private void gerarSetCampoDeCorrida(Maquina maquina) {
        Set<String> todasChaves = jogo.getTabuleiro().getTerrenos().keySet();
        for(String chave : todasChaves) {
            int linha = Integer.parseInt(String.valueOf(chave.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(chave.charAt(1)));
            if(maquina.podeCorrer(linha, coluna, jogo.getTabuleiro().getTerrenoPorPosicao(linha + "" + coluna), jogo.getTabuleiro().getMaquinas())) {
                campoDeCorrida.add(chave);
            }
        }
        for(Maquina m : jogo.getTabuleiro().getMaquinas()) {
            int linAtq = maquinaSelecionada.getLinha();
            int linDef = m.getLinha();
            int colAtq = maquinaSelecionada.getColuna();
            int colDef = m.getColuna();
            int correrAtq = maquinaSelecionada.getMovimento() + 1;
            if(!(linAtq == linDef && colAtq == colDef)) {
                if((Math.abs(linAtq - linDef) <= correrAtq) || (Math.abs(colAtq - colDef) <= correrAtq)) {
                    if(colAtq < colDef) {
                        for(int i = (colDef + 1); i <= 7; i++) {
                            if(Math.abs(colAtq - i) >= (correrAtq - 1)) {
                                campoDeCorrida.remove(linDef + "" + i);
                            }
                        }
                    } else if(colAtq > colDef) {
                        for(int i = (colDef - 1); i >= 0; i--) {
                            if(Math.abs(colAtq - i) >= (correrAtq - 1)) {
                                campoDeCorrida.remove(linDef + "" + i);
                            }
                        }
                    }
                    else if(linAtq < linDef) {
                        for(int i = (linDef + 1); i <= 7; i++) {
                            if(Math.abs(linAtq - i) >= (correrAtq - 1)) {
                                campoDeCorrida.remove(i + "" + colDef);
                            }
                        }
                    } else if(linAtq > linDef) {
                        for(int i = (linDef - 1); i >= 0; i--) {
                            if(Math.abs(linAtq - i) >= (correrAtq - 1)) {
                                campoDeCorrida.remove(i + "" + colDef);
                            }
                        }
                    }
                }
            }
        }
        for (String posicao : jogo.getTabuleiro().getTerrenos().keySet()) {
            int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            if(maquina.podeMover(linha, coluna, jogo.getTabuleiro().getTerrenoPorPosicao(linha + "" + coluna), jogo.getTabuleiro().getMaquinas())) {
                campoDeCorrida.remove(posicao);
            }
        }
//        gerarSetCampoDeMovimento(maquina);
//        for(String posicao : campoDeMovimento) {
//            campoDeCorrida.remove(posicao);
//        }
//        campoDeMovimento = new HashSet<>();
    }
    
    private void gerarSetCampoDeAtaque(Maquina maquina) {
        Set<String> todasChaves = jogo.getTabuleiro().getTerrenos().keySet();
        for(String chave : todasChaves) {
            int linha = Integer.parseInt(String.valueOf(chave.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(chave.charAt(1)));
            if(jogo.getTabuleiro().getMaquinaPorPosicao(linha, coluna) != null) {
                if(maquina.podeAtacar(jogo.getTabuleiro().getMaquinaPorPosicao(linha, coluna), jogo.getTabuleiro())) {
                    campoDeAtaque.add(chave);
                }
            }
        }
    }
    
    private HashMap<String, String> getInformacoesMaquina() {
        HashMap<String, String> resposta = new HashMap<>();
        resposta.put("CaminhoImagem", "src/images/Bloqueado.png");
        resposta.put("Nome", "");
        resposta.put("PV", "");
        resposta.put("Vida", "");
        resposta.put("Ataque", "");
        resposta.put("Alcance", "");
        resposta.put("Movimento", "");
        return resposta;
    }
    
    private HashMap<String, String> getInformacoesMaquina(Maquina maquina) {
        HashMap<String, String> resposta = new HashMap<>();
        resposta.put("CaminhoImagem", maquina.caminhoImagemDirecaoFixa());
        resposta.put("Nome", maquina.getNome());
        resposta.put("PV", String.valueOf(maquina.getPontosVitoria()));
        resposta.put("Vida", String.valueOf(maquina.getVida()));
        resposta.put("Ataque", String.valueOf(maquina.getAtaque()));
        resposta.put("Alcance", String.valueOf(maquina.getAlcance()));
        resposta.put("Movimento", String.valueOf(maquina.getMovimento()));
        return resposta;
    }
    
    public String getInformacoesHeaderJogadorAtual() {
        return jogo.jogadorAtivo().getEnumNome().getNome() + " PV: " + jogo.getJogador(jogo.jogadorAtivo().getEnumNome()).getPontosVitoria();
    }
    
    public void setStateAcaoAtiva(StateAcaoAtiva stateAcaoAtiva) {
        this.stateAcaoAtiva = stateAcaoAtiva;
    }
    
    public Jogo getJogo() {
        return jogo;
    }
    
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
    
    public Maquina getMaquinaSelecionada() {
        return maquinaSelecionada;
    }
    
    public void setMaquinaSelecionada(Maquina maquinaSelecionada) {
        this.maquinaSelecionada = maquinaSelecionada;
    }
    
    public Set<String> getCampoDeMovimento() {
        return campoDeMovimento;
    }
    
    public Set<String> getCampoDeCorrida() {
        return campoDeCorrida;
    }
    
    public Set<String> getCampoDeAtaque() {
        return campoDeAtaque;
    }
    
    public void navegarParaOutraTela(String caminho) throws Exception {
        AbstractFactoryTela factoryTela = (AbstractFactoryTela) Class.forName(caminho).getDeclaredConstructor().newInstance();
        for(ObserverTelaJogo observer : observers) {
            factoryTela.construirTela();
            observer.navegarParaOutraTela();
        }
    }
}
