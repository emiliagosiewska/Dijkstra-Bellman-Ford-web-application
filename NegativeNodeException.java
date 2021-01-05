
package lab5Algorithms.model;

/**
 * Exception class for negative cycle
 * @author Emilia Gosiewska
 * version 5.0
 */
  public class NegativeNodeException extends Exception {

    /**
     * Non-parameter constructor
     */
    public NegativeNodeException() {
    }

    /**
     * Exception class constructor
     *
     * @param message display message
     */
    public NegativeNodeException(String message) {
        super(message);
    }
  }