package com.pixelhub.schemas.parsing


import com.pixelhub.schemas.lexing.tokens._
import com.pixelhub.schemas.parsing.ast._

import scala.util.parsing.combinator.Parsers
import scala.util.parsing.input.{NoPosition, Position, Reader}

object SchemaParser extends Parsers {

  override type Elem = Token

  class TokenReader(tokens: Seq[Token]) extends Reader[Token] {
    override def first: Token = tokens.head
    override def atEnd: Boolean = tokens.isEmpty
    override def pos: Position = NoPosition
    override def rest: Reader[Token] = new TokenReader(tokens.tail)
  }

  case class ParsingError(msg: String)


  def apply(tokens: List[Token]): Either[ParsingError, Schema] = {
    val reader = new TokenReader(tokens)
    schema(reader) match {
      case NoSuccess(msg, _) => Left(ParsingError(msg))
      case Success(result, _) => Right(result)
    }
  }

  def schema: Parser[Schema] = {
    (OPENED_BRACE ~ typ(RECORD_KEYWORD) ~ name ~ namespace ~ doc ~ aliases ~ fields /*~ CLOSED_BRACE*/) ^^
      { case _ ~ _ ~ n ~ ns ~ d ~ a ~ f /*~ _*/ => Record(n, ns, d, a, f) }
  }

  def name: Parser[String] = bindedWithStringWithComma(NAME)

  def namespace: Parser[Option[String]] = opt(bindedWithStringWithComma(NAMESPACE))

  def doc: Parser[Option[String]] = opt(bindedWithStringWithComma(DOC))

  def aliases: Parser[List[String]] = {
    opt(ALIASES ~ OPENED_BRACKET ~ rep1(string) ~ CLOSED_BRACKET) ^^ {
      case Some(_ ~ _ ~ as ~ _) => as map { case STRING(s) => s }
      case None => List()
    }
  }

  def fields: Parser[List[Field]] = FIELDS ^^ {_ => List()}





  def string: Parser[STRING] = {
    accept("string literal", { case id @ STRING(_) => id })
  }


  /* Helper */

  def bindedWithStringWithComma(key: Token): Parser[String] = {
    key ~ string ~ COMMA ^^ { case _ ~ STRING(s) ~ _ => s}
  }

  def typ(typ: Token) = {
    TYPE ~ typ ~ COMMA
  }



}
