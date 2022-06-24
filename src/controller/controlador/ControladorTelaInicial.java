package controller.controlador;

import controller.observer.ObserverTelaInicial;

import java.util.ArrayList;
import java.util.List;

public class ControladorTelaInicial {
    private List<ObserverTelaInicial> observers;

    public ControladorTelaInicial(){
        observers = new ArrayList<>();
    }

    public void attach(ObserverTelaInicial observer){
        observers.add(observer);
    }

    public void mostrar(){
        for (ObserverTelaInicial observer:observers){
            observer.mostrarTela();
        }
    }

    public void ocultar(){
        for (ObserverTelaInicial observer:observers){
            observer.ocultarTela();
        }
    }
}
