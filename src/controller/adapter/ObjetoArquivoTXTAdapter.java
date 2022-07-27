package controller.adapter;

import controller.objetosArquivos.ObjetoArquivoTXT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ObjetoArquivoTXTAdapter extends ObjetoArquivoAdapter {
    
    private ObjetoArquivoTXT adaptada;
    
    public ObjetoArquivoTXTAdapter(ObjetoArquivoTXT objetoArquivoTXT) {
        adaptada = objetoArquivoTXT;
    }
    
    @Override
    public ArrayList<ArrayList<String>> getConteudo() {
        ArrayList<ArrayList<String>> conteudo = new ArrayList<>();
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getNome())));
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getTipo())));
        HashMap<String, String> terrenos = adaptada.getTerrenos();
        for(int l = 0; l < 8; l++) {
            ArrayList<String> linha = new ArrayList<>();
            for(int c = 0; c < 8; c++) {
                linha.add(terrenos.get(l + "" + c));
            }
            conteudo.add(linha);
        }
        return conteudo;
    }
}
