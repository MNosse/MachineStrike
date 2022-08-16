package view.decorator;

import javax.swing.*;

public abstract class ImagemDecorator extends ImagemBase {
    
    protected ImagemBase imagem;
    
    public ImagemDecorator(ImagemBase imagem) {
        this.imagem = imagem;
    }
    
    @Override
    public ImageIcon getImagem() {
        return imagem.getImagem();
    }
    
    @Override
    protected int getAltura() {
        return imagem.getAltura();
    }
    
    @Override
    protected int getLargura() {
        return imagem.getLargura();
    }
    
    @Override
    protected String getCaminho() {
        return imagem.getCaminho();
    }
}
