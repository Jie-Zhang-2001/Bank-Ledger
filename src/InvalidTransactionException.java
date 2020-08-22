/**
 * The InvalidTransactionException class creates an exception object that can be thrown
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 */
public class InvalidTransactionException extends Exception{
	public InvalidTransactionException() {
		super("Transaction Not Added: Invalid Transaction.\n");
	}
}
