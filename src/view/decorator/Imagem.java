package view.decorator;

import javax.swing.*;
import java.awt.*;

public class Imagem extends ImagemBase{
    
    private String caminhoImagem;
    private int altura;
    private int largura;
    
    public Imagem(String caminhoImage, int altura, int largura) {
        this.caminhoImagem = caminhoImage;
        this.altura = altura;
        this.largura = largura;
    }
    
    @Override
    public ImageIcon getImagem() {
        return new ImageIcon(new ImageIcon((caminhoImagem)).getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH));
    }
    
    @Override
    protected int getAltura() {
        return altura;
    }
    
    @Override
    protected int getLargura() {
        return largura;
    }
    
    @Override
    protected String getCaminho() {
        return caminhoImagem;
    }
}
