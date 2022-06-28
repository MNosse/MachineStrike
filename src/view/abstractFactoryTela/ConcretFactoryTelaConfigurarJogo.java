package view.abstractFactoryTela;

//VIEW
import view.Tela.Tela;
import view.Tela.TelaConfigurarJogo;

public class ConcretFactoryTelaConfigurarJogo extends AbstractFactoryTela{
    @Override
    public Tela construirTela() {
        return new TelaConfigurarJogo();
    }
}
