package exercises

object Chapter8 extends App {
  // copied from the text, and added print statements, modified balance to 'protected'
  class BankAccount(initialBalance: Double) {
    protected var balance = initialBalance
    println(toString + " New account!")
    def deposit(amount: Double) = {
      balance += amount;
      println(toString + " Deposit")
      balance
    }
    def withdraw(amount: Double) = {
      balance -= amount;
      println(toString + " Withdrawal");
      balance
    }
    // added toString to simplify printouts
    override def toString = "Balance: " + balance
  }


  // 1
  class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    // Implemented assuming 'private var balance' in the base class
    override def deposit(amount: Double) = { super.deposit(amount - 10) }
    override def withdraw(amount: Double) = { super.withdraw(amount + 10) }
  }
  {
    val account = new CheckingAccount(100)
    account.deposit(50)
    account.withdraw(50)
  }


  // 2
  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    val monthlyInterestRate = 0.02
    private var freeMonthlyTrx = 3
    // Unless I'm mistaken, the BankAccount's 'private var balance' means we can't get to it at all in a subclass. So
    // I think the text is probably out of alignment with the intent, and I've changed the base class to have
    // 'protected var balance'.
    def earnMonthlyInterest = {
      balance += balance * monthlyInterestRate
      freeMonthlyTrx = 3
      println(toString + " Accrued interest")
    }
    override def deposit(amount: Double) = {
      super.deposit(amount - (if (freeMonthlyTrx > 0) 0 else 10))
      freeMonthlyTrx -= 1
      balance
    }
    override def withdraw(amount: Double) = {
      super.withdraw(amount + (if (freeMonthlyTrx > 0) 0 else 10))
      freeMonthlyTrx -= 1
      balance
    }
  }
  {
    val account = new SavingsAccount(1000)
    account.earnMonthlyInterest
    for (i <- 1 to 4) account.withdraw(100)
    account.earnMonthlyInterest
    for (i <- 1 to 4) account.deposit(100)
  }


  // 3
  // Trying to come up with a relatively original example, and some sensible members?
  // Manuscript
  // + BoundText
  //   + Book
  //   + Magazine
  // + Poster
  class Manuscript(
      val wordCount: Long,
      val year: Int,
      var condition: String)
  class BoundText(
      wordCount: Long,
      val pageCount: Int,
      year: Int,
      condition: String) extends Manuscript(wordCount, year, condition) {
    def avgWordsPerPage = wordCount / pageCount
  }
  class Book(
      wordCount: Long,
      pageCount: Int,
      val blankPageCount: Int,
      year: Int,
      condition: String) extends BoundText(wordCount, pageCount, year,condition) {
    override def avgWordsPerPage = wordCount / (pageCount - blankPageCount)
  }
  class Magazine(
      wordCount: Long,
      pageCount: Int,
      year: Int,
      condition: String) extends BoundText(wordCount, pageCount, year,condition)
  class Poster(
      wordCount: Long,
      year: Int,
      condition: String,
      val xDim: Float,
      val yDim: Float) extends Manuscript(wordCount, year,condition) {
    val area = xDim * yDim
  }
  // Feel free to flesh this out some more. I'm lazy.


  // 4
  abstract class Item {
    def price: Float
    def description: String
    // added to simplify prints
    override def toString = description + ": " + price
  }

  // override is optional for implementing abstracts
  class SimpleItem(override val price: Float, override val description: String) extends Item
  // Using Traversable as a sort of common type for all collection-y things. 

  class Bundle(val items: collection.mutable.Set[Item], override val description: String) extends Item {
    // This trick just creates a collection of the prices of the items, and then calls sum on that.
    override def price = items.map(_.price).sum
    def add(item: Item) = items += item
  }
  {
    val bandaids = new SimpleItem(0.25f, "Band-aids 20pk")
    val gauze = new SimpleItem(1.20f, "Gauze 20m")
    val sanitizer = new SimpleItem(2.19f, "Sanitizer 200ml")
    val first_aid_kit = new Bundle(collection.mutable.HashSet(bandaids, gauze), "Patching, First-Aid Kit")
    println(bandaids)
    println(gauze)
    println(first_aid_kit)
    first_aid_kit.add(sanitizer)
    println(first_aid_kit)
  }


  // 5
  class Point(val x: Double, val y: Double) {
    override def toString = "(%f, %f)".format(x, y)
  }
  class LabeledPoint(val desc: String, x: Double, y: Double) extends Point(x, y) {
    override def toString = desc + " " + super.toString
  }
  {
    println(new Point(3.5, -7.12))
    println(new LabeledPoint("Pointless", 0, 0))
  }


  // 6
  // re-using Point here for the hell of it
  abstract class Shape(x: Double, y: Double) extends Point(x, y) {
    def centerPoint: (Double, Double)
    override def toString = "Center:" + centerPoint
  }

  // modeling such that (x,y) is the top left corner. The coordinate system increases left-right, up-down
  class Rectangle(x: Double, y: Double, val width: Double, val height: Double) extends Shape(x, y) {
    def centerPoint = (x + width / 2, y - height / 2)
    override def toString = "Location:(%f,%f), Height:%f, Width:%f, ".format(x, y, width, height) + super.toString
  }

  // modeling such that (x,y) is the center.
  class Circle(x: Double, y: Double, val radius: Double) extends Shape(x, y) {
    def centerPoint = (x, y)
    override def toString = "Location:(%f,%f), Radius:%f, ".format(x,y,radius) + super.toString()
  }

  {
    println(new Rectangle(1.5, 6.0, 3, 4.5))
    println(new Circle(5, 5, 2.5))
  }


  // 7
  class Square(x: Int, y: Int, width: Int) extends java.awt.Rectangle(x, y, width, width) {
    // This actually creates specifically, a no-arg constructor and one that only takes a width. With the primary
    // constructor, we have the three requested constructor forms.
    def this(width: Int = 0) {
      this(0, 0, width)
    }
  }
  {
    println(new Square)
    println(new Square(5))
    println(new Square(2, 3, 1))
  }


  // 8
  // Copied from the text on page 91
  class Person(val name: String) {
    override def toString = getClass.getName + "[name=" + name + "]"
  }
  class SecretAgent(codename: String) extends Person(codename) {
    override val name = "secret" // Don’t want to reveal name . . . 
    override val toString = "secret" // . . . or class name
  }
  /*
I'm not sure what you're supposed to get out of the -c option here. Anyways here's the output.

C:\workspace\Scala for the Impatient\bin\exercises>javap -private Chapter8$Person
Compiled from "Chapter8.scala"
public class exercises.Chapter8$Person extends java.lang.Object implements scala.ScalaObject{
    private final java.lang.String name;
    public java.lang.String name();
    public java.lang.String toString();
    public exercises.Chapter8$Person(java.lang.String);
}

C:\workspace\Scala for the Impatient\bin\exercises>javap -private Chapter8$SecretAgent
Compiled from "Chapter8.scala"
public class exercises.Chapter8$SecretAgent extends exercises.Chapter8$Person implements scala.ScalaObject{
    private final java.lang.String name;
    private final java.lang.String toString;
    public java.lang.String name();
    public java.lang.String toString();
    public exercises.Chapter8$SecretAgent(java.lang.String);
}

*/


  // 9
  { // original text, added toString
    class Creature {
      val range: Int = 10
      val env: Array[Int] = new Array[Int](range)
      override def toString = super.toString + " Range:" + range + " Environment:" + env.toBuffer
    }
    class Ant extends Creature {
      override val range = 2
    }
    println(new Creature)
    println(new Ant)
    // notice Ant's env was constructed with range=0 as explained in the text, even though 2 is in the println 
  }
  { // replaced Creature's 'val range' with 'def', added toString, in Ant still using 'val'
    class Creature {
      def range: Int = 10
      val env: Array[Int] = new Array[Int](range)
      override def toString = super.toString + " Range:" + range + " Environment:" + env.toBuffer
    }
    class Ant extends Creature {
      override val range = 2
    }
    println(new Creature)
    println(new Ant) // Ant's env is again constructed with range=0, even though range shows 2 in the println
  }
  { // replaced Creature's 'val range' with 'def', added toString, in Ant used 'def'
    class Creature {
      def range: Int = 10
      val env: Array[Int] = new Array[Int](range)
      override def toString = super.toString + " Range:" + range + " Environment:" + env.toBuffer
    }
    class Ant extends Creature {
      override def range = 2
    }
    println(new Creature)
    println(new Ant) // finally this does what we wanted
  }
  // This is already explained in the text (page 95), but essentially by overriding the getter directly (def), we're
  // ensuring the correct overridden range value is returned to the super class during construction.


  // 10
  /* I think the 'protected' outside the parens () refers to making the primary constructor only visible to extending
   * classes. The 'protected' inside the parens on the field (hence the 'val'), make the field only visible to extending
   * classes. All in the family, no outsiders.
   */
}