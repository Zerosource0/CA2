
package exception;

/**
 *
 * @author plaul1
 */
public class EntityNotFoundException extends Exception {

  public EntityNotFoundException(String string) {
    super(string);
  }
  public EntityNotFoundException() {
    super("Person with requested id not found");
  }
  
}
