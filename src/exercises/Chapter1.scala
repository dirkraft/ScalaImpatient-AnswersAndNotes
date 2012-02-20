package exercises

object Chapter1 extends App {
  // 1
  // Do it.
  
  // 2
  println(3 - math.pow(math.sqrt(3), 2))
  
  // 3
  // val
  
  // 4
  println("crazy"*3)
  
  // 5
  println(10 max 2)
  
  // 6
  println(BigInt(2) pow 1024)
  
  // 7
  import BigInt.probablePrime, util.Random
  probablePrime(100, Random)
  
  // 8
  println(BigInt(100, Random) toString 36)
  
  // 9
  println("astring"(0), "astring".last)
  
  // 10
  println("abcdefg" take 3, "abcdefg" drop 3, "abcdefg" takeRight 3, "abcdefg" dropRight 3)
}
