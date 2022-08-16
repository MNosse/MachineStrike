package controller;

import controller.observer.ObserverTelaInicial;
import view.abstractFactoryTela.AbstractFactoryTela;

import java.util.ArrayList;
import java.util.List;

public class ControladorTelaInicial {
    
    private List<ObserverTelaInicial> observers;
    
    public ControladorTelaInicial() {
        observers = new ArrayList<>();
    }
    
    public void attach(ObserverTelaInicial observer) {
        observers.add(observer);
    }
    
    public void navegarParaOutraTela(String caminho) throws Exception {
        AbstractFactoryTela factoryTela = (AbstractFactoryTela) Class.forName(caminho).getDeclaredConstructor().newInstance();
        for(ObserverTelaInicial observer : observers) {
            factoryTela.construirTela();
            observer.navegarParaOutraTela();
        }
    }
}
