package controller.singleton;

import controller.singleton.strategy.StrategyArquivoPROPERTIES;
import controller.singleton.strategy.StrategyArquivoTXT;
import controller.singleton.strategy.StrategyTipoArquivo;

import java.io.IOException;
import java.util.ArrayList;

public class SingletonTipoDeArquivo {
    private static SingletonTipoDeArquivo instancia;
    private StrategyTipoArquivo strategyTipoArquivo;
    
    private SingletonTipoDeArquivo() {
    }
    
    public synchronized static SingletonTipoDeArquivo getInstancia() {
        if(instancia == null) {
            instancia = new SingletonTipoDeArquivo();
        }
        return instancia;
    }
    
    public void setTXTStrategy() {
        strategyTipoArquivo = new StrategyArquivoTXT();
    }
    
    public void setPROPERTIESStrategy() {
        strategyTipoArquivo = new StrategyArquivoPROPERTIES();
    }
    
    public String getCaminhoArquivos() {
        return strategyTipoArquivo.caminhoArquivos();
    }
    
    
    public String getExtensaoArquivo() {
        return strategyTipoArquivo.extensaoArquivos();
    }
    
    
    public Class getClasseObjeto() {
        return strategyTipoArquivo.classeObjeto();
    }
    
    
    public Class getClasseAdapter() {
        return strategyTipoArquivo.classeAdapter();
    }
    
    public void escreverArquivo(String caminho, String nome, ArrayList<ArrayList<String>> terrenos) throws IOException {
        strategyTipoArquivo.criarArquivo(caminho, nome, terrenos);
    }
    
    public void editarArquivo(String caminho, String nome, ArrayList<ArrayList<String>> terrenos) throws IOException {
        strategyTipoArquivo.editarArquivo(caminho, nome, terrenos);
    }
    
}
