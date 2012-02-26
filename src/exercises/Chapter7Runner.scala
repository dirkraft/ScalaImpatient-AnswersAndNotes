package exercises

// Sorry, can't keep it all in one file this time. I have to be somewhat careful not to pollute namespaces between
// exercises too :/
object Chapter7Runner extends App {
  // 1
  // More awkward wording. This seems exactly contrary to the lesson of 7.4 which explains how these two forms can be
  // equivalent. I think I have an example of how they are different, but am unsure as to whether this was the intent
  // of the question.

  // Absorb Q1_Setup.scala . Then take a look at the difference between Q1_SinglePackage.scala and Q1_MultiPackage.scala

  // 2
  // Look at Q2_Run.scala, follow it through to see that the constructor I'm calling is indeed private. Figure it out?
  // ... Realize that there's package shadowing effect caused by Q2_Hack.scala .

  // 3
  {
    import Chapter7.Q3.random._
    for (i <- 1 to 5) print(nextInt + " ")
    println
    setSeed(0)
    for (i <- 1 to 5) print(nextInt + " ")
    println
    setSeed(System.currentTimeMillis.toInt)
    for (i <- 1 to 5) print(nextInt + " ")
    println

    setSeed(0)
    for (i <- 1 to 5) print(nextDouble + " ")
    println
    setSeed(0)
    for (i <- 1 to 5) print(nextDouble + " ")
    println
    setSeed(System.currentTimeMillis.toInt)
    for (i <- 1 to 5) print(nextDouble + " ")
    println
  }

  // 4
  // Perhaps as a way to narrow unintentional consequences, e.g. accidentally putting stuff outside of defined classes
  // and objects. Perhaps as a way to advertise a limitation of the JVM to wary developers.

  // 5
  // private[...] is a scope modifier that customizes the private-ness (really, loosening on restrictions of 'private').
  // private[com] whatever will allow access to this thingy to anything com and narrower. The scope of 'com' depends on
  // its context, like how question 2 introduces its own 'com' package that is actually far from the _root_ package.

  // 6
  {
    import java.util.{HashMap => JavaHashMap}
    import collection.mutable.{HashMap => ScalaHashMap}
    def toScalaMap[K, V](jMap: JavaHashMap[K, V]) = {
      val sMap = ScalaHashMap[K,V]();
      // The writer already mentioned conversions, right? So just using those. There's probably a JavaConverter, but
      // this way will definitely give us a completely new scala.collection.mutable.HashMap . I think the converter
      // just wraps the original Java map, though could be wrong.
      import collection.JavaConversions.mapAsScalaMap
      for ((k,v) <- jMap)  sMap.put(k,v)
      sMap
    }
    val jMap = new JavaHashMap[String, Int]();
    jMap.put("one", 1)
    jMap.put("two", 2)
    jMap.put("three", 3)
    println(jMap)
    println(toScalaMap(jMap))
  }

  // 7
  // I believe 6 is written with the imports at the inner-most level.

  // 8
  // It makes all the sub-packages available as starting reference points to other packages and types. 
  // Good. Bad. It's all a matter of subjective perspective (opinion). I would be careful with this one. Because so
  // many package names are being mashed into the current context, you might quickly start to run into collision
  // problems. But sometimes we're lazy. And sometimes being lazy turns out okay. Sometimes it doesn't, and you end up
  // doing more work to fix the problems caused by the early laziness.

  // 9
  { // to help prove I am only doing one import, and no additional qualified names, I omitted all omittable dots
    import java.lang.System
    print("Authorization token: ")
    val username = System getProperty "user.name"
    val password = readLine
    if ("secret" equals password) println("Hello, " + username + ". Welcome to the real world.")
    else System.err println "Sorry, you are not identified. A robot death squad will be dispatched to your location after 3 failed attempts."
    // You can also use Console just as well. Either way requires one dot to 'err'
  }

  // 10
  // No way. Not doing this by eye. Have fun, guys. In any IDE that supports Scala (e.g. Eclipse>Scala IDE), put your
  // cursor within 'StringBuilder' below and press F3 (or however you go to the definition). You will recognize many
  // Java things that Scala is shadowing, and some Java things that Scala is aliasing.
  scala.StringBuilder
}