package com.pixelhub.schemas.lexing

import tokens._

import scala.util.parsing.combinator.RegexParsers

object Lexer extends RegexParsers {

  /* Values */

  def int: Parser[INT] = {
    "-?[0-9][0-9]*".r ^^ { str => INT(str.toInt) }
  }

  def double: Parser[DOUBLE] = {
    "(-?[0-9][0-9]*(\\.[0-9]*)?|-?\\.[0-9]*)".r ^^ { str => DOUBLE(str.toDouble) }
  }

  def string: Parser[STRING] = {
    stringValueParser("[^\"]*", STRING.apply)
  }

  /* Value keywords */

  private def nullKeyWord = keywordParser("null", NULL)
  private def trueKeyWord= keywordParser("true", TRUE)
  private def falseKeyWord = keywordParser("false", FALSE)

  /* Type Keywords */

  private def boolKeyWord = kewordWithCommasParser("bool", BOOL_KEYWORD)
  private def intKeyWord = kewordWithCommasParser("int", INT_KEYWORD)
  private def longKeyWord = kewordWithCommasParser("long", LONG_KEYWORD)
  private def doubleKeyWord = kewordWithCommasParser("double", DOUBLE_KEYWORD)
  private def stringKeyWord = kewordWithCommasParser("string", STRING_KEYWORD)
  private def byteKeyWord = kewordWithCommasParser("byte", BYTE_KEYWORD)
  private def enumKeyWord = kewordWithCommasParser("enum", ENUM_KEYWORD)
  private def recordKeyWord = kewordWithCommasParser("record", RECORD_KEYWORD)
  private def fixedKeyWord = kewordWithCommasParser("fixed", FIXED_KEYWORD)
  private def unionKeyWord = kewordWithCommasParser("union", UNION_KEYWORD)
  private def mapKeyword = kewordWithCommasParser("map", MAP_KEYWORD)

  /* Schema Keywords */

  private def protocol = fieldNameParser("protocol", PROTOCOL)
  private def namespace = fieldNameParser("namespace", NAMESPACE)
  private def types = fieldNameParser("types", TYPES)
  private def typ = fieldNameParser("type", TYPE)
  private def name = fieldNameParser("name", NAME)
  private def doc = fieldNameParser("doc", DOC)
  private def aliases = fieldNameParser("aliases", ALIASES)
  private def fields = fieldNameParser("fields", FIELDS)
  private def symbols = fieldNameParser("symbols", SYMBOLS)
  private def default = fieldNameParser("default", DEFAULT)
  private def order = fieldNameParser("order", ORDER)
  private def asc = fieldNameParser("ascending", ASC)
  private def desc = fieldNameParser("descending", DESC)
  private def ignore = fieldNameParser("ignore", IGNORE)
  private def size = fieldNameParser("size", SIZE)
  private def values = fieldNameParser("values", VALUES)

  /* Punctuation*/

  private def openedBrace = keywordParser("(", OPENED_BRACE)
  private def closedBrace = keywordParser(")", CLOSED_BRACE)
  private def openedBracket = keywordParser("[", OPENED_BRACKET)
  private def closedBracket = keywordParser("]", CLOSED_BRACKET)
  private def colon = keywordParser(":", COLON)
  private def comma = keywordParser(",", COMMA)

  def tokens: Parser[List[Token]] = {
    phrase(
      rep1(
        /* Predominant parsers should be on the top of the list to be chosen in case of ambiguity */
        nullKeyWord
        | trueKeyWord
        | falseKeyWord
        | openedBrace
        | closedBrace
        | openedBracket
        | closedBracket
        | colon
        | comma
        | boolKeyWord
        | intKeyWord
        | longKeyWord
        | doubleKeyWord
        | stringKeyWord
        | byteKeyWord
        | enumKeyWord
        | recordKeyWord
        | fixedKeyWord
        | unionKeyWord
        | mapKeyword
        | protocol
        | namespace
        | types
        | typ
        | name
        | doc
        | aliases
        | fields
        | symbols
        | default
        | order
        | asc
        | desc
        | ignore
        | size
        | int
        | double
        | string
      )
    )
  }

  def apply(schema: String): Either[TokenizingError, List[Token]] = {
    parse(tokens, schema) match {
      case Success(result, _) => Right(result)
      case NoSuccess(msg, _) => Left(TokenizingError(msg))
    }
  }

  case class TokenizingError(msg: String)

}