package view.abstractFactoryTela;

import view.Tela.Tela;
import view.Tela.TelaInicial;

public class ConcretFactoryTelaInicial extends AbstractFactoryTela {
    @Override
    public Tela construirTela() {
        return new TelaInicial();
    }
}
