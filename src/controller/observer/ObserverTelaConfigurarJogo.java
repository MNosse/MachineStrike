package controller.observer;

//GLOBAL
import global.EnumTipoTerreno;

//JAVA
import java.util.HashMap;

public interface ObserverTelaConfigurarJogo {
    void mostrarTela();
    void ocultarTela();
    void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos);
}
