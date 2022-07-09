package controller;

//CONTROLLER
import controller.observer.ObserverTelaInicial;
import view.abstractFactoryTela.AbstractFactoryTela;

//JAVA
import java.util.List;
import java.util.ArrayList;

public class ControladorTelaInicial {

    private List<ObserverTelaInicial> observers;

    public ControladorTelaInicial() {
        observers = new ArrayList<>();
    }

    public void attach(ObserverTelaInicial observer) {
        observers.add(observer);
    }

    public void navegarParaOutraTela(String caminho) {
        try {
            AbstractFactoryTela factoryTela = (AbstractFactoryTela) Class.forName(caminho).getDeclaredConstructor().newInstance();
            for (ObserverTelaInicial observer : observers) {
                observer.navegarParaOutraTela(factoryTela.construirTela());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
