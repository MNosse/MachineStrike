package view.abstractFactoryTela;

import view.Tela;
import view.TelaConfigurarJogo;

public class ConcretFactoryTelaConfigurarJogo extends AbstractFactoryTela {
    @Override
    public Tela construirTela() {
        return new TelaConfigurarJogo();
    }
}
