package view.abstractFactoryTela;

import view.Tela;
import view.TelaJogo;

public class ConcretFactoryTelaJogo extends AbstractFactoryTela {
    @Override
    public Tela construirTela() {
        return new TelaJogo();
    }
}
