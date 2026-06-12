package Practice;
import java.util.Scanner;
import java.util.ArrayList;


public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();




        accounts.add(new SavingsAccount("SAV001", "Vikas Kumar", 1500.00, 6.5));
        accounts.add(new SavingsAccount("SAV002", "Rupam Kumari", 8000.00, 7.0));
        accounts.add(new CurrentAccount("CUR001", "Manish", 25000.00, 15000.00));
        accounts.add(new CurrentAccount("CUR002", "Aslam beg" ,5000.00, 10000.00));


        System.out.println("-----------------------------------------");
        System.out.println("     WELCOME TO B.TECH BANKING SYSTEM");
        System.out.println("-----------------------------------------");


        // First find the user
        System.out.print("\n Enter your account number: ");
        String accNum = scanner.nextLine();


        System.out.print("🔍 Enter your name: ");
        String name = scanner.nextLine();


        Account loggedInAccount = null;


        for(Account acc : accounts) {
            if(acc.getAccountNumber().equals(accNum) && acc.getOwnerName().equalsIgnoreCase(name)) {
                loggedInAccount = acc;
                break;
            }
        }


        if(loggedInAccount == null) {
            System.out.println("\n Account not found! Please check your account number and name.");
            scanner.close();
            return;
        }


        System.out.println("\n Login successful!");
        System.out.println("Welcome back, " + loggedInAccount.getOwnerName() + "!");


        // Menu system
        int choice;
        do {
            System.out.println("\n------------------$---------------------");
            System.out.println("           BANKING MENU");
            System.out.println("-------------$------------------------");
            System.out.println("1  Deposit Money");
            System.out.println("2  Withdraw Money");
            System.out.println("3 Check Balance");
            System.out.println("4  View Account Details");
            System.out.println("5  Exit");
            System.out.println("----------------------------------------");
            System.out.print(" Enter your choice: ");


            choice = scanner.nextInt();


            switch(choice) {
                case 1:
                    System.out.print("\n Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    loggedInAccount.deposit(depositAmount);
                    break;


                case 2:
                    System.out.print("\n Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    loggedInAccount.withdraw(withdrawAmount);
                    break;


                case 3:
                    loggedInAccount.checkBalance();
                    break;


                case 4:
                    loggedInAccount.display();
                    break;


                case 5:
                    System.out.println("\n Thank you for banking with us!");
                    System.out.println("Have a great day, " + loggedInAccount.getOwnerName() + "!");
                    break;


                default:
                    System.out.println("\n Invalid choice! Please enter 1-5.");
            }


        } while(choice != 5);


        scanner.close();
    }
}

CurrentAccount.java
package Practice;


public class CurrentAccount extends Account {
    private double overdraftLimit;


    public CurrentAccount(String accountNumber, String ownerName, double balance, double overdraftLimit) {
        super(accountNumber, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }


    public double getOverdraftLimit() {
        return overdraftLimit;
    }


    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }


    @Override
    public void withdraw(double amount) {
        if(amount <= 0) {
            System.out.println("\n Invalid amount! Please enter positive amount.");
        } else {
            double availableBalance = getBalance() + overdraftLimit;
            if(amount > availableBalance) {
                System.out.println("\n Sorry! Insufficient funds (including overdraft limit of ₹" + overdraftLimit + ")");
                System.out.println(" Your available balance including overdraft is: ₹" + availableBalance);
            } else {
                setBalance(getBalance() - amount);
                System.out.println("\n Successfully withdrawn: ₹" + amount);
                System.out.println(" Your remaining balance is: ₹" + getBalance());
                if(getBalance() < 0) {
                    System.out.println("  You are using overdraft: ₹" + Math.abs(getBalance()));
                    System.out.println("  Remaining overdraft limit: ₹" + (overdraftLimit + getBalance()));
                }
            }
        }
    }


    @Override
    public void display() {
        super.display();
        System.out.println("Account Type: CURRENT ACCOUNT");
        System.out.println("Overdraft Limit: ₹" + overdraftLimit);
        System.out.println("Total Available (with overdraft): ₹" + (getBalance() + overdraftLimit));
    }
}

SavingAccount.java
package Practice;


public class SavingsAccount extends Account {
    private double interestRate;


    public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
    }


    public double getInterestRate() {
        return interestRate;
    }


    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }


    public void addInterest() {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
        System.out.println(" Interest added: ₹" + String.format("%.2f", interest) + " at " + interestRate + "% rate");
    }


    @Override
    public void display() {
        super.display();
        System.out.println("Account Type: SAVINGS ACCOUNT");
        System.out.println("Interest Rate: " + interestRate + "%");
    }


    @Override
    public void withdraw(double amount) {
        if(amount <= 0) {
            System.out.println("\n Invalid amount! Please enter positive amount.");
        } else if(amount > getBalance()) {
            System.out.println("\n Sorry! Insufficient balance in savings account.");
            System.out.println(" Your current balance is: ₹" + getBalance());
        } else {
            setBalance(getBalance() - amount);
            System.out.println("\n Successfully withdrawn: ₹" + amount);
            System.out.println(" Your remaining balance is: ₹" + getBalance());
        }
    }
}

Account.java
package Practice;
import java.util.Scanner;
import java.util.ArrayList;


public class Account {
    private String accountNumber;
    private String ownerName;
    private double balance;


    public Account() {
        this("Unknown", "Unknown", 0.0);
    }


    public Account(String accountNumber, String ownerName) {
        this(accountNumber, ownerName, 0.0);
    }


    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }


    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getOwnerName() {
        return ownerName;
    }


    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    public double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }


    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
            System.out.println("\n Successfully deposited: ₹" + amount);
            System.out.println(" Your current balance is: ₹" + this.balance);
        } else {
            System.out.println("\n Invalid amount! Please enter positive amount.");
        }
    }


    public void withdraw(double amount) {
        if(amount <= 0) {
            System.out.println("\n Invalid amount! Please enter positive amount.");
        } else if(amount > this.balance) {
            System.out.println("\n Sorry! Insufficient balance.");
            System.out.println(" Your current balance is: ₹" + this.balance);
        } else {
            this.balance -= amount;
            System.out.println("\n Successfully withdrawn: ₹" + amount);
            System.out.println(" Your remaining balance is: ₹" + this.balance);
        }
    }


    public void checkBalance() {
        System.out.println("\n💰 Your current balance is: ₹" + String.format("%.2f", this.balance));
    }


    public void display() {
        System.out.println("\n========== ACCOUNT DETAILS ==========");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + ownerName);
        System.out.println("Current Balance: ₹" + String.format("%.2f", balance));
        System.out.println("======================================");
    }
}
