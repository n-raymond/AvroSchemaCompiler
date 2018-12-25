package com.pixelhub.schemas

import com.pixelhub.schemas.lexing.Lexer._
import com.pixelhub.schemas.lexing.tokens.Token

package object lexing {

  /* String utility */

  implicit class StringUtils(str: String) {
    def trailCommas = str.substring(1, str.length - 1)

    def surroundedByCommas = '"' + str + '"'

    def asFieldName = str.surroundedByCommas + ":"
  }


  /* Parser utility */

  def keywordParser[T <: Token](keyword: String, token: T): Parser[T] = {
    keyword ^^ (_ => token)
  }

  def kewordWithCommasParser[T <: Token](keyword: String, token: T): Parser[T] = {
    keywordParser(keyword.surroundedByCommas, token)
  }

  def fieldNameParser[T <: Token](keyword: String, token: T): Parser[T] = {
    keywordParser(keyword.asFieldName, token)
  }

  def stringValueParser[T <: Token](keyword: String, token: String => T): Parser[T] = {
    keyword.surroundedByCommas.r ^^ { str => token(str.trailCommas) }
  }

}
