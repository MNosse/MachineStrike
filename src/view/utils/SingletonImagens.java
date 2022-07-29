package view.utils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SingletonImagens {
    private static SingletonImagens instancia;
    private static HashMap<String, ImageIcon> imagens;
    
    static {
        imagens = new HashMap<>();
        int largura = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.9);
        int altura = (int) (largura * 0.562);
        imagens.put("Background", new ImageIcon(new ImageIcon(("src/images/Background.png")).getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH)));
        imagens.put("Logo", new ImageIcon(new ImageIcon(("src/images/Logo.png")).getImage().getScaledInstance((int) (largura * 0.375), ((int) (altura * 0.36402)), Image.SCALE_SMOOTH)));
        imagens.put("Abismo", new ImageIcon(new ImageIcon(("src/images/Abismo.png")).getImage().getScaledInstance((int) (altura * 0.11), (int) (altura * 0.11), Image.SCALE_SMOOTH)));
        imagens.put("Pantano", new ImageIcon(new ImageIcon(("src/images/Pantano.png")).getImage().getScaledInstance((int) (altura * 0.11), (int) (altura * 0.11), Image.SCALE_SMOOTH)));
        imagens.put("Pasto", new ImageIcon(new ImageIcon(("src/images/Pasto.png")).getImage().getScaledInstance((int) (altura * 0.11), (int) (altura * 0.11), Image.SCALE_SMOOTH)));
        imagens.put("Floresta", new ImageIcon(new ImageIcon(("src/images/Floresta.png")).getImage().getScaledInstance((int) (altura * 0.11), (int) (altura * 0.11), Image.SCALE_SMOOTH)));
        imagens.put("Elevacao", new ImageIcon(new ImageIcon(("src/images/Elevacao.png")).getImage().getScaledInstance((int) (altura * 0.11), (int) (altura * 0.11), Image.SCALE_SMOOTH)));
        imagens.put("Montanha", new ImageIcon(new ImageIcon(("src/images/Montanha.png")).getImage().getScaledInstance((int) (altura * 0.11), (int) (altura * 0.11), Image.SCALE_SMOOTH)));
        imagens.put("Vazio", new ImageIcon(new ImageIcon(("src/images/Vazio.png")).getImage().getScaledInstance((int) (altura * 0.11), (int) (altura * 0.11), Image.SCALE_SMOOTH)));
        imagens.put("Bloqueado", new ImageIcon(new ImageIcon(("src/images/Bloqueado.png")).getImage().getScaledInstance((int) (altura * 0.11), (int) (altura * 0.11), Image.SCALE_SMOOTH)));
        imagens.put("Selecionado", new ImageIcon(new ImageIcon(("src/images/Selecionado.png")).getImage().getScaledInstance((int) (altura * 0.11), (int) (altura * 0.11), Image.SCALE_SMOOTH)));
    }
    
    private SingletonImagens() {
    }
    
    public synchronized static SingletonImagens getInstancia() {
        if(instancia == null) {
            instancia = new SingletonImagens();
        }
        return instancia;
    }
    
    public HashMap<String, ImageIcon> getImagens() {
        return imagens;
    }
}
