package com.pixelhub.schemas.lexing

object tokens {

  sealed trait Token

  /* Values */

  case class INT(int: Int) extends Token
  case class DOUBLE(double: Double) extends Token
  case class STRING(str: String) extends Token

  /* Value keyword */

  case object NULL extends Token
  case object TRUE extends Token
  case object FALSE extends Token

  /* Type keywords */

  case object BOOL_KEYWORD extends Token
  case object INT_KEYWORD extends Token
  case object LONG_KEYWORD extends Token
  case object DOUBLE_KEYWORD extends Token
  case object STRING_KEYWORD extends Token
  case object BYTE_KEYWORD extends Token
  case object ENUM_KEYWORD extends Token
  case object RECORD_KEYWORD extends Token
  case object FIXED_KEYWORD extends Token
  case object UNION_KEYWORD extends Token
  case object MAP_KEYWORD extends Token

  /* Schema keywords */

  case object PROTOCOL extends Token
  case object NAMESPACE extends Token
  case object TYPES extends Token
  case object TYPE extends Token
  case object NAME extends Token
  case object DOC extends Token
  case object ALIASES extends Token
  case object FIELDS extends Token
  case object SYMBOLS extends Token
  case object DEFAULT extends Token
  case object ORDER extends Token
  case object ASC extends Token
  case object DESC extends Token
  case object IGNORE extends Token
  case object SIZE extends Token
  case object VALUES extends Token

  /* Punctuation */

  case object OPENED_BRACE extends Token
  case object CLOSED_BRACE extends Token
  case object OPENED_BRACKET extends Token
  case object CLOSED_BRACKET extends Token
  case object COLON extends Token  /* TODO: This will not be usefull anmyore */
  case object COMMA extends Token

}
