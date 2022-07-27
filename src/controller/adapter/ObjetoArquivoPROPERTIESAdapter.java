package controller.adapter;

import controller.objetosArquivos.ObjetoArquivoPROPERTIES;

import java.util.ArrayList;
import java.util.Arrays;

public class ObjetoArquivoPROPERTIESAdapter extends ObjetoArquivoAdapter {
    
    private ObjetoArquivoPROPERTIES adaptada;
    
    public ObjetoArquivoPROPERTIESAdapter(ObjetoArquivoPROPERTIES objetoArquivoPROPERTIES) {
        adaptada = objetoArquivoPROPERTIES;
    }
    
    @Override
    public ArrayList<ArrayList<String>> getConteudo() {
        ArrayList<ArrayList<String>> conteudo = new ArrayList<>();
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getNome())));
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getTipo())));
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getLinha1().split(" "))));
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getLinha2().split(" "))));
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getLinha3().split(" "))));
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getLinha4().split(" "))));
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getLinha5().split(" "))));
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getLinha6().split(" "))));
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getLinha7().split(" "))));
        conteudo.add(new ArrayList<>(Arrays.asList(adaptada.getLinha8().split(" "))));
        return conteudo;
    }
}
