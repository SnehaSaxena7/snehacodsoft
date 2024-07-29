import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        } else {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
    }

    public void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
    }
}

class ATM {
    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = getUserChoice();
            if (choice == 4) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            }
            handleChoice(choice);
        }
    }

    private void showMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    private int getUserChoice() {
        System.out.print("Choose an option (1-4): ");
        return scanner.nextInt();
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                handleWithdraw();
                break;
            case 2:
                handleDeposit();
                break;
            case 3:
                account.checkBalance();
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                break;
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(500.0); // Starting balance
        ATM atm = new ATM(account);
        atm.start();
    }
}
