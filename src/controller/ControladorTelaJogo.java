package controller;

//CONTROLLER
import controller.command.Command;
import controller.command.CommandFactory;
import controller.command.CommandInvoker;
import controller.observer.ObserverCommand;
import controller.observer.ObserverTelaJogo;
import controller.singleton.SingletonConfiguracaoJogo;
import view.abstractFactoryTela.AbstractFactoryTela;

//GLOBAL
import global.EnumTipoTerreno;

//MODEL
import model.*;

//JAVA
import java.util.*;

public class ControladorTelaJogo implements ObserverCommand {

    private Jogo jogo;
    private List<ObserverTelaJogo> observers;
    private Set<String> campoDeMovimento;
    private Set<String> campoDeCorrida;
    private Maquina maquinaSelecionada;
    private Boolean isMoverAtivo;
    private Boolean isCorrerAtivo;
    CommandFactory cf = CommandFactory.getInstancia();
    CommandInvoker ci = CommandInvoker.getInstancia();

    public ControladorTelaJogo() {
        observers = new ArrayList<>();
        campoDeMovimento = new HashSet<>();
        campoDeCorrida = new HashSet<>();
        maquinaSelecionada = null;
        isMoverAtivo = false;
        isCorrerAtivo = false;
        cf.attach(this);
        jogo = new Jogo(SingletonConfiguracaoJogo.getInstancia().getTabuleiro(), SingletonConfiguracaoJogo.getInstancia().getJogadores());
    }

    public void attach(ObserverTelaJogo observer) {
        observers.add(observer);
    }

    public void desenharJogoInicial() {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        HashMap<String, Terreno> terrenos = tabuleiro.getTerrenos();
        Set<String> posicoes = terrenos.keySet();
        HashMap<String, EnumTipoTerreno> tiposTerrenos = new HashMap<>();
        for (String posicao : posicoes) {
            tiposTerrenos.put(posicao, terrenos.get(posicao).getTipo());
        }
        List<Maquina> maquinas = tabuleiro.getMaquinas();
        HashMap<String, String> desenhosMaquinas = new HashMap<>();
        for (Maquina maquina : maquinas) {
            desenhosMaquinas.put(maquina.getLinha()+""+maquina.getColuna(), maquina.caminhoImagemDirecaoAtual());
        }
        for (ObserverTelaJogo observer:observers) {
            observer.desenharTabuleiro(tiposTerrenos);
        }
        for (ObserverTelaJogo observer:observers) {
            observer.desenharQuadrados(desenhosMaquinas);
        }
    }

    @Override
    public void redesenharMaquinas() {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        List<Maquina> maquinas = tabuleiro.getMaquinas();
        HashMap<String, String> desenhosMaquinas = new HashMap<>();
        for (Maquina maquina : maquinas) {
            desenhosMaquinas.put(maquina.getLinha()+""+maquina.getColuna(), maquina.caminhoImagemDirecaoAtual());
        }
        for (ObserverTelaJogo observer:observers) {
            observer.apagarCampoDeMovimento(tabuleiro.getTerrenos().keySet());
            observer.desenharQuadrados(desenhosMaquinas);
        }
    }

    private void desenharCampoDeMovimento(Maquina maquina) {
        apagarCampoDeCorrida();
        isCorrerAtivo = false;
        gerarSetCampoDeMovimento(maquina);
        for (ObserverTelaJogo observer:observers) {
            observer.desenharCampoDeMovimento(campoDeMovimento);
        }
    }

    private void apagarCampoDeMovimento() {

        for (ObserverTelaJogo observer:observers) {
            observer.apagarCampoDeMovimento(campoDeMovimento);
        }
        limparSetCampoDeMovimento();
    }

    private void desenharCampoDeCorrida(Maquina maquina) {
        apagarCampoDeMovimento();
        isMoverAtivo = false;
        gerarSetCampoDeCorrida(maquina);
        for (ObserverTelaJogo observer:observers) {
            observer.desenharCampoDeMovimento(campoDeCorrida);
        }
    }

    private void apagarCampoDeCorrida() {
        for (ObserverTelaJogo observer:observers) {
            observer.apagarCampoDeMovimento(campoDeCorrida);
        }
        limparSetCampoDeCorrida();
    }

    public void clicarBotaoMover() {
        isMoverAtivo = !isMoverAtivo;
        if (isMoverAtivo) {
            apagarCampoDeMovimento();
            desenharCampoDeMovimento(maquinaSelecionada);
        } else {
            apagarCampoDeMovimento();
        }
    }

    public void clicarBotaoCorrer() {
        isCorrerAtivo = !isCorrerAtivo;
        if (isCorrerAtivo) {
            apagarCampoDeCorrida();
            desenharCampoDeCorrida(maquinaSelecionada);
        } else {
            apagarCampoDeCorrida();
        }
    }

    public void clicarBotaoGirar() {
        Command comm = cf.getComando("girar", new Object[]{maquinaSelecionada});
        ci.execute(comm);
    }

    public void selecionarQuadrado(String posicao) {
        int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
        int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
        if (jogo.getJogador(jogo.nomeJogadorAtivo()).getMaquinaPorPosicao(linha, coluna) != null) {
            maquinaSelecionada = jogo.getTabuleiro().getMaquinaPorPosicao(linha, coluna);
            for (ObserverTelaJogo observer:observers) {
                observer.mudarEstadoBtnMover(maquinaSelecionada.isMoverAtivo());
                observer.mudarEstadoBtnCorrer(maquinaSelecionada.isCorrerAtivo());
                observer.mudarEstadoBtnGirar(true);
                observer.atualizarCardMaquinaAtacante(getInformacoesMaquina(maquinaSelecionada));
            }
        } else if (isMoverAtivo && campoDeMovimento.contains(posicao)) {
            isMoverAtivo = false;
            apagarCampoDeMovimento();
            Command comm = cf.getComando("mover", new Object[]{maquinaSelecionada, linha, coluna});
            ci.execute(comm);
            maquinaSelecionada = null;
            for (ObserverTelaJogo observer:observers) {
                observer.desativarBotoes();
                observer.atualizarCardMaquinaAtacante(getInformacoesMaquina());
            }
        } else if (isCorrerAtivo && campoDeCorrida.contains(posicao)) {
            isCorrerAtivo = false;
            apagarCampoDeCorrida();
            Command comm = cf.getComando("correr", new Object[]{maquinaSelecionada, linha, coluna});
            ci.execute(comm);
            maquinaSelecionada = null;
            for (ObserverTelaJogo observer:observers) {
                observer.desativarBotoes();
                observer.atualizarCardMaquinaAtacante(getInformacoesMaquina());
            }
        } else {
            maquinaSelecionada = null;
            isMoverAtivo = false;
            apagarCampoDeMovimento();
            for (ObserverTelaJogo observer:observers) {
                observer.mudarEstadoBtnMover(false);
                observer.atualizarCardMaquinaAtacante(getInformacoesMaquina());
            }
        }
    }

    private void gerarSetCampoDeMovimento(Maquina maquina) {
        Set<String> todasChaves = jogo.getTabuleiro().getTerrenos().keySet();
        for (String chave : todasChaves) {
            int linha = Integer.parseInt(String.valueOf(chave.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(chave.charAt(1)));
            if (maquina.podeMover(linha, coluna)) {
                campoDeMovimento.add(chave);
            }
        }
        for (Maquina m : jogo.getTabuleiro().getMaquinas()) {
            campoDeMovimento.remove(m.getLinha()+""+ m.getColuna());
        }
    }

    private void limparSetCampoDeMovimento() {
        campoDeMovimento = new HashSet<>();
    }

    private void gerarSetCampoDeCorrida(Maquina maquina) {
        Set<String> todasChaves = jogo.getTabuleiro().getTerrenos().keySet();
        for (String chave : todasChaves) {
            int linha = Integer.parseInt(String.valueOf(chave.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(chave.charAt(1)));
            if (maquina.podeCorrer(linha, coluna)) {
                campoDeCorrida.add(chave);
            }
        }
        for (Maquina m : jogo.getTabuleiro().getMaquinas()) {
            campoDeCorrida.remove(m.getLinha()+""+ m.getColuna());
        }
        gerarSetCampoDeMovimento(maquina);
        for (String posicao : campoDeMovimento) {
            campoDeCorrida.remove(posicao);
        }
        limparSetCampoDeMovimento();
    }

    private void limparSetCampoDeCorrida() {
        campoDeCorrida = new HashSet<>();
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
        resposta.put("CaminhoImagem", maquina.caminhoImagemDirecaoAtual());
        resposta.put("Nome", maquina.getNome());
        resposta.put("PV", String.valueOf(maquina.getPontosVitoria()));
        resposta.put("Vida", String.valueOf(maquina.getVida()));
        resposta.put("Ataque", String.valueOf(maquina.getAtaque()));
        resposta.put("Alcance", String.valueOf(maquina.getAlcance()));
        resposta.put("Movimento", String.valueOf(maquina.getMovimento()));
        return resposta;
    }

    public String getNomeJogadorAtual() {
        return jogo.nomeJogadorAtivo().getNome();
    }

    public void navegarParaOutraTela(String caminho) {
        try {
            AbstractFactoryTela factoryTela = (AbstractFactoryTela) Class.forName(caminho).getDeclaredConstructor().newInstance();
            for (ObserverTelaJogo observer : observers) {
                observer.navegarParaOutraTela(factoryTela.construirTela());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
