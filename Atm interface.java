import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
class Transaction {
    String type;
    int amount;
    int balanceAfterTransaction;

    public Transaction(String type, int amount, int balanceAfterTransaction) {
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", balanceAfterTransaction=" + balanceAfterTransaction +
                '}';
    }
}
class BankAccount {
    int accountNumber;
    String userName;
    int balance;
    int pin;
    static int totalAccounts = 0;
    List<Transaction> transactions;
    public BankAccount() {
        transactions = new ArrayList<>();
    }
    public void createAccount() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter Account Holder name: ");
        userName = scanner.nextLine();

        System.out.print("Enter Initial Deposit Balance: ");
        balance = scanner.nextInt();
        transactions.add(new Transaction("Deposit", balance, balance));

        System.out.print("Enter Pin to secure your account: ");
        pin = scanner.nextInt();

        accountNumber = random.nextInt(10000) + 1;
        totalAccounts++;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount, balance));
    }

    public boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount, balance));
            return true;
        } else {
            return false;
        }
    }

    public void printTransactionHistory() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

class ATM {
    public void deposit(BankAccount account) {
        Scanner scanner = new Scanner(System.in);
        int cash;

        System.out.print("Enter Amount to be Deposited: ");
        cash = scanner.nextInt();

        account.deposit(cash);
        System.out.println("Deposited Successfully into the Account");
    }

    public void withdraw(BankAccount account) {
        Scanner scanner = new Scanner(System.in);
        int amount;

        System.out.print("Enter Amount to Withdraw: ");
        amount = scanner.nextInt();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal Successful!");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    public void checkBalance(BankAccount account) {
        System.out.printf("The current balance is %d\n", account.getBalance());
    }

    public void viewTransactionHistory(BankAccount account) {
        account.printTransactionHistory();
    }

    public BankAccount findAccount(BankAccount[] accounts, int accountNumber) {
        for (BankAccount account : accounts) {
            if (account != null && account.accountNumber == accountNumber) {
                return account;
            }
        }
        return null;
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount[] accounts = new BankAccount[10];
        ATM atm = new ATM();

        System.out.println("Welcome to our BANK");

        while (true) {
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transaction History");
            System.out.println("9. Exit");
            System.out.print("Enter Your Choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (BankAccount.totalAccounts < 10) {
                        BankAccount account = new BankAccount();
                        account.createAccount();
                        accounts[BankAccount.totalAccounts - 1] = account;
                        System.out.printf("Account Created Successfully. Your Account Number is %d\n", account.accountNumber);
                    } else {
                        System.out.println("Maximum Accounts Limit Reached!");
                    }
                    break;
                case 2:
                    System.out.print("Enter Account Number to deposit Amount: ");
                    int depositAccountNumber = scanner.nextInt();
                    BankAccount depositAccount = atm.findAccount(accounts, depositAccountNumber);
                    if (depositAccount != null) {
                        atm.deposit(depositAccount);
                    } else {
                        System.out.println("Account Not Found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Account Number to withdraw Amount: ");
                    int withdrawAccountNumber = scanner.nextInt();
                    BankAccount withdrawAccount = atm.findAccount(accounts, withdrawAccountNumber);
                    if (withdrawAccount != null) {
                        atm.withdraw(withdrawAccount);
                    } else {
                        System.out.println("Account Not Found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter Account Number to check Balance: ");
                    int balanceAccountNumber = scanner.nextInt();
                    BankAccount balanceAccount = atm.findAccount(accounts, balanceAccountNumber);
                    if (balanceAccount != null) {
                        atm.checkBalance(balanceAccount);
                    } else {
                        System.out.println("Account Not Found!");
                    }
                    break;
                case 5:
                    System.out.print("Enter Account Number to view Transaction History: ");
                    int historyAccountNumber = scanner.nextInt();
                    BankAccount historyAccount = atm.findAccount(accounts, historyAccountNumber);
                    if (historyAccount != null) {
                        atm.viewTransactionHistory(historyAccount);
                    } else {
                        System.out.println("Account Not Found!");
                    }
                    break;
                case 9:
                    System.out.println("Thank You for Banking");
                    System.exit(0);
                default:
                    System.out.println("Enter a Correct Choice!!!");
            }
        }
    }
}
