package view.abstractFactoryTela;

//VIEW
import view.Tela.Tela;
import view.Tela.TelaTabuleiros;

public class ConcretFactoryTelaTabuleiros extends AbstractFactoryTela{
    @Override
    public Tela construirTela() {
        return new TelaTabuleiros();
    }
}
