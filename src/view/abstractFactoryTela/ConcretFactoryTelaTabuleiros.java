package view.abstractFactoryTela;

import view.Tela;
import view.TelaTabuleiros;

public class ConcretFactoryTelaTabuleiros extends AbstractFactoryTela {
    @Override
    public Tela construirTela() {
        return new TelaTabuleiros();
    }
}
