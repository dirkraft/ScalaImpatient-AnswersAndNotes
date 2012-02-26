package exercises.Chapter7.Q3

package object random {

  var seed = 0

  val a = 1664525
  val b = 1013904223
  val n = 32

  def setSeed(seed: Int) = this.seed = seed

  def next() = {
    val res = seed * a + b % (2 * n)
    seed = res
    res
  }

  def nextInt = next()

  // Text doesn't specify any guidelines for doubles. I'll take that to mean something easy, -1.0 to 1.0
  def nextDouble = 1.0 / next()
}