import java.util.Scanner;
/**
 * The GeneralLedgerManager class works as a test to test out the GeneralLedger
 * class and Transaction class and the methods within them.
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 1 R09
 */		
public class GeneralLedgerManager {
	public static void main(String[] args) {
		GeneralLedger list1 = new GeneralLedger();
		Scanner stdin = new Scanner(System.in);
		boolean x = true;
		GeneralLedger backupCopy = new GeneralLedger();
		String choice = "";
		String description = "", date = "";
		double amount;
		do {
			try {
				System.out.println("(A) Add Transaction");
				System.out.println("(G) Get Transaction");
				System.out.println("(R) Remove Transaction");
				System.out.println("(P) Print Transaction in General Ledger");
				System.out.println("(F) Filter by Date");
				System.out.println("(L) Look for Transaction");
				System.out.println("(S) Size");
				System.out.println("(B) Backup");
				System.out.println("(PB) Print Transaction in Backup");
				System.out.println("(RB) Revert to Backup");
				System.out.println("(CB) Compare Backup with Current");
				System.out.println("(PF) Print Financial Information");
				System.out.println("(Q) Quit \n");
				System.out.print("Enter a Selection: ");
				choice = stdin.nextLine().toUpperCase();
				switch (choice) {
				case "A":
					System.out.print("\nEnter Date: ");
					date = stdin.next();
					System.out.print("Enter Amount($): ");
					amount = stdin.nextDouble();
					stdin.nextLine();
					System.out.print("Enter Description: ");
					description = stdin.nextLine();
					System.out.println();
					Transaction t = new Transaction(date, amount, description);
					list1.addTransaction(t);
					System.out.println("Transaction successfully added to the "
					  + "general ledger. \n");
					break;
				case "G":
					System.out.print("\nEnter position: ");
					int pos = stdin.nextInt();
					list1.getTransaction(pos);
					list1.printHeader();
					list1.printSpecific(pos);
					stdin.nextLine();
					break;
				case "R":
					System.out.print("\nEnter position: ");
					pos = stdin.nextInt();
					list1.removeTransaction(pos);
					stdin.nextLine();
					System.out.println("\nTransaction has been successfully "
					  + "removed from the general ledger.\n");
					break;
				case "F":
					System.out.print("\nEnter date: ");
					date = stdin.next();
					System.out.println();
					GeneralLedger.filter(list1, date);
					System.out.println();
					stdin.nextLine();
					break;
				case "Q":
					System.out.println("\nProgram teriminated sucessfully");
					stdin.close();
					x = false;
					break;
				case "PF":
					System.out.println("\nAssets: $" + (list1.
					  getTotalDebitAmount()));
					System.out.println("Liabilities: $" + (list1.
					  getTotalCreditAmount() * -1));
					System.out.println(
					  "Net Worth: $" + (list1.getTotalDebitAmount() + 
					  list1.getTotalCreditAmount()) + "\n");
					break;
				case "P":
					if (list1.size() < 1) {
						System.out.println("\nNo transactions currently in the"
					      + " general ledger.\n");
					} else {
						list1.printAllTransactions();
					}
					break;
				case "S":
					System.out.println(
					  "\nThere are " + list1.size() + " transactions currently "
					  + "in the general ledger \n");
					break;
				case "L":
					System.out.print("\nEnter Date: ");
					date = stdin.next();
					System.out.print("Enter Amount($): ");
					amount = stdin.nextDouble();
					stdin.nextLine();
					System.out.print("Enter Description: ");
					description = stdin.nextLine();
					Transaction t1 = new Transaction(date, amount, description);
					if (list1.exists(t1)) {
						for (int i = 0; i < list1.size(); i++) {
							if (list1.getTransaction(i + 1).equals(t1)) {
								list1.printHeader();
								list1.printSpecific(i + 1);
								break;
							}

						}
					}
					break;
				case "B":
					backupCopy = (GeneralLedger) list1.clone();
					System.out.println("\nCreated a backup of the current "
					  + "general ledger.\n");
					break;
				case "PB":
					backupCopy.printAllTransactions();
					break;
				case "RB":
					list1 = (GeneralLedger)backupCopy.clone();
					System.out.println("\nGeneral ledger successfully reverted "
					  + "to the backup copy\n");
					break;
				case "CB":
					if (list1.equals(backupCopy)) {
						System.out.println("\nThe current general ledger is the"
						  + " same as the backup copy \n");
					} else {
						System.out.println("\nThe current general ledger is "
						  + "NOT the same as the back up copy \n");
					}
					break;
				default:
					System.out.println("Enter a valid choice!");
					break;
				}
			} catch (TransactionAlreadyExistsException ex) {
				System.out.println(ex.getMessage());
			} catch (FullGeneralLedgerException ex1) {
				System.out.println(ex1.getMessage());
			} catch (InvalidTransactionException ex2) {
				System.out.println(ex2.getMessage());
			} catch (InvalidLedgerPositionException ex3) {
				System.out.println(ex3.getMessage());
				stdin.nextLine();
			} catch (IllegalArgumentException ex4) {
				System.out.println(ex4.getMessage());
			}
		} while (x);

	}
}
