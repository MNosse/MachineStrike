package controller.controlador;

//CONTROLLER
import controller.abstractFactoryTela.ConcretFactoryTelaTabuleiros;
import controller.observer.ObserverTelaInicial;
import controller.abstractFactoryTela.AbstractFactoryTela;
import controller.abstractFactoryTela.ConcretFactoryTelaConfigurarJogo;

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

    public void navegarParaTelaConfigurarJogo() {
        AbstractFactoryTela factoryTela = new ConcretFactoryTelaConfigurarJogo();
        for (ObserverTelaInicial observer:observers) {
            observer.navegarParaOutraTela(factoryTela.construirTela());
        }
    }

    public void navegarParaTelaTabuleiros() {
        AbstractFactoryTela factoryTela = new ConcretFactoryTelaTabuleiros();
        for (ObserverTelaInicial observer:observers) {
            observer.navegarParaOutraTela(factoryTela.construirTela());
        }
    }
}
