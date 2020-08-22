/**
 * The Transaction class creates a Transaction object with specified date,
 * amount, and description.
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *Data members: String date
 *				double amount
 *				String description
 */
public class Transaction {
	private String date;
	private double amount;
	private String description;

/**
 * Returns an instance of Transaction.
 */
	public Transaction() {
		date = "1900/01/01";
		amount = 0;
		description = "start";
	}

/**
 * Return an instance of Transaction.
 * @param date
 * 		The date of the instance to be created.
 * @param amount
 * 		The money value of the instance to be created.
 * @param description
 * 		The description of the instance to be created.
 */
	public Transaction(String date, double amount, String description) {
		this.date = date;
		this.amount = amount;
		this.description = description;
	}

/**
 * Returns the value of date.
 * @return
 * 		The date of the Transaction object.
 */
	public String getDate() {
		return date;
	}

/**
 * Returns the value of amount.
 * @return
 * 		The amount of the Transaction object.
 */
	public double getAmount() {
		return amount;
	}

/**
 * Returns the value of description.
 * @return
 * 		The description of the Transaction object.
 */
	public String getDescription() {
		return description;
	}

/**
 * Creates a deep copy of this Transaction.
 * @precondition
 * 		This Transaction object is instantiated.
 * @return
 * 		A copy of this Transaction object.
 */
	public Transaction clone() {
		Transaction tCopy = new Transaction(this.getDate(), this.getAmount(), 
		  this.getDescription());
		return tCopy;
	}

/**
 * Checks if all the transactions in this GeneralLedger are equal to
 * all the transactions in obj.
 * @param obj
 * 		The obj to be checked for.
 * 
 * @precondition
 * 		This GeneralLedger and obj are instantiated.
 * 
 * @return
 *  	true if all the transactions in this GeneralLedger are equal to all 
 *		the transactions in obj, false otherwise.
 */
	public boolean equals(Object obj) {
		if (obj instanceof Transaction) {
			if ((this.date).equals(((Transaction) obj).getDate()) && this.amount
			  == ((Transaction) obj).getAmount()&& (this.description).equals
			  (((Transaction) obj).getDescription())) {
				return true;
			}
		}
		return false;
	}
}
