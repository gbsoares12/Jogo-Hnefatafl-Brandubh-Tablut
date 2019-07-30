package control;

/**
 *
 * @author 42519630833
 */
public interface Command {
    
    boolean execute();
    void undo();
    
}
