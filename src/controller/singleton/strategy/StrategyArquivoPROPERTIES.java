package controller.singleton.strategy;

import controller.adapter.ObjetoArquivoPROPERTIESAdapter;
import controller.objetosArquivos.ObjetoArquivoPROPERTIES;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class StrategyArquivoPROPERTIES implements StrategyTipoArquivo {
    @Override
    public String caminhoArquivos() {
        return "src/arquivosTabuleirosPROPERTIES";
    }
    
    @Override
    public String extensaoArquivos() {
        return ".properties";
    }
    
    @Override
    public Class classeObjeto() {
        return ObjetoArquivoPROPERTIES.class;
    }
    
    @Override
    public Class classeAdapter() {
        return ObjetoArquivoPROPERTIESAdapter.class;
    }
    
    @Override
    public void criarArquivo(String caminho, String nome, ArrayList<ArrayList<String>> terrenos) throws IOException {
        File file = new File(caminho);
        if(!file.exists()) {
            Properties properties = new Properties();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            properties.setProperty("tipo", "criado");
            properties.setProperty("nome", nome);
            for(int l = 0; l < 8; l++) {
                String linha = "";
                for(int c = 0; c < 8; c++) {
                    linha += terrenos.get(l).get(c);
                    if(c < 7) {
                        linha += " ";
                    }
                }
                properties.setProperty("linha" + (l + 1), linha);
            }
            properties.store(fileOutputStream, null);
            fileOutputStream.flush();
        }
    }
    
    @Override
    public void editarArquivo(String caminho, String nome, ArrayList<ArrayList<String>> terrenos) throws IOException {
        File file = new File(caminho);
        Properties properties = new Properties();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        properties.setProperty("tipo", "criado");
        properties.setProperty("nome", nome);
        for(int l = 0; l < 8; l++) {
            String linha = "";
            for(int c = 0; c < 8; c++) {
                linha += terrenos.get(l).get(c);
                if(c < 7) {
                    linha += " ";
                }
            }
            properties.setProperty("linha" + (l + 1), linha);
        }
        properties.store(fileOutputStream, null);
        fileOutputStream.flush();
    }
}
