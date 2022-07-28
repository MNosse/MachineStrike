package view.decorator;

import javax.swing.*;
import java.awt.*;

public class AumentarImagemDecorator extends ImagemDecorator{
    
    public AumentarImagemDecorator(ImagemBase imagem) {
        super(imagem);
    }
    
    @Override
    public ImageIcon getImagem() {
        ImageIcon imageIcon = imagem.getImagem();
        return new ImageIcon(imageIcon.getImage().getScaledInstance((imageIcon.getIconHeight()*5), (imageIcon.getIconHeight()*5), Image.SCALE_SMOOTH));
    }
}
