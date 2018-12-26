package com.pixelhub.schemas.lexing

import com.pixelhub.schemas.lexing.tokens._
import org.scalatest
import org.scalatest.{FlatSpec, Matchers}

class LexerSpec extends FlatSpec with Matchers {

  import LexerSpec._
  import com.pixelhub.schemas.fixtures._

  /* Integers */

  "int" should "well parse an int" in {
    shouldMatchParsingResult(Lexer.int, "1093", INT(1093))
    shouldMatchParsingResult(Lexer.int, "-1093", INT(-1093))
  }

  it should "fail with an invalid int" in {
    shouldFailToParse(Lexer.int, invalidEntry)
  }


  /* Floats */

  "double" should "well parse a double" in {
    shouldMatchParsingResult(Lexer.double, "9", DOUBLE(9))
    shouldMatchParsingResult(Lexer.double, "-9", DOUBLE(-9))
    shouldMatchParsingResult(Lexer.double, "-9654.5684", DOUBLE(-9654.5684))
    shouldMatchParsingResult(Lexer.double, "-.5543", DOUBLE(-.5543))
    shouldMatchParsingResult(Lexer.double, ".67", DOUBLE(.67))
  }

  it should "fail with an invalid double" in {
    shouldFailToParse(Lexer.double, invalidEntry)
    shouldFailToParse(Lexer.double, "-")
  }



  /* Strings */

  "string" should "well parse a string" in {
    shouldMatchParsingResult(Lexer.string, validString, STRING(validString.trailCommas))
    shouldMatchParsingResult(Lexer.string, "\"\"", STRING(""))
  }


  /* Full tokenizing */

  "apply" should "well parse the handshake fixture" in {
    Lexer(handshake.Source.content) shouldBe Right(handshake.Tokenized.content)
  }


}

object LexerSpec extends Matchers {

  val validIdentifier = "\"a_Valid_1dentifi3r\""
  val validDotedIdentifier = "\"a.Valid.ident1fi3r_\""
  val invalidEntry = "$*#{!"

  val validString = "\"Hey ! I am a valid string with spaces !\""

  def shouldMatchParsingResult[T](parser: Lexer.Parser[T], entry: String, result: T): scalatest.Assertion = {
    Lexer.parse(parser, entry).get shouldBe result
  }

  def shouldFailToParse(parser: Lexer.Parser[_], entry: String): scalatest.Assertion = {
    assert(!Lexer.parse(parser, entry).successful)
  }

}
