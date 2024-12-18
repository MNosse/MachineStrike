package controller.objetosArquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ObjetoArquivoTXT {
    private String nome;
    private String tipo;
    private HashMap<String, String> terrenos;
    
    public ObjetoArquivoTXT(File file) throws IOException {
        this.nome = lerNome(file);
        this.tipo = lerTipo(file);
        this.terrenos = lerTerrenos(file);
    }
    
    private String lerNome(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String n = bufferedReader.readLine();
        bufferedReader.close();
        return n;
    }
    
    private String lerTipo(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        bufferedReader.readLine();
        String t = bufferedReader.readLine();
        bufferedReader.close();
        return t;
    }
    
    private HashMap<String, String> lerTerrenos(File file) throws IOException {
        HashMap<String, String> t = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        bufferedReader.readLine();
        bufferedReader.readLine();
        ArrayList<ArrayList<String>> matrizTerrenos = new ArrayList<>();
        for(int linha = 2; linha < 10; linha++) {
            matrizTerrenos.add(new ArrayList<>(Arrays.asList(bufferedReader.readLine().split(" "))));
        }
        for(int linha = 0; linha < 8; linha++) {
            for(int coluna = 0; coluna < 8; coluna++) {
                t.put(linha + "" + coluna, matrizTerrenos.get(linha).get(coluna));
            }
        }
        bufferedReader.close();
        return t;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public HashMap<String, String> getTerrenos() {
        return terrenos;
    }
}
