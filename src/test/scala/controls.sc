Iterator.from(1)
.map{i: Int =>
  Map("email" -> s"user$i@gatling.io")
}






















for (i <- 0 to 9) yield i

0 to 9

def evenOrOdd(number: Int): String =
  if (number % 2 == 0)
    "even"
else
    "odd"

val list = List(1,2)
val list2 = 3 :: List(1,2)

val pair = (1,"hello")
val element1 = pair._2
val (element2, element3) = pair

val map = Map("a" -> 1, "b" -> 2)

val arrayBuffer = collection.mutable.ArrayBuffer(1,2)
arrayBuffer += 3

for (i <- List(1,2)) println(i)

val result =
  for {
    i <- List(1,2) if i % 2 == 0
    j <- List(3,4,12)
  } yield i * j
println(result)


val option = Option("foo")
val i: String = null
val opt2 = Option(i)


for {
  opt1 <- Some("ok")
} println(opt1)

def concat(opt1: Option[String],
          opt2: Option[String]): Option[String] = for {
  i <- opt1
j <- opt2 } yield i + j

concat(Some("foo"), Some("bar"))
concat(Some("foo"), None)
concat(None, Some("None"))
concat(None, None)




