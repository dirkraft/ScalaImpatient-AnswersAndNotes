package exercises

// Simply for the sake of pure torture, I've eliminated as many '.' as possible in this exercise. Just awful. Let's just
// see what the consequences are through this exercise.
object Chapter4 extends App {
  // 1
  val wishlist: Map[String, Double] = Map(
    "Elephantine Braille Input Device" -> 322000,
    "Microsoft Word 95" -> 10,
    "4TB Ram Drive" -> 32000,
    "Apple" -> 0.60)
  println(wishlist)
  println(for ((k, v) <- wishlist) yield k -> (v * 0.9))

  // 2
  {
    val in = new java.util.Scanner(new java.io.File("files/scanned-file.txt"))
    val wordCounts = collection.mutable.Map[String, Int]() withDefault (_ => 0) // awesome
    while (in hasNext) wordCounts(in next) += 1 // previous line's "awesome" makes this easy
    println(wordCounts)
  }

  // 3
  {
    val in = new java.util.Scanner(new java.io.File("files/scanned-file.txt"))
    var wordCounts = Map[String, Int]() withDefault (_ => 0)
    while (in hasNext) {
      val key = in next
      val currentCount = wordCounts(key)
      wordCounts = wordCounts - key + (key -> (currentCount + 1))
    }
    println(wordCounts)
  }

  // 4
  {
    val in = new java.util.Scanner(new java.io.File("files/scanned-file.txt"))
    var wordCounts = collection.immutable.SortedMap[String, Int]() withDefault (_ => 0)
    while (in hasNext) {
      val key = in next
      val currentCount = wordCounts(key)
      wordCounts = wordCounts - key + (key -> (currentCount + 1))
    }
    println(wordCounts)
  }

  // 5
  {
    val in = new java.util.Scanner(new java.io.File("files/scanned-file.txt"))
    val wordCounts = new java.util.TreeMap[String, Int]
    while (in hasNext) {
      val key = in next
      // If we omit both the . and () for "in.next()", the compiler sees ambiguity here. Need one of those or a blank line!

      if (!(wordCounts containsKey key)) wordCounts put (key, 0)
      wordCounts put (key, (wordCounts get key) + 1) // Just awful, parens (or dots) required, or else order of ops will cause wrong behavior.
    }
    println(wordCounts)
  }

  // 6
  import java.util.Calendar._
  val stringsToCalConst = collection.mutable.LinkedHashMap(
    "Monday" -> MONDAY,
    "Tuesday" -> TUESDAY,
    "Wednesday" -> WEDNESDAY,
    "Thursday" -> THURSDAY,
    "Friday" -> FRIDAY,
    "Saturday" -> SATURDAY,
    "Sunday" -> SUNDAY)
  println(stringsToCalConst)

  // 7
  val props = collection.JavaConversions asMap (System getProperties)
  val maxLengthKey = ((props keySet) toList) maxBy (_ size) // '.' would make this a lot easier. Many extra parens are necessary.
  for ((k, v) <- props) {
    println(k + " " * ((maxLengthKey size) - (k size)) + " | " + v) // This makes a good case why being pro-whitespace can be dead wrong.
  }

  // 8
  val arr = Array(-5, 0, 5, 9, -2, 17, 3)
  def minmax(values: Array[Int]) = {
    (values min, values max)
  }
  println(minmax(arr))

  // 9
  def lteqgt(values: Array[Int], v: Int) = {
    ((values filter (_ < v)) size, (values filter (_ == v)) size, (values filter (_ > v)) size) // All except tuple's parens could be eliminated with '.'
  }
  println(lteqgt(arr, 0))

  // 10
  println("Hello".zip("World"))
  // Encryption?

  // Allowing so many '.' to be omitted can easily become detrimental as above. If it is to be, it is up to me... D.A.R.E
}