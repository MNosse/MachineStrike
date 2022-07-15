package controller;

//CONTROLLER
import controller.observer.ObserverTelaTabuleiros;

//GLOBAL
import global.Enum.EnumTipoTerreno;
import global.Enum.EnumTipoTabuleiro;

//JAVA
import java.io.*;
import java.util.*;

//MODEL
import model.Terreno;
import model.Tabuleiro;
import model.builderTerreno.DirectorTerreno;
import model.builderTerreno.ConstruirTerreno;
import model.builderTabuleiro.DirectorTabuleiro;
import model.builderTabuleiro.ConstruirTabuleiroSemMaquinas;

//VIEW ABSTRACT FACTORY
import view.abstractFactoryTela.AbstractFactoryTela;

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
        BufferedReader bufferedReader;
        File todosArquivos = new File("src/arquivosTabuleiros");
        File[] arquivos = todosArquivos.listFiles();
        for (File arquivo:arquivos) {
            bufferedReader = new BufferedReader(new FileReader(arquivo));
            List<String> linhas = new ArrayList<>(bufferedReader.lines().toList());
            bufferedReader.close();
            String nome = linhas.get(0);
            EnumTipoTabuleiro tipoTabuleiro = EnumTipoTabuleiro.PADRAO;
            if (!linhas.get(1).equals("padrao")) {
                tipoTabuleiro = EnumTipoTabuleiro.CRIADO;
            }
            ArrayList<ArrayList<String>> tiposTerrenos = new ArrayList<>();
            for (int linha = 2; linha < linhas.size(); linha++) {
                tiposTerrenos.add(new ArrayList<>(Arrays.asList(linhas.get(linha).split(" "))));
            }
            HashMap<String, Terreno> terrenos = new HashMap<>();
            for (int linha = 0; linha < 8; linha++) {
                for (int coluna = 0; coluna < 8; coluna++) {
                    ConstruirTerreno builder = (ConstruirTerreno) Class.forName("model.builderTerreno.Construir"+tiposTerrenos.get(linha).get(coluna)).getDeclaredConstructor().newInstance();
                    directorTerreno = new DirectorTerreno(builder);
                    directorTerreno.construir();
                    terrenos.put(linha+""+coluna, builder.getTerreno());
                }
            }
            directorTabuleiro.construir(tipoTabuleiro, terrenos, null);
            tabuleiros.put(nome, construirTabuleiro.getTabuleiro());
        }
    }

    public EnumTipoTabuleiro getTipoTabuleiro(String nomeTabuleiro) {
        return tabuleiros.get(nomeTabuleiro).getTipoTabuleiro();
    }

    public void criarArquivoDeTabuleiro(String nome, HashMap<String, EnumTipoTerreno> terrenos) throws Exception {
        String nomeDoArquivo = nome.replace(" ", "_");
        String caminho = "src/arquivosTabuleiros/Mapa"+nomeDoArquivo+".txt";
        File arquivoDeTabuleiro = new File(caminho);
        arquivoDeTabuleiro.createNewFile();
        FileWriter fileWriter = new FileWriter(caminho);
        fileWriter.write(nome+"\n");
        fileWriter.write("criado\n");
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                fileWriter.write(terrenos.get(linha+""+coluna).getTipo());
                if (coluna < 7) {
                    fileWriter.write(" ");
                }
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
        construirTabuleiros();
        for (ObserverTelaTabuleiros observer:observers) {
            observer.atualizarListaDeTabuleiros(new Vector<String>(tabuleiros.keySet()));
        }
    }

    public void atualizarArquivoDeTabuleiro(String nome, HashMap<String, EnumTipoTerreno> terrenos) throws Exception {
        String caminho = "src/arquivosTabuleiros/Mapa"+nome+".txt";
        File arquivoDeTabuleiro = new File(caminho);
        arquivoDeTabuleiro.createNewFile();
        FileWriter fileWriter = new FileWriter(caminho);
        fileWriter.write(nome+"\n");
        fileWriter.write(getTipoTabuleiro(nome).getTipo()+"\n");
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++){
                fileWriter.write(terrenos.get(linha+""+coluna).getTipo());
                if (coluna < 7) {
                    fileWriter.write(" ");
                }
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
        construirTabuleiros();
        for (ObserverTelaTabuleiros observer:observers) {
            observer.atualizarListaDeTabuleiros(new Vector<String>(tabuleiros.keySet()));
        }
    }

    public void deletarArquivoDeTabuleiro(String nome) throws Exception {
        String nomeDoArquivo = nome.replace(" ", "_");
        String caminho = "src/arquivosTabuleiros/Mapa"+nomeDoArquivo+".txt";
        File arquivoDeTabuleiro = new File(caminho);
        arquivoDeTabuleiro.delete();
        construirTabuleiros();
        for (ObserverTelaTabuleiros observer:observers) {
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
        for (ObserverTelaTabuleiros observer:observers) {
            observer.desenharTabuleiro(tiposTerreno);
        }
    }

    public void iniciarListaTerrenosCriacao(String chaveTabuleiro) {
        HashMap<String, Terreno> terrenos = tabuleiros.get(chaveTabuleiro).getTerrenos();
        Set<String> posicoes = terrenos.keySet();
        HashMap<String, EnumTipoTerreno> tiposTerrenos = new HashMap<>();
        for (String posicao : posicoes) {
            tiposTerrenos.put(posicao, terrenos.get(posicao).getTipo());
        }
        for (ObserverTelaTabuleiros observer:observers) {
            observer.iniciarListaTerrenosCriacao(tiposTerrenos);
        }
    }

    public void mudarEstadoEditarDeletar(String chaveTabuleiro) {
        boolean retorno = false;
        if (tabuleiros.get(chaveTabuleiro).getTipoTabuleiro() == EnumTipoTabuleiro.CRIADO) {
            retorno = true;
        }
        for (ObserverTelaTabuleiros observer:observers) {
            observer.mudarEstadoEditarDeletar(retorno);
        }
    }

    public void navegarParaOutraTela(String caminho) throws Exception {
        AbstractFactoryTela factoryTela = (AbstractFactoryTela) Class.forName(caminho).getDeclaredConstructor().newInstance();
        for (ObserverTelaTabuleiros observer : observers) {
            observer.navegarParaOutraTela(factoryTela.construirTela());
        }
    }

    public HashMap<String, Tabuleiro> getTabuleiros() {
        return tabuleiros;
    }
}
