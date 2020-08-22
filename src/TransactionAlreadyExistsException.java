/**
 * The TransactionAlreadyExistsException class creates an exception object that can be thrown
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 */
public class TransactionAlreadyExistsException extends Exception {
	public TransactionAlreadyExistsException() {
		super("Transaction Not Added: Transaction Already Existed\n");
	}
}

