package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

//JAVA
import java.util.List;

public abstract class Command {

    protected List<ObserverCommand> observers;

    public Command(List<ObserverCommand> observers) {
        for (ObserverCommand observer : observers) {
            attach(observer);
        }
    }

    public void attach(ObserverCommand observer) {
        observers.add(observer);
    }

    public abstract void execute();

    public abstract void undo();

    public abstract void redo();

}
