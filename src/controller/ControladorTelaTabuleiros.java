package controller;

import controller.adapter.ObjetoArquivoAdapter;
import controller.observer.ObserverTelaTabuleiros;
import controller.singleton.SingletonTipoDeArquivo;
import global.Enum.EnumTipoTabuleiro;
import global.Enum.EnumTipoTerreno;
import model.Tabuleiro;
import model.Terreno;
import model.builderTabuleiro.ConstruirTabuleiroSemMaquinas;
import model.builderTabuleiro.DirectorTabuleiro;
import model.builderTerreno.ConstruirTerreno;
import model.builderTerreno.DirectorTerreno;
import view.abstractFactoryTela.AbstractFactoryTela;

import java.io.File;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.*;

public class ControladorTelaTabuleiros {
    
    private DirectorTerreno directorTerreno;
    private DirectorTabuleiro directorTabuleiro;
    private List<ObserverTelaTabuleiros> observers;
    private LinkedHashMap<String, Tabuleiro> tabuleiros;
    private ConstruirTabuleiroSemMaquinas construirTabuleiro;
    
    public ControladorTelaTabuleiros() throws Exception {
        observers = new ArrayList<>();
        construirTabuleiro = new ConstruirTabuleiroSemMaquinas();
        directorTabuleiro = new DirectorTabuleiro(construirTabuleiro);
        construirTabuleiros();
    }
    
    private void construirTabuleiros() throws Exception {
        tabuleiros = new LinkedHashMap<>();
        File todosArquivos = new File(SingletonTipoDeArquivo.getInstancia().getCaminhoArquivos());
        File[] arquivos = todosArquivos.listFiles();
        for(File arquivo : arquivos) {
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
    
    public EnumTipoTabuleiro getTipoTabuleiro(String nomeTabuleiro) {
        if(tabuleiros.get(nomeTabuleiro) != null) {
            return tabuleiros.get(nomeTabuleiro).getTipoTabuleiro();
        } else {
            return null;
        }
    }
    
    public void criarArquivoDeTabuleiro(String nome, HashMap<String, EnumTipoTerreno> terrenos) throws Exception {
        EnumTipoTabuleiro tipo = getTipoTabuleiro(nome);
        if((tipo == null) || (!getTipoTabuleiro(nome).getTipo().equals("padrao"))) {
            String nomeDoArquivo = nome.replace(" ", "_");
            String caminho = SingletonTipoDeArquivo.getInstancia().getCaminhoArquivos() + "/Mapa" + nomeDoArquivo + SingletonTipoDeArquivo.getInstancia().getExtensaoArquivo();
            ArrayList<ArrayList<String>> textoTerrenos = new ArrayList<>();
            for(int linha = 0; linha < 8; linha++) {
                textoTerrenos.add(new ArrayList<>());
                for(int coluna = 0; coluna < 8; coluna++) {
                    textoTerrenos.get(linha).add(terrenos.get(linha + "" + coluna).getTipo());
                }
            }
            SingletonTipoDeArquivo.getInstancia().escreverArquivo(caminho, nome, textoTerrenos);
            construirTabuleiros();
            for(ObserverTelaTabuleiros observer : observers) {
                observer.atualizarListaDeTabuleiros(new Vector<String>(tabuleiros.keySet()));
            }
        }
    }
    
    public void atualizarArquivoDeTabuleiro(String nome, HashMap<String, EnumTipoTerreno> terrenos) throws Exception {
        String nomeDoArquivo = nome.replace(" ", "_");
        String caminho = SingletonTipoDeArquivo.getInstancia().getCaminhoArquivos() + "/Mapa" + nomeDoArquivo + SingletonTipoDeArquivo.getInstancia().getExtensaoArquivo();
        ArrayList<ArrayList<String>> textoTerrenos = new ArrayList<>();
        for(int linha = 0; linha < 8; linha++) {
            textoTerrenos.add(new ArrayList<>());
            for(int coluna = 0; coluna < 8; coluna++) {
                textoTerrenos.get(linha).add(terrenos.get(linha + "" + coluna).getTipo());
            }
        }
        SingletonTipoDeArquivo.getInstancia().editarArquivo(caminho, nome, textoTerrenos);
        construirTabuleiros();
        for(ObserverTelaTabuleiros observer : observers) {
            observer.atualizarListaDeTabuleiros(new Vector<String>(tabuleiros.keySet()));
        }
    }
    
    public void deletarArquivoDeTabuleiro(String nome) throws Exception {
        String nomeDoArquivo = nome.replace(" ", "_");
        String caminho = SingletonTipoDeArquivo.getInstancia().getCaminhoArquivos() + "/Mapa" + nomeDoArquivo + SingletonTipoDeArquivo.getInstancia().getExtensaoArquivo();
        File arquivoDeTabuleiro = new File(caminho);
        arquivoDeTabuleiro.delete();
        Files.delete(arquivoDeTabuleiro.toPath());
        construirTabuleiros();
        for(ObserverTelaTabuleiros observer : observers) {
            observer.atualizarListaDeTabuleiros(new Vector<String>(tabuleiros.keySet()));
        }
    }
    
    public void attach(ObserverTelaTabuleiros observer) {
        observers.add(observer);
    }
    
    public void desenharTabuleiro(String chaveTabuleiro) {
        HashMap<String, Terreno> terrenos = tabuleiros.get(chaveTabuleiro).getTerrenos();
        Set<String> posicoes = terrenos.keySet();
        HashMap<String, EnumTipoTerreno> tiposTerreno = new HashMap<>();
        for(String posicao : posicoes) {
            tiposTerreno.put(posicao, terrenos.get(posicao).getTipo());
        }
        for(ObserverTelaTabuleiros observer : observers) {
            observer.desenharTabuleiro(tiposTerreno);
        }
    }
    
    public void iniciarListaTerrenosCriacao(String chaveTabuleiro) {
        HashMap<String, Terreno> terrenos = tabuleiros.get(chaveTabuleiro).getTerrenos();
        Set<String> posicoes = terrenos.keySet();
        HashMap<String, EnumTipoTerreno> tiposTerrenos = new HashMap<>();
        for(String posicao : posicoes) {
            tiposTerrenos.put(posicao, terrenos.get(posicao).getTipo());
        }
        for(ObserverTelaTabuleiros observer : observers) {
            observer.iniciarListaTerrenosCriacao(tiposTerrenos);
        }
    }
    
    public void mudarEstadoEditarDeletar(String chaveTabuleiro) {
        boolean retorno = false;
        if(tabuleiros.get(chaveTabuleiro).getTipoTabuleiro() == EnumTipoTabuleiro.CRIADO) {
            retorno = true;
        }
        for(ObserverTelaTabuleiros observer : observers) {
            observer.mudarEstadoEditarDeletar(retorno);
        }
    }
    
    public void navegarParaOutraTela(String caminho) throws Exception {
        AbstractFactoryTela factoryTela = (AbstractFactoryTela) Class.forName(caminho).getDeclaredConstructor().newInstance();
        for(ObserverTelaTabuleiros observer : observers) {
            factoryTela.construirTela();
            observer.navegarParaOutraTela();
        }
    }
    
    public HashMap<String, Tabuleiro> getTabuleiros() {
        return tabuleiros;
    }
}
