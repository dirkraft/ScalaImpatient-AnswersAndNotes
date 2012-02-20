package exercises

object Chapter2 extends App {
  // 1
  def signum(x: Int) = {
    if (x < 0) -1 else if (x == 0) 0 else 1
  }
  println(signum(-10), signum(0), signum(15))

  // 2
  // Type = Unit, value = () 'no useful value'

  // 3
  // FAIL don't know TODO

  // 4
  for (i <- 10 to (0, -1)) println(i)

  // 5
  def countdown(n: Int) = {
    for (n <- n to (0, -1)) print(n)
  }
  countdown(5)
  println()

  // 6
  var prod = 1
  for (i <- "Hello") { prod *= i }
  println(prod)

  // 7
  prod = 1
  "Hello".foreach((c: Char) => prod *= c)
  println(prod)

  // 8
  def product(s: String) = {
    var prod = 1
    s.foreach((c: Char) => prod *= c)
    prod
  }
  println(product("Hello"))

  // 9
  def productRec(s: String): Int = {
    val tail = s.tail
    s.head * (if (tail.size == 0) 1 else productRec(s.tail))
  }
  println(productRec("Hello"))

  // 10
  def intPow(x: Double, n: Int): Double = {
    if (n > 0 & n % 2 == 0) math.pow(intPow(x, n >> 1), 2)
    // n is odd...
    else if (n > 0) x * intPow(x, n - 1)
    else if (n == 0) 1
    else /*(n < 0)*/ 1 / intPow(x, -n) 
  }
  println(math.pow(2, 2), intPow(2, 2))
  println(math.pow(2, -2), intPow(2, -2))
}