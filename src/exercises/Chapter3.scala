package exercises
import scala.collection.mutable.ArrayBuffer

// All the toList inside the println's are just so that the automatic toString produces something informative.
object Chapter3 extends App {
  // 1
  def randArr(len: Int) = {
    (for (i <- 1 to len) yield util.Random.nextInt(11) - 5).toArray // Range [-5, 5]  ( [] mean inclusive )
  }
  val a = randArr(10)
  println(a.toList)

  // 2
  def swapPairsInPlace(arr: Array[Int]) = {
    for (i <- 0 until (if (arr.length % 2 == 0) arr.length else arr.length - 1, 2)) {
      val t = arr(i)
      arr(i) = arr(i + 1)
      arr(i + 1) = t
    }
    arr
  }
  println(swapPairsInPlace(a.clone).toList)
  // odd one test
  println(swapPairsInPlace(Array.concat(a, Array(2))).toList)

  // 3
  def swapPairs(arr: Array[Int]) = {
    (for (i <- 0 until arr.length)
      // first conditional takes care of the odd-length case
      yield if (i == arr.length - 1 & i % 2 == 0) arr(i) else if (i % 2 == 0) arr(i + 1) else arr(i - 1)).toArray
  }
  val q2 = swapPairs(a)
  println(q2.toList)
  // odd one test
  println(swapPairs(Array.concat(a, Array(3))).toList);

  // 4
  def posThenNeg(arr: Array[Int]) = {
    Array.concat(for (i <- arr if i > 0) yield i, for (i <- arr if i <= 0) yield i)
  }
  println(posThenNeg(a).toList)

  // 5
  val dbl = Array(0.5, 1.2, 3.7, 4.1, 2.2)
  println(dbl.sum / dbl.length)

  // 6
  var aClone = a.clone
  util.Sorting.quickSort(aClone)
  aClone = aClone.reverse // Warn: This creates a copy which quicksort does not.
  println(aClone.toList)
  val aBuf = a.toBuffer
  println(aBuf.sortWith(_ > _)) // Warn: Book example does not compile. aBuf.sortBy(-_) also works

  // 7
  println(a.distinct.toList)

  // 8
  // Warn: Maybe an older version of scala, drop behaved differently? This question is nonsense to all of 2.9.1's
  // IndexedSeqOptimized.drop(Int) .dropRight .dropWhile  as I understand them.
  // Here's the example. Warn again!: Text does not compile, s.b. 'if elem' not 'if a'
//  println((for (elem <- a if elem % 2 == 0) yield 2 * elem).toList)
  // Here's a filter (to pick even numbers) and map-with-anonymous-function (to double them) solution.
  // _ * 2   is the shorthand for   (x: Int) => 2 * x   ?
  println(a.filter(_ % 2 == 0).map(_ * 2).toList) // filter reminds me of Django :)

  // 9
  println(java.util.TimeZone.getAvailableIDs().filter(_.startsWith("America/")).map(_.stripPrefix("America/")).toList)

  // 10
  import java.awt.datatransfer._
  val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
  println(collection.JavaConversions.asBuffer(flavors.getNativesForFlavor(DataFlavor.imageFlavor)))
  // Found this other fat way, but probably noms up extra cycles for no reason (fat). Interestingly you get an explicit
  // ArrayBuffer instead of just a Buffer like the previous line.
//  println(flavors.getNativesForFlavor(DataFlavor.imageFlavor).toArray.toBuffer)
}
