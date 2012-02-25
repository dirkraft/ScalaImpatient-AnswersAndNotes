package exercises

object Chapter6 extends App {
  // 1
  object Conversions {
    def inchesToCentimetres(inches: Double) = 2.54 * inches
    def gallonsToLitres(gallons: Double) = 3.78541178 * gallons
    def milesToKilometres(miles: Double) = 1.609344 * miles
  }

  // 2
  class UnitConversion(val conversionFactor: Double) {
    def convert(units: Double) = conversionFactor * units
  }
  object InchesToCentimetres extends UnitConversion(2.54)
  object GallonsToLitres extends UnitConversion(3.78541178)
  object MilesToKilometres extends UnitConversion(1.609344)

  // 3
  object Origin extends java.awt.Point
  // I suppose because Point has mutators, and you wouldn't want to allow moving the Origin.

  // 4
  class Point(x: Int, y: Int) extends java.awt.Point(x, y)
  object Point {
    def apply(x: Int, y: Int) = new Point(x, y)
  }
  println(Point(5, 10))

  // 5
  // This is a bit meta (since we're already in an App)
  object InnerApp extends App {
    for (arg <- args.reverse) println(arg)
  }
  InnerApp.main(Array("1", "2", "3", "4", "5")) // I guess this is a way to simulate an App run

  // 6
  // Had to fix some Eclipse settings on this one. Went to Window->Preferences->General->Workspace: Forced 'Text File
  // Encoding' to Other: UTF-8. Also had to make sure all files in the project were saved in UTF-8 encoding, otherwise
  // the scala compiler choked and then Scala IDE choked on that (so you couldn't even really see the error).
  object Suits extends Enumeration {
    type Suits = Value // for #7
    val Clover = Value("♣")
    val Diamond = Value("♦")
    val Heart = Value("♥")
    val Spade = Value("♠")
  }
  println(Suits.values)

  // 7
  import Suits._
  def isRed(suit: Suits) = suit == Heart || suit == Diamond
  // pairing with boolean that indicates the suit is red
  println(for (s <- Suits.values) yield (s, isRed(s)))

  // 8
  // I guess this is what they mean. Too lazy to Google it. Too lazy TO GOOGLE IT. So lazy...
  object RGBCubeCorners extends Enumeration {
    val Black = Value(0x000000)
    val Red = Value(0xff0000)
    val Green = Value(0x00ff00)
    val Blue = Value(0x0000ff)
    val Yellow = Value(0xffff00)
    val Cyan = Value(0x00ffff)
    val Magenta = Value(0xff00ff)
    val White = Value(0xffffff)
  }
  println(for (color <- RGBCubeCorners.values) yield ("0x%06x".format(0xFFFFFF & color.id), color))
  // Google on...
  // Found and altered format String from http://stackoverflow.com/questions/6539879/how-to-convert-a-color-integer-to-a-hex-string-in-android
  // Adapted to Scala from http://www.scala-lang.org/node/732#comment-1356
}