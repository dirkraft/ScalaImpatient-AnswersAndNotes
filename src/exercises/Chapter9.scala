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
    // This solution does not truncate column contents that are longer than the column is wide (pushing will occur). 
    import io.Source
    val source = Source.fromFile("files/data.tab")
    val lines = source.getLines.toArray
    
    val out = new java.io.PrintWriter("files/data.tab")
    out.close()
  }
}