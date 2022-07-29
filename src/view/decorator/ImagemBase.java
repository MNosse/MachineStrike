package view.decorator;

import javax.swing.*;

public abstract class ImagemBase {
    public abstract ImageIcon getImagem();
    
    protected abstract int getAltura();
    
    protected abstract int getLargura();
    
    protected abstract String getCaminho();
}
