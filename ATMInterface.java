import java.util.Scanner;

// BankAccount class to store and manage account balance
class BankAccount {
    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Check balance
    public double getBalance() {
        return balance;
    }
}

// ATM class to handle user interaction
class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Start ATM operations
    public void start() {
        System.out.println("Welcome to the ATM!");
        System.out.println("Your Current Balance: ₹" + account.getBalance());

        while (true) {
            System.out.println("\n====== ATM MENU ======");
            System.out.println("1. Withdraw Money");
            System.out.println("2. Deposit Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print(" Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Successfully withdrawn: ₹" + withdrawAmount);
                        System.out.println("Remaining Balance: ₹" + account.getBalance());
                    } else {
                        System.out.println("Insufficient balance or invalid amount!");
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Successfully deposited: ₹" + depositAmount);
                    System.out.println(" Updated Balance: ₹" + account.getBalance());
                    break;

                case 3:
                    System.out.println(" Current Balance: ₹" + account.getBalance());
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM. Please take your card.");
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

// Main Class
public class ATMInterface {
    public static void main(String[] args) {
        // Create a bank account with initial balance ₹5000
        BankAccount account = new BankAccount(5000);

        // Connect ATM with Bank Account
        ATM atm = new ATM(account);

        // Start ATM operations
        atm.start();
    }
}