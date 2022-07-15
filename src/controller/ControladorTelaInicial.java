package controller;

//CONTROLLER
import controller.observer.ObserverTelaInicial;

//JAVA
import java.util.List;
import java.util.ArrayList;

//VIEW ABSTRACT FACTORY
import view.abstractFactoryTela.AbstractFactoryTela;

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
        for (ObserverTelaInicial observer : observers) {
            observer.navegarParaOutraTela(factoryTela.construirTela());
        }
    }
}
