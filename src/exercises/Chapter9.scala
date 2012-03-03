package exercises


object Chapter9 extends App {

  // 1
  {
    import io.Source
    val source = Source.fromFile("files/LoremIpsum.txt")
    val lines = source.getLines.toArray
    val out = new java.io.PrintWriter("files/LoremIpsum.txt")
    for (line <- lines.reverse) out.println(line)
    out.close()
  }

  // 2
  {
    val n = 16
    // This solution does not truncate column contents that are longer than the column is wide (pushing will occur). 
    import io.Source
    val source = Source.fromFile("files/data.tab")
    val out = new java.io.PrintWriter("files/data2.tab")
    // Not writing to the same file on this one like the question asked.
    val lines = source.getLines.toArray // all into memory
    for (line <- lines) {
      val tokenz = line.split("""\t""")
      for (token <- tokenz) out.print(token + " " * (n - token.length))
      out.println
    }
    out.close()
  }

  // 3
  for (line <- io.Source.fromFile("files/LoremIpsum.txt").getLines) for (word <- line.split("""\s+""") if word.size > 12) println(word)

  // 4
  {
    val floats = io.Source.fromFile("files/numbers.txt").mkString.split("""\s+""").map(_.toFloat) // sick
    println(floats.sum)
    println(floats.sum / floats.size)
    println(floats.max)
    println(floats.min)
  }

  // 5
  for (i <- 0 to 20) {
    val whole = math.pow(2, i).toInt.toString
    val frac = math.pow(2, -i)
    println(whole + " " * (10 - whole.size) + frac)
  }

  // 6
  // So this prints the String literals in a Java source file
  {
    import io.Source
    val file = Source.fromFile("src/exercises/Chapter5_Java.java").mkString
    // The regex is "(?:\\"|.)*?" . The """regex""" syntax isn't always better. I'm not sure if this is perfect, but
    // it gets some of the weirdness in Chapter5_Java.java alright.
    val strings = """"(?:\\"|.)*?"""".r.findAllIn(file).toList
    println(strings)
  }

  // 7
  {
    import io.Source
    val file = Source.fromFile("files/numbers n more.txt").mkString
    // The regex means (digits with a period within or ending it) or (a period in front of digits).In other words, each
    // token is all the stuff between valid floating point numbers, where these are all valid floating points:
    //   5.  2.5  -.2  (as they are in Java/Scala)
    val strings = file.split("""-?(\d\.\d*|\.\d+)""").toList
    // Printing Strings surround by ^startuntilend$ to clarify result.
    for (string <- strings) println("^" + string + "$")
  }

  // 8
  {
    // Shamelessly copied from placekitten.com . You should use it for your placeholders. It is appropriate for all the
    // sites, all the situations.
    import io.Source
    val file = Source.fromFile("files/placekitten.com.html").mkString
    val regex = """<\s*?img\s+[^>]*\s+src="(.*?)"\s+[^>]*>""".r
    for (regex(imgsrc) <- regex.findAllIn(file) ) println(imgsrc)
  }

  // 9
  {
    // Cheater way. I'm on a Windows machine which has a "find.exe" which does something else. So I've created a cyg.bat
    // which forces using the cygwin libs, i.e. exactly as it is, this is not portable. On most other machines, I would
    // imagine removing the cyg.bat will work. The counts should match between both the shell and jvm strategies.
    import sys.process._
    // Next line commented out because it is not quite portable.
    //"""cyg.bat find . -name "*.class" -type f""" #| "wc -l" !
    // Here's a non-shell way. Function copied from text page 105
    import java.io.File
    def subdirs(dir: File): Iterator[File] = {
      val children = dir.listFiles.filter(_.isDirectory)
      children.toIterator ++ children.toIterator.flatMap(subdirs _)
    }
    // Now count'em up. By mashing some statements into the for (loopage), we can yield all matches individually.
    val classFiles = for (
      dir <- subdirs(new File("."));
      // That f => is an anonymous function to filter, which is doing a quick and dirty regex string match. 
      classFile <- dir.listFiles.filter(f => """^.+\.class$""".r.findFirstIn(f.getName).isDefined)
    ) yield classFile
    println(classFiles.size)
  }

  // 10
  // Copied from text page 106
  @SerialVersionUID(1L) class Person(val name: String, val age: Int) extends Serializable {
    override def toString = name + "(" + age + ")"
  }
  {
    val hal = new Person("Hal", 45)
    val meth = new Person("Methuselah", 969)
    val bob = new Person("Silent Bob", 28)
    val trex = new Person("T-Rex Bones", 2580450)
    val people = List(hal, meth, bob, trex)
    println(people)
    import java.io._
    val out = new ObjectOutputStream(new FileOutputStream("files/ObjectStore"))
    out.writeObject(people)
    out.close()
  }
  {
    import java.io._
    val in = new ObjectInputStream(new FileInputStream("files/ObjectStore"))
    val people = in.readObject().asInstanceOf[List[Person]]
    println(people)
  }
}