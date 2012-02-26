package exercises

object Chapter8 extends App {
  // copied from the text
  class BankAccount(initialBalance: Double) {
    private var balance = initialBalance
    def deposit(amount: Double) = { balance += amount; balance }
    def withdraw(amount: Double) = { balance -= amount; balance }
    // added toString to simplify printouts
    override def toString = "Balance: " + balance
  }

  // 1
  class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    override def deposit(amount: Double) = { super.deposit(amount - 10) }
    override def withdraw(amount: Double) = { super.withdraw(amount + 10) }
  }
  {
    val account = new CheckingAccount(100)
    println(account)
    account.deposit(50)
    println(account)
    account.withdraw(50)
    println(account)
  }

  // 2
  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    val monthlyInterestRate = 0.02
    private var freeMonthlyTrx = 3
    // Unless I'm mistaken, the BankAccount's 'private var balance' means we can't get to it at all in a subclass. So
    // I think the text is probably wrong, but we'll treat it as is and shadow that inaccessible variable with our own.
    private var balance = initialBalance
    def earnMonthlyInterest = deposit(balance * monthlyInterestRate)
    override def deposit(amount: Double) = super.deposit(amount - 10) // TODO
    override def withdraw(amount: Double) = super.withdraw(amount + 10) // TODO
  }
}