package Calculator_exp

//As you can see, this repeated pattern matching gets tedious. WE will see how to abstract this later.

sealed trait Expression {
  def eval: Calculation =
    this match {
      case Addition(l, r) =>
        l.eval match {
          case Failure(reason) => Failure(reason)
          case Success(r1) =>
            r.eval match {
              case Failure(reason) => Failure(reason)
              case Success(r2) => Success(r1 + r2)
            }
        }
      case Subtraction(l, r) =>
        l.eval match {
          case Failure(reason) => Failure(reason)
          case Success(r1) =>
            r.eval match {
              case Failure(reason) => Failure(reason)
              case Success(r2) => Success(r1 - r2)
            }
        }
      case Division(l, r) =>
        l.eval match {
          case Failure(reason) => Failure(reason)
          case Success(r1) =>
            r.eval match {
              case Failure(reason) => Failure(reason)
              case Success(r2) =>
                if(r2 == 0)
                  Failure("Division by zero")
                else
                  Success(r1 / r2)
            }
        }
      case SquareRoot(v) =>
        v.eval match {
          case Success(r) =>
            if(r < 0)
              Failure("Square root of negative number")
            else
              Success(Math.sqrt(r))
          case Failure(reason) => Failure(reason)
        }
      case Number(v) => Success(v)
    }
}
final case class Addition(left: Expression, right: Expression) extends Expression
final case class Subtraction(left: Expression, right: Expression) extends Expression
final case class Division(left: Expression, right: Expression) extends Expression
final case class SquareRoot(value: Expression) extends Expression
final case class Number(value: Int) extends Expression