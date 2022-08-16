package controller;

import controller.observer.ObserverTelaEscolhaTipoArquivo;
import controller.singleton.SingletonTipoDeArquivo;
import view.abstractFactoryTela.AbstractFactoryTela;

import java.util.ArrayList;
import java.util.List;

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
        for(ObserverTelaEscolhaTipoArquivo observer : observers) {
            factoryTela.construirTela();
            observer.navegarParaOutraTela();
        }
    }
}
