package controller.controlador;

//CONTROLLER
import controller.command.Command;
import controller.command.CommandFactory;
import controller.command.CommandInvoker;
import controller.observer.ObserverCommand;
import controller.observer.ObserverTelaJogo;
import controller.singleton.SingletonConfiguracaoJogo;
import controller.abstractFactoryTela.AbstractFactoryTela;

//GLOBAL
import global.EnumTipoTerreno;

//MODEL
import model.*;

//JAVA
import java.util.*;

public class ControladorTelaJogo implements ObserverCommand {

    private Jogo jogo;
    private List<ObserverTelaJogo> observers;
    private Set<String> quadradosSelecionados;
    private Maquina maquinaSelecionada;
    private Boolean estaMovendo;
    CommandFactory cf = CommandFactory.getInstancia();
    CommandInvoker ci = CommandInvoker.getInstancia();

    public ControladorTelaJogo() {
        observers = new ArrayList<>();
        quadradosSelecionados = new HashSet<>();
        maquinaSelecionada = null;
        estaMovendo = false;
        cf.attach(this);
        jogo = new Jogo(SingletonConfiguracaoJogo.getInstancia().getTabuleiro(), SingletonConfiguracaoJogo.getInstancia().getJogadores());
    }

    public void attach(ObserverTelaJogo observer) {
        observers.add(observer);
    }

    public void desenharJogo() {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        HashMap<String, Terreno> terrenos = tabuleiro.getTerrenos();
        Set<String> posicoes = terrenos.keySet();
        HashMap<String, EnumTipoTerreno> tiposTerrenos = new HashMap<>();
        for (String posicao : posicoes) {
            tiposTerrenos.put(posicao, terrenos.get(posicao).getTipo());
        }
        HashMap<String, Maquina> maquinas = tabuleiro.getMaquinas();
        posicoes = maquinas.keySet();
        HashMap<String, String> desenhosMaquinas = new HashMap<>();
        for (String posicao : posicoes) {
            desenhosMaquinas.put(posicao, maquinas.get(posicao).caminhoImagemDirecaoAtual());
        }
        for (ObserverTelaJogo observer:observers) {
            observer.desenharTabuleiro(tiposTerrenos);
        }
        for (ObserverTelaJogo observer:observers) {
            observer.desenharQuadrados(desenhosMaquinas);
        }
    }

    public void atualizarDesenhoJogo() {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        HashMap<String, Terreno> terrenos = tabuleiro.getTerrenos();
        HashMap<String, Maquina> maquinas = tabuleiro.getMaquinas();
        Set<String> posicoesTerrenos = terrenos.keySet();
        Set<String> posicoesMaquinas = maquinas.keySet();
        Set<String> posicoesVazias = new HashSet<>();
        for (String posicao : posicoesTerrenos) {
            if (!posicoesMaquinas.contains(posicao))
                posicoesVazias.add(posicao);
        }
        HashMap<String, String> desenhosMaquinas = new HashMap<>();
        for (String posicao : posicoesMaquinas) {
            desenhosMaquinas.put(posicao, maquinas.get(posicao).caminhoImagemDirecaoAtual());
        }
        for (ObserverTelaJogo observer:observers) {
            observer.desenharQuadrados(posicoesVazias);
        }
        for (ObserverTelaJogo observer:observers) {
            observer.desenharQuadrados(desenhosMaquinas);
        }
    }

    public void trocarEstadoMover() {
        if (maquinaSelecionada != null) {
            estaMovendo = true;
            Set<String> campoDeAtaque = gerarCampoDeAtaque(maquinaSelecionada);
            Set<String> campoDeAtaqueVisual = filtrarCampoDeAtaque(campoDeAtaque, jogo.getTabuleiro().getMaquinas().keySet());
            for (ObserverTelaJogo observer : observers) {
                observer.desenharQuadradosSelecionados(quadradosSelecionados, campoDeAtaqueVisual);
            }
            quadradosSelecionados = campoDeAtaqueVisual;
        } else {
            estaMovendo = false;
            for (ObserverTelaJogo observer : observers) {
                observer.atualizarCardMaquinaAtacante(getInformacoesMaquina());
                observer.desenharQuadradosSelecionados(quadradosSelecionados, null);
                quadradosSelecionados.clear();
            }
        }

    }

    public String getNomeJogadorAtual() {
        return jogo.nomeJogadorAtivo().getNome();
    }

    public void selecionarQuadrado(String posicao) {
        if (jogo.getTabuleiro().getMaquinas().containsKey(posicao)) {
            if (jogo.getJogador(jogo.nomeJogadorAtivo()).getMaquinas().containsKey(posicao)) {
                maquinaSelecionada = jogo.getTabuleiro().getMaquinaPorIndice(posicao);
                for (ObserverTelaJogo observer : observers) {
                    observer.atualizarCardMaquinaAtacante(getInformacoesMaquina(maquinaSelecionada));
                }
            } else {
                for (ObserverTelaJogo observer : observers) {
                    observer.atualizarCardMaquinaAtacante(getInformacoesMaquina());
                }
                maquinaSelecionada = null;
                trocarEstadoMover();
            }
        } else if (quadradosSelecionados.contains(posicao) && maquinaSelecionada != null) {
            int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            Command comm = cf.getComando("mover", new Object[]{maquinaSelecionada, linha, coluna});
            ci.execute(comm);
            atualizarDesenhoJogo();
            for (ObserverTelaJogo observer : observers) {
                observer.atualizarCardMaquinaAtacante(getInformacoesMaquina());
                observer.desenharQuadradosSelecionados(quadradosSelecionados, null);
                quadradosSelecionados.clear();
                maquinaSelecionada = null;
                trocarEstadoMover();
            }
        } else {
            for (ObserverTelaJogo observer : observers) {
                observer.atualizarCardMaquinaAtacante(getInformacoesMaquina());
            }
            maquinaSelecionada = null;
            trocarEstadoMover();
        }
    }

    public void atualizarListasDeMaquinas(String posicao) {
        HashMap<String, Maquina> maquinasTabuleiro = jogo.getTabuleiro().getMaquinas();
        Map<String, Maquina> maquinasJogador = jogo.getJogador(jogo.nomeJogadorAtivo()).getMaquinas();
        int linha = maquinaSelecionada.getLinha();
        int coluna = maquinaSelecionada.getColuna();
        maquinasTabuleiro.put(linha+""+coluna, maquinasTabuleiro.remove(posicao));
        maquinasJogador.put(linha+""+coluna, maquinasJogador.remove(posicao));
        quadradosSelecionados.remove(linha+""+coluna);
    }

    private Set<String> gerarCampoDeAtaque(Maquina maquina) {
        Set<String> campoDeAtaque = new HashSet<>();
        int linhaMaquina = maquina.getLinha();
        int colunaMaquina = maquina.getColuna();
        int alcance = maquina.getAlcance();
        int inicioLinha = ((linhaMaquina-alcance) < 0) ? 0 : (linhaMaquina-alcance);
        int inicioColuna = ((colunaMaquina-alcance) < 0) ? 0 : (colunaMaquina-alcance);
        int fimLinha = ((linhaMaquina+alcance) > 7) ? 7 : (linhaMaquina+alcance);
        int fimColuna = ((colunaMaquina+alcance) > 7) ? 7 : (colunaMaquina+alcance);
        for (int i = inicioLinha; i < fimLinha+1; i++) {
            for (int j = inicioColuna; j < fimColuna+1; j++) {
                if ((Math.abs(linhaMaquina - i)+Math.abs(colunaMaquina-j)) != 0 && (Math.abs(linhaMaquina - i)+Math.abs(colunaMaquina-j)) <= alcance) {
                    campoDeAtaque.add(i+""+j);
                }
            }
        }
        return campoDeAtaque;
    }

    private Set<String> filtrarCampoDeAtaque(Set<String> campoDeAtaque, Set<String> maquinas) {
        Set<String> campoDeAtaqueVisual = new HashSet<>();
        for (String campo: campoDeAtaque) {
            if (!maquinas.contains(campo)) {
                campoDeAtaqueVisual.add(campo);
            }
        }
        return campoDeAtaqueVisual;
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

    public HashMap<String, String> getInformacoesMaquina() {
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
