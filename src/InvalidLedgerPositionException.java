/**
 * The InvalidPositionException creates an exception object that can be thrown
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 */
public class InvalidLedgerPositionException extends Exception {
	public InvalidLedgerPositionException() {
		super("\nNo such transaction in the general ledger.\n");
	}

}
