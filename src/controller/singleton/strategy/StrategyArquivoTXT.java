package controller.singleton.strategy;

import controller.adapter.ObjetoArquivoTXTAdapter;
import controller.objetosArquivos.ObjetoArquivoTXT;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StrategyArquivoTXT implements StrategyTipoArquivo {
    @Override
    public String caminhoArquivos() {
        return "src/arquivosTabuleirosTXT";
    }
    
    @Override
    public String extensaoArquivos() {
        return ".txt";
    }
    
    @Override
    public Class classeObjeto() {
        return ObjetoArquivoTXT.class;
    }
    
    @Override
    public Class classeAdapter() {
        return ObjetoArquivoTXTAdapter.class;
    }
    
    @Override
    public void criarArquivo(String caminho, String nome, ArrayList<ArrayList<String>> terrenos) throws IOException {
        File file = new File(caminho);
        if(!file.exists()) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            file.createNewFile();
            fileOutputStream.write((nome + "\n").getBytes());
            fileOutputStream.write("criado\n".getBytes());
            for(int l = 0; l < 8; l++) {
                String linha = "";
                for(int c = 0; c < 8; c++) {
                    linha += terrenos.get(l).get(c);
                    if(c < 7) {
                        linha += " ";
                    } else if(l < 7) {
                        linha += "\n";
                    }
                }
                fileOutputStream.write(linha.getBytes());
            }
            fileOutputStream.flush();
        }
    }
    
    @Override
    public void editarArquivo(String caminho, String nome, ArrayList<ArrayList<String>> terrenos) throws IOException {
        File file = new File(caminho);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        file.createNewFile();
        fileOutputStream.write((nome + "\n").getBytes());
        fileOutputStream.write("criado\n".getBytes());
        for(int l = 0; l < 8; l++) {
            String linha = "";
            for(int c = 0; c < 8; c++) {
                linha += terrenos.get(l).get(c);
                if(c < 7) {
                    linha += " ";
                } else if(l < 7) {
                    linha += "\n";
                }
            }
            fileOutputStream.write(linha.getBytes());
        }
        fileOutputStream.flush();
    }
}
