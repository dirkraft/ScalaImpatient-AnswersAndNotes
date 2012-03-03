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
    out.flush()
    out.close()
  }

  // 3
  {
    
  }
}