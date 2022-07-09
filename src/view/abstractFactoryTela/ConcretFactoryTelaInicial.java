package view.abstractFactoryTela;

//VIEW
import view.Tela;
import view.TelaInicial;

public class ConcretFactoryTelaInicial extends AbstractFactoryTela {
    @Override
    public Tela construirTela() {
        return new TelaInicial();
    }
}