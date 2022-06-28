package controller.controlador;

//CONTROLLER
import controller.observer.ObserverTelaInicial;

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

    public void mostrar() {
        for (ObserverTelaInicial observer:observers) {
            observer.mostrarTela();
        }
    }

    public void ocultar() {
        for (ObserverTelaInicial observer:observers) {
            observer.ocultarTela();
        }
    }
}
