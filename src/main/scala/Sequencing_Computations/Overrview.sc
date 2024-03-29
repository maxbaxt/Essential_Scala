//IN this section we're going to look at two more language features, GENERICS and FUNCTIONS, and see
//some abstractions we can build using these features: FUNCTORS and MONADS

//Consider the linked list from earlier:

sealed trait IntList {
  def length: Int =
    this match {
      case End => 0
      case Pair(hd, tl) => 1 + tl.length
    }
  def double: IntList =
    this match {
      case End => End
      case Pair(hd, tl) => Pair(hd * 2, tl.double)
    }
  def product: Int =
    this match {
      case End => 1
      case Pair(hd, tl) => hd * tl.product
    }
  def sum: Int =
    this match {
      case End => 0
      case Pair(hd, tl) => hd + tl.sum
    }
}
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

//Two problems: Our list is restricted to storing Ints. The second is a lot of repetition.
//The former is addressed with GENERICS to abstract over types
//The latter will use FUNCTIONS to abstract over methods

//Category theory link:
//FUNCTIONS = FUNCTORS; GENERICS = MONADS?
