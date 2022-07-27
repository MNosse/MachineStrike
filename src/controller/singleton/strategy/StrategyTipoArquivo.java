package controller.singleton.strategy;

import java.io.IOException;
import java.util.ArrayList;

public interface StrategyTipoArquivo {
    String caminhoArquivos();
    
    String extensaoArquivos();
    
    Class classeObjeto();
    
    Class classeAdapter();
    
    void criarArquivo(String caminho, String nome, ArrayList<ArrayList<String>> terrenos) throws IOException;
    
    void editarArquivo(String caminho, String nome, ArrayList<ArrayList<String>> terrenos) throws IOException;
}
