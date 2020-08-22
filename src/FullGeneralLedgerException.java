/**
 * The FullGeneralLedgerException class creates an exception object that can be thrown
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 */
public class FullGeneralLedgerException extends IndexOutOfBoundsException{
	public FullGeneralLedgerException() {
		super("Transaction Not Added: Ledger is full");
	}
}
