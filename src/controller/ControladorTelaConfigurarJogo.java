package controller;

//CONTROLLER

import controller.adapter.ObjetoArquivoAdapter;
import controller.singleton.SingletonConfiguracaoJogo;
import controller.observer.ObserverTelaConfigurarJogo;

//GLOBAL

//JAVA
import java.lang.reflect.Constructor;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

//MODEL
import controller.singleton.SingletonTipoDeArquivo;
import global.Enum.*;
import global.Exception.LimiteDeMaquinasException;
import global.Exception.MaquinaEmTerrenoInvalidoException;
import global.Exception.MinimoMaquinasException;
import global.Exception.SubstituicaoInvalidaException;
import model.Terreno;
import model.Jogador;
import model.Tabuleiro;
import model.maquinas.Maquina;
import model.builderTerreno.DirectorTerreno;
import model.builderTerreno.ConstruirTerreno;
import model.builderTabuleiro.DirectorTabuleiro;
import model.abstractFactoryMaquina.AbstractFactoryMaquina;
import model.builderTabuleiro.ConstruirTabuleiroComMaquinas;
import model.builderTabuleiro.ConstruirTabuleiroSemMaquinas;

//VIEW ABSTRACT FACTORY
import view.abstractFactoryTela.AbstractFactoryTela;

public class ControladorTelaConfigurarJogo {
    
    private DirectorTerreno directorTerreno;
    private final File[] arquivosTabuleiros;
    private DirectorTabuleiro directorTabuleiro;
    private AbstractFactoryMaquina factoryMaquina;
    private LinkedHashMap<EnumJogador, Jogador> jogadores;
    private List<ObserverTelaConfigurarJogo> observers;
    private LinkedHashMap<String, Tabuleiro> tabuleiros;
    private ConstruirTabuleiroSemMaquinas construirTabuleiro;
    
    public ControladorTelaConfigurarJogo() throws Exception {
        observers = new ArrayList<>();
        arquivosTabuleiros = new File(SingletonTipoDeArquivo.getInstancia().getCaminhoArquivos()).listFiles();
        construirTabuleiros();
        iniciarJogadores();
    }
    
    private void construirTabuleiros() throws Exception {
        tabuleiros = new LinkedHashMap<>();
        for(File arquivo : arquivosTabuleiros) {
            Class<?>[] parametroObjeto = new Class<?>[]{File.class};
            Class<?>[] parametroAdapter = new Class<?>[]{SingletonTipoDeArquivo.getInstancia().getClasseObjeto()};
            Constructor<?> construtorObjeto = SingletonTipoDeArquivo.getInstancia().getClasseObjeto().getConstructor(parametroObjeto);
            Constructor<? extends ObjetoArquivoAdapter> construtorAdapter = SingletonTipoDeArquivo.getInstancia().getClasseAdapter().getConstructor(parametroAdapter);
            Object objetoArquivo = construtorObjeto.newInstance(arquivo);
            ObjetoArquivoAdapter objetoArquivoAdapter = construtorAdapter.newInstance(objetoArquivo);
            ArrayList<ArrayList<String>> conteudo = objetoArquivoAdapter.getConteudo();
            String nome = conteudo.get(0).get(0);
            String tipo = conteudo.get(1).get(0);
            EnumTipoTabuleiro tipoTabuleiro = EnumTipoTabuleiro.PADRAO;
            if(!tipo.equals("padrao")) {
                tipoTabuleiro = EnumTipoTabuleiro.CRIADO;
            }
            ArrayList<ArrayList<String>> tiposTerrenos = new ArrayList<>();
            for(int l = 2; l < conteudo.size(); l++) {
                tiposTerrenos.add(conteudo.get(l));
            }
            HashMap<String, Terreno> terrenos = new HashMap<>();
            construirTabuleiro = new ConstruirTabuleiroSemMaquinas();
            directorTabuleiro = new DirectorTabuleiro(construirTabuleiro);
            for(int linha = 0; linha < 8; linha++) {
                for(int coluna = 0; coluna < 8; coluna++) {
                    ConstruirTerreno builder = (ConstruirTerreno) Class.forName("model.builderTerreno.Construir" + tiposTerrenos.get(linha).get(coluna)).getDeclaredConstructor().newInstance();
                    directorTerreno = new DirectorTerreno(builder);
                    directorTerreno.construir();
                    terrenos.put(linha + "" + coluna, builder.getTerreno());
                }
            }
            directorTabuleiro.construir(tipoTabuleiro, terrenos, null);
            tabuleiros.put(nome, construirTabuleiro.getTabuleiro());
        }
    }
    
    private void iniciarJogadores() {
        jogadores = new LinkedHashMap<>();
        Jogador jogador1 = new Jogador(EnumJogador.JOGADOR1);
        Jogador jogador2 = new Jogador(EnumJogador.JOGADOR2);
        jogadores.put(EnumJogador.JOGADOR1, jogador1);
        jogadores.put(EnumJogador.JOGADOR2, jogador2);
    }
    
    public void desenharTabuleiro(String chaveTabuleiro) {
        HashMap<String, Terreno> terrenos = tabuleiros.get(chaveTabuleiro).getTerrenos();
        Set<String> posicoes = terrenos.keySet();
        HashMap<String, EnumTipoTerreno> tiposTerreno = new HashMap<>();
        for(String posicao : posicoes) {
            tiposTerreno.put(posicao, terrenos.get(posicao).getTipo());
        }
        for(ObserverTelaConfigurarJogo observer : observers) {
            observer.desenharTabuleiro(tiposTerreno);
        }
    }
    
    public void attach(ObserverTelaConfigurarJogo observer) {
        observers.add(observer);
    }
    
    public HashMap<String, Tabuleiro> getTabuleiros() {
        return tabuleiros;
    }
    
    public LinkedHashMap<EnumJogador, Jogador> getJogadores() {
        return jogadores;
    }
    
    public HashMap<String, String> getInformacoesMaquina(EnumMaquinas nomeMaquina) throws Exception {
        HashMap<String, String> resposta = new HashMap<>();
        String nome = nomeMaquina.getNome();
        factoryMaquina = (AbstractFactoryMaquina) Class.forName("model.abstractFactoryMaquina.ConcretFactory" + nomeMaquina.getNome().replace(" ", "")).getDeclaredConstructor().newInstance();
        Maquina maquina = factoryMaquina.construirMaquinaJogador1(0, 0, null);
        resposta.put("CaminhoImagem", maquina.caminhoImagemDirecaoFixa());
        resposta.put("Nome", nome);
        resposta.put("PV", String.valueOf(maquina.getPontosVitoria()));
        resposta.put("Vida", String.valueOf(maquina.getVida()));
        resposta.put("Ataque", String.valueOf(maquina.getAtaque()));
        resposta.put("Alcance", String.valueOf(maquina.getAlcance()));
        resposta.put("Movimento", String.valueOf(maquina.getMovimento()));
        return resposta;
    }
    
    public void adicionarMaquinaAoJogador(EnumJogador nomeJogador, int linha, int coluna, EnumMaquinas nomeMaquina, String nomeTabuleiro) throws Exception {
        Jogador jogador = jogadores.get(nomeJogador);
        factoryMaquina = (AbstractFactoryMaquina) Class.forName("model.abstractFactoryMaquina.ConcretFactory" + nomeMaquina.getNome().replace(" ", "")).getDeclaredConstructor().newInstance();
        Maquina maquina;
        if(nomeJogador.equals(EnumJogador.JOGADOR1)) {
            maquina = factoryMaquina.construirMaquinaJogador1(linha, coluna, jogadores.get(EnumJogador.JOGADOR1));
        } else {
            maquina = factoryMaquina.construirMaquinaJogador2(linha, coluna, jogadores.get(EnumJogador.JOGADOR2));
        }
        if(!jogador.podeAdicionarMaquinaNaPosicao(linha, coluna)) {
            if(jogador.podeSubstituirMaquinas(jogador.getMaquinaPorPosicao(linha, coluna), maquina)) {
                jogador.removeMaquina(linha, coluna);
            } else {
                throw new SubstituicaoInvalidaException();
            }
        }
        if(jogador.podeAdicionarMaquinas(maquina)) {
            if((tabuleiros.get(nomeTabuleiro).getTerrenoPorPosicao(linha + "" + coluna).getTipo().equals(EnumTipoTerreno.ABISMO) && maquina.getTipo().equals(EnumTipoMaquinas.MERGULHO)) || (!tabuleiros.get(nomeTabuleiro).getTerrenoPorPosicao(linha + "" + coluna).getTipo().equals(EnumTipoTerreno.ABISMO))) {
                jogador.addMaquinas(maquina);
                for(ObserverTelaConfigurarJogo observer : observers) {
                    observer.desenharMaquina(maquina.caminhoImagemDirecaoAtual(), linha + "" + coluna);
                }
            } else {
                throw new MaquinaEmTerrenoInvalidoException();
            }
        } else {
            throw new LimiteDeMaquinasException();
        }
    }
    
    public void removerMaquinaDoJogador(EnumJogador nomeJogador, int linha, int coluna) {
        Jogador jogador = jogadores.get(nomeJogador);
        jogador.removeMaquina(linha, coluna);
        for(ObserverTelaConfigurarJogo observer : observers) {
            observer.desenharMaquina("src/images/Vazio.png", linha + "" + coluna);
        }
    }
    
    public void removerMaquinasDosJogadores() {
        jogadores.get(EnumJogador.JOGADOR1).getMaquinas().clear();
        jogadores.get(EnumJogador.JOGADOR2).getMaquinas().clear();
    }
    
    public void alterouComboboxJogadores(EnumJogador nomeNovoJogador) {
        HashMap<String, String> chavesLinhasSuperiores = new HashMap<>();
        HashMap<String, String> chavesLinhasInferiores = new HashMap<>();
        Jogador jogador1 = jogadores.get(EnumJogador.JOGADOR1);
        Jogador jogador2 = jogadores.get(EnumJogador.JOGADOR2);
        for(int linha = 0; linha < 2; linha++) {
            for(int coluna = 0; coluna < 8; coluna++) {
                if(jogador2.getMaquinaPorPosicao(linha, coluna) == null) {
                    String nome = (nomeNovoJogador.equals(EnumJogador.JOGADOR1) ? "Bloqueado" : "Vazio");
                    chavesLinhasSuperiores.put(linha + "" + coluna, nome);
                }
                int linhaInferior = linha + 6;
                if(jogador1.getMaquinaPorPosicao(linhaInferior, coluna) == null) {
                    String nome = (nomeNovoJogador.equals(EnumJogador.JOGADOR1) ? "Vazio" : "Bloqueado");
                    chavesLinhasInferiores.put(linhaInferior + "" + coluna, nome);
                }
            }
        }
        for(ObserverTelaConfigurarJogo observer : observers) {
            observer.desenharBloqueadosOuVazios(chavesLinhasSuperiores);
            observer.desenharBloqueadosOuVazios(chavesLinhasInferiores);
        }
    }
    
    public void navegarParaTelaJogo(String nomeTabuleiro) throws Exception {
        if(jogadores.get(EnumJogador.JOGADOR1).contagemPVMaquinas() >= 1 && jogadores.get(EnumJogador.JOGADOR2).contagemPVMaquinas() >= 1) {
            construirTabuleiro = new ConstruirTabuleiroComMaquinas();
            directorTabuleiro = new DirectorTabuleiro(construirTabuleiro);
            List<Maquina> maquinas = new ArrayList<>();
            for(Maquina maquina : jogadores.get(EnumJogador.JOGADOR1).getMaquinas()) {
                maquinas.add(maquina);
            }
            for(Maquina maquina : jogadores.get(EnumJogador.JOGADOR2).getMaquinas()) {
                maquinas.add(maquina);
            }
            directorTabuleiro.construir(tabuleiros.get(nomeTabuleiro).getTipoTabuleiro(), tabuleiros.get(nomeTabuleiro).getTerrenos(), maquinas);
            SingletonConfiguracaoJogo.getInstancia().setTabuleiro(construirTabuleiro.getTabuleiro());
            SingletonConfiguracaoJogo.getInstancia().setJogadores(jogadores);
            navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaJogo");
        } else {
            throw new MinimoMaquinasException();
        }
    }
    
    public void navegarParaOutraTela(String caminho) throws Exception {
        AbstractFactoryTela factoryTela = (AbstractFactoryTela) Class.forName(caminho).getDeclaredConstructor().newInstance();
        for(ObserverTelaConfigurarJogo observer : observers) {
            factoryTela.construirTela();
            observer.navegarParaOutraTela();
        }
    }
}
