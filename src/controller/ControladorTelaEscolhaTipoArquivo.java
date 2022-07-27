package controller;

//CONTROLLER
import controller.adapter.ObjetoArquivoPROPERTIESAdapter;
import controller.adapter.ObjetoArquivoTXTAdapter;
import controller.objetosArquivos.ObjetoArquivoPROPERTIES;
import controller.objetosArquivos.ObjetoArquivoTXT;
import controller.observer.ObserverTelaEscolhaTipoArquivo;
import controller.observer.ObserverTelaInicial;

//JAVA
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

//VIEW ABSTRACT FACTORY
import controller.singleton.SingletonTipoDeArquivo;
import view.abstractFactoryTela.AbstractFactoryTela;

public class ControladorTelaEscolhaTipoArquivo {

    private List<ObserverTelaEscolhaTipoArquivo> observers;

    public ControladorTelaEscolhaTipoArquivo() {
        observers = new ArrayList<>();
    }

    public void attach(ObserverTelaEscolhaTipoArquivo observer) {
        observers.add(observer);
    }

    public void setTipoTXT() {
        SingletonTipoDeArquivo.getInstancia().setTXTStrategy();
    }

    public void setTipoPROPERTIES() {
        SingletonTipoDeArquivo.getInstancia().setPROPERTIESStrategy();
    }

    public void navegarParaOutraTela(String caminho) throws Exception {
        AbstractFactoryTela factoryTela = (AbstractFactoryTela) Class.forName(caminho).getDeclaredConstructor().newInstance();
        for (ObserverTelaEscolhaTipoArquivo observer : observers) {
            factoryTela.construirTela();
            observer.navegarParaOutraTela();
        }
    }
}
