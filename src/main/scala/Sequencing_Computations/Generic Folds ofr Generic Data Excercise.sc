//5.3.4.1 Tree

sealed trait Tree[A] {
  def fold[B](leaf: A=>B, fn: (B, B) => B): B =
    this match {
      case Node(left, right) => fn(left.fold(leaf, fn), right.fold(leaf, fn))
      case Leaf(value) => leaf(value)
    }

  override def toString: String =
    fold[String](v => v.toString, (a, b) => a + " " + b)
}

final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

val tree: Tree[String] =
  Node(Node(Leaf("To"), Leaf("iterate")),
    Node(Node(Leaf("is"), Leaf("human,")),
      Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

tree.toString

/*
sealed trait Tree[A] {
  def fold[B](node: (B, B) => B, leaf: A => B): B
}
final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A
] {
  def fold[B](node: (B, B) => B, leaf: A => B): B =
    node(left.fold(node, leaf), right.fold(node, leaf))
}
final case class Leaf[A](value: A) extends Tree[A] {
  def fold[B](node: (B, B) => B, leaf: A => B): B =
    leaf(value)
}
val tree: Tree[String] =
  Node(Node(Leaf("To"), Leaf("iterate")),
    Node(Node(Leaf("is"), Leaf("human,")),
      Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

tree.fold[String]((a, b) => a + " " + b, str => str)
 */