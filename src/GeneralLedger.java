/**
 * The GeneralLedger class creates a GeneralLedger object that
 * stores an ordered list of Transaction objects
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 1 R09
 *Data members: final static int MAX_TRANSACTIONS
 *				double totalDebitAmount
 *              double totalCreditAmount
 *              double credit
 *              double debit
 *              Transaction[] Ledger
 *              int totalTransaction
 *      
 */
public class GeneralLedger {
	final static int MAX_TRANSACTIONS = 50;
	private double totalDebitAmount;
	private double totalCreditAmount;
	private double credit, debit;
	private Transaction[] Ledger;
	private int totalTransaction;

/**
 * Returns an instance of GeneralLedger.
 */
	public GeneralLedger() {
		Ledger = new Transaction[MAX_TRANSACTIONS];
	}

/**
 * Adds newTransaction into this GeneralLedger object if not already existed.
 * In date order.
 * @param newTransaction
 * 		An Transaction object to be added to the ledger.
 * 
 * @preconditions
 * 		newTransaction instantiated and GeneralLedger has less than 
 * 		MAX_TRANSACTIONS objects.
 *
 * @postconditions
 * 		newTransaction added in the correct order in the list, and the
 * 		Transaction objects with newer dates are moved back one position.
 *
 * @throws TransactionAlreadyExistsException
 * 		Thrown if newTransaction is equal to one of the transaction objects
 * 		in the list.
 * @throws FullGeneralLedgerException
 * 		Thrown if GeneralLedger is full and no room to record newTransaction.
 * @throws InvalidTransactionException
 * 		Thrown if newTransaction amount is 0 or if the date is invalid.
 */
	public void addTransaction(Transaction newTransaction)
	  throws TransactionAlreadyExistsException, FullGeneralLedgerException
	  , InvalidTransactionException {
		int pos = this.size();
		if (newTransaction.getAmount() == 0 || 
		  !checkDate(newTransaction.getDate())) {
			throw new InvalidTransactionException();
		}
		if (totalTransaction >= MAX_TRANSACTIONS) {
			throw new FullGeneralLedgerException();
		}
		if (!exists(newTransaction)) {
			if (newTransaction.getAmount() > 0) {
				totalDebitAmount += newTransaction.getAmount();
			} else {
				totalCreditAmount += newTransaction.getAmount();
			}
			for (int i = 0; i < this.size(); i++) {
				if (newTransaction.getDate().substring(0, 4).compareTo(Ledger[i]
				  .getDate().substring(0, 4)) < 0) {
					pos = i;
					break;
				} else if (newTransaction.getDate().substring(0, 4)
						.compareTo(Ledger[i].getDate().substring(0, 4)) == 0) {
					if (newTransaction.getDate().substring(5, 7).compareTo
					  (Ledger[i].getDate().substring(5, 7)) < 0) {
						pos = i;
						break;
					} else if (newTransaction.getDate().substring(5, 7)
					  .compareTo(Ledger[i].getDate().substring(5, 7)) > 0){
						pos = i + 1;
					} else {
						if (newTransaction.getDate().substring(8, 10)
						  .compareTo(Ledger[i].getDate().substring(8, 10))
						  < 0) {
							pos = i;
							break;
						} else {
							pos = i + 1;
						}
					}
				}
			}
			insert(pos, newTransaction);
			totalTransaction++;
		} else {
			throw new TransactionAlreadyExistsException();
		}
	}

/**
 * Creates a deep copy of this GeneralLedger.
 * @precondition
 * 		This GeneralLedger object is instantiated.
 * @return
 * 		A copy of this GeneralLedger object.
 */
	public Object clone() {
		GeneralLedger clone = new GeneralLedger();
		try {
			for (int i = 0; i < this.size(); i++) {
				clone.addTransaction(this.getTransaction(i + 1));
			}
		} catch (InvalidTransactionException iex) {
			System.out.println(iex.getMessage());
		} catch (TransactionAlreadyExistsException tex) {
			System.out.println(tex.getMessage());
		} catch (InvalidLedgerPositionException ex2) {
			System.out.println(ex2.getMessage());
		}
		return clone;
	}

/**
 * Checks if the date entered is in valid format and between 1900 to 2050,
 * month 1-12, day 1-30.
 * @param date
 * 		The date string to be tested for validity.
 * 
 * @return
 * 		A boolean value to determine the validity of the date.
 */
	public boolean checkDate(String date) {
		boolean isDate = false;
		if (date.length() == 10
		  && (Integer.parseInt(date.substring(0, 4)) >= 1900 && Integer.parseInt
		  (date.substring(0, 4)) <= 2050)&& (Integer.parseInt(date.substring
		  (5, 7)) > 0 && Integer.parseInt(date.substring(5, 7)) <= 12)&& 
		  (Integer.parseInt(date.substring(8, 10)) > 0 && Integer.parseInt
		  (date.substring(8, 10)) <= 30)&& date.substring(4, 5).equals("/") 
		  && date.substring(7, 8).equals("/")) {
			isDate = true;
		}
		return isDate;
	}

/**
 * Removes the transaction object at position in this GeneralLedger.
 * @param position
 * 		The position of the Transaction to be removed.
 * 
 * @precondition
 * 		This generalLedger is instantiated and position is greater or equal to
 * 		1 but less than or equal to the size of the current list.
 * @postcondition
 * 		The Transaction at the specified position is removed. And transactions
 * 		after it are shifted 1 position to the left.
 * 
 * @throws InvalidLedgerPositionException
 * 		Thrown if position is not valid.
 */
	public void removeTransaction(int position) throws 
	  InvalidLedgerPositionException {
		if (position > this.size() || position < 1) {
			throw new InvalidLedgerPositionException();
		}
		if (Ledger[position - 1].getAmount() > 0) {
			totalDebitAmount -= Ledger[position - 1].getAmount();
		} else {
			totalCreditAmount -= Ledger[position - 1].getAmount();
		}
		for (int i = position - 1; i < this.size(); i++) {
			Ledger[i] = Ledger[i + 1];
		}

		totalTransaction--;
	}

/**
 * Returns the Transaction object located at position.
 * @param position
 * 		The position in this GeneralLedger to get.
 * 
 * @precondition
 * 		The GeneralLedger has been instantiated position is greater or equal to
 * 		1 but less than or equal to the size of the current list.
 *
 * @return
 * 		The Transaction object at the specified position.
 * 
 * @throws InvalidLedgerPositionException
 */
	public Transaction getTransaction(int position) throws 
	  InvalidLedgerPositionException {
		if (position > this.size() || position < 1) {
			throw new InvalidLedgerPositionException();
		}
		return Ledger[position - 1];
	}

/**
 * Prints all transactions with the specified date.
 * @param generalLedger
 * 		The ledger object to search in.
 * @param date
 * 		The date of the transaction(s) to search for.
 * 
 * @precondition
 * 		This GeneralLedger object has been instantiated.
 * @postcondition
 * 		Prints a table of all Transactions with the specified date.
 */
	public static void filter(GeneralLedger generalLedger, String date) {
		boolean dateFound = false;
		boolean header = false;
		if (generalLedger.size() < 1) {
			System.out.println("\nDate not found in the general ledger: 0 "
			  + "transaction in the general ledger. \n");
		} else {
			for (int i = 0; i < generalLedger.size(); i++) {
				if (generalLedger.Ledger[i].getDate().equals(date)) {
					dateFound = true;
					if (!header) {
						System.out.println(
						  String.format("%-10s%-15s%-15s%-15s%-80s", "No", 
						  "Date", "Debit", "Credit", "Description"));
						System.out.println(
						  "--------------------------------------"
						  + "----------------------------------");
						header = true;
					}
					if (generalLedger.Ledger[i].getAmount() > 0) {
						generalLedger.debit = generalLedger.Ledger[i].getAmount
						  ();
						System.out.print(String.format("%-10s%-15s%-15s%-15s%"
						  + "-80s", i + 1, generalLedger.Ledger[i].getDate(),
						  generalLedger.debit, "", generalLedger.Ledger[i].
						  getDescription()));
						System.out.println();
					} else {
						generalLedger.credit = generalLedger.Ledger[i].getAmount
						  ();
						System.out.print(String.format("%-10s%-15s%-15s%-15s%"
						  + "-80s", i + 1, generalLedger.Ledger[i].getDate(),
						  "", (generalLedger.credit * -1), generalLedger.Ledger
						  [i].getDescription()));
						System.out.println();
					}
				}
			}
			if (!dateFound) {
				System.out.println("\nDate not found in the general ledger.\n");
			}
		}
	}

/**
 * Returns the value of the totalDebitAmount variable.
 * @return
 * 		the value of totalDebitAmount variable.
 */
	public double getTotalDebitAmount() {
		return totalDebitAmount;
	}

/**
 * Returns the value of the totalCreidtAmount variable.
 * @return
 * 		the value of totalCreditAmount variable.
 */
	public double getTotalCreditAmount() {
		return totalCreditAmount;
	}

/**
 * Prints a table of each item in the list with its position number.
 * @precondition
 * 		This GeneralLedger has been instantiated.
 * @postcondition
 * 		All transactions in GeneralLedger object are printed in table format.
 */
	public void printAllTransactions() {
		System.out.print(this.toString());
	}

/**
 * Returns a String representation of this GeneralLedger object in a table
 * format with each Transaction on its own line.
 * @returns
 * 		A String representation of this GeneralLedger object.
 */
	public String toString() {
		String table = "";
		System.out.println();
		printHeader();
		for (int i = 0; i < this.size(); i++) {
			if (Ledger[i].getAmount() > 0) {
				debit = Ledger[i].getAmount();
				table+= String.format("%-10s%-15s%-15s%-15s%-80s", i + 1, Ledger
				  [i].getDate(), debit, "",
				  Ledger[i].getDescription());
				table+= "\n";
			} else {
				credit = Ledger[i].getAmount();
				table+= String.format("%-10s%-15s%-15s%-15s%-80s", i + 1, Ledger
				  [i].getDate(), "", credit * -1,Ledger[i].getDescription());
				table+= "\n";
			}
		}
		table+= "\n";
		return table;
	}

/**
 * Prints the header of the table.
 */
	public void printHeader() {
		System.out
		  .println("\n" + String.format("%-10s%-15s%-15s%-15s%-80s", "No", 
		  "Date", "Debit", "Credit", "Description"));
		System.out.println("-------------------------------------------"
		  + "---------------------------------");
	}

/**
 * Prints out the Transaction object located at position pos in the list.
 * @param pos
 * 		The position of the GeneralLedger object list to be printed.
 * 
 * @precondition
 * 		This GeneralLedger object has been instantiated and pos is valid.
 * @postcondition
 * 		Prints out the Transaction at position pos in the list.
 */
	public void printSpecific(int pos) {
		try {
			if (this.getTransaction(pos).getAmount() > 0) {
				System.out.print(String.format("%-10s%-15s%-15s%-15s%-80s", pos, 
				  this.getTransaction(pos).getDate(),this.getTransaction(pos).
				  getAmount(), "", this.getTransaction(pos).getDescription() 
				  + "\n"));
			} else {
				System.out.print(String.format("%-10s%-15s%-15s%-15s%-80s", pos, 
				  this.getTransaction(pos).getDate(), "",this.getTransaction
				  (pos).getAmount() * -1, this.getTransaction(pos).
				  getDescription() + "\n"));
			}
			System.out.println();
		} catch (InvalidLedgerPositionException ex) {
			System.out.print(ex.getMessage());
		}
	}

/**
 * Inserts the Transaction object newTransaction into position pos in the list.
 * @param pos
 * 		The position in the list to add the Transaction object newTransaction.
 * @param newTransaction
 * 		The Transaction object to be inserted into the list at position pos.
 * 
 * @precondition
 * 		The GeneralLedger has been instantiated and pos is greater or equal to
 * 		1 but less than or equal to the size of the current list.
 * @postcondition
 * 		newTransaction added at position pos and all transactions after it are
 * 		shifted one position to the right.
 */
	public void insert(int pos, Transaction newTransaction) {
		for (int i = this.size(); i > pos; i--) {
			Ledger[i] = Ledger[i - 1];
		}
		Ledger[pos] = newTransaction;

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
 * 		true if all the transactions in this GeneralLedger are equal to all 
 * 		the transactions in obj, false otherwise.
 */
	public boolean equals(Object obj) {
		boolean x = true;
		try {
			if (obj instanceof GeneralLedger) {
				if (this.size() == ((GeneralLedger) obj).size()) {
					for (int i = 0; i < this.size(); i++) {
						if (!(this.getTransaction(i + 1).equals(((GeneralLedger)
						  obj).getTransaction(i + 1)))) {
							x = false;
							break;
						}
					}
				} else {
					x = false;
				}
			} else {
				x = false;
			}
		} catch (InvalidLedgerPositionException ex) {
			System.out.println(ex.getMessage());
		}
		return x;
	}

/**
 * Returns the total number of Transaction objects in the list.
 * @return
 * 		The total number of Transaction objects in the list.
 */
	public int size() {
		return this.totalTransaction;
	}

/**
 * Checks if a certain Transaction object is in the ledger list.
 * @param transaction
 * 		The Transaction object to be checked.
 * 
 * @precondition
 * 		This GeneralLedger and transaction have been instantiated.
 * 
 * @return
 * 		true if this GeneralLedger contains transaction, false otherwise.
 * 
 * @throws IllegalArgumentException
 * 		Thrown if transaction is not a valid Transaction object
 */
	public boolean exists(Transaction transaction) throws 
	  IllegalArgumentException {
		boolean exists = false;
		if (!(transaction instanceof Transaction)) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < this.size(); i++) {
				if (Ledger[i].equals(transaction)) {
					exists = true;
					break;
				}
			}
			return exists;
		}

	}
}
