package com.pixelhub.schemas.lexing.fixtures.handshake

import com.pixelhub.schemas.lexing.tokens._

case object Result {

  def content: List[Token] = List(
    OPENED_BRACE,
      TYPE, RECORD_KEYWORD, COMMA,
      NAME, STRING("HandshakeRequest"), COMMA,
      NAMESPACE, STRING("org.apache.avro.ipc"), COMMA,
      FIELDS, OPENED_BRACKET,
        OPENED_BRACE,
          NAME, STRING("clientHash"), COMMA,
          TYPE, OPENED_BRACE,
            TYPE, FIXED_KEYWORD, COMMA,
            NAME, STRING("MD5"), COMMA,
            SIZE, INT(16),
          CLOSED_BRACE,
        CLOSED_BRACE, COMMA,
        OPENED_BRACKET,
          NAME, STRING("clientProtocol"), COMMA,
          TYPE, OPENED_BRACKET, NULL, COMMA, STRING_KEYWORD, CLOSED_BRACKET,
        CLOSED_BRACE, COMMA,
        OPENED_BRACE, NAME, STRING("serverHash"), COMMA, TYPE, STRING("MD5"), CLOSED_BRACE, COMMA,
        OPENED_BRACE,
          NAME, STRING("meta"), COMMA,
          TYPE, OPENED_BRACKET,
            NULL, COMMA,
            OPENED_BRACE, MAP_KEYWORD, COMMA, VALUES, BYTE_KEYWORD, CLOSED_BRACE,
          CLOSED_BRACKET,
        CLOSED_BRACE,
      CLOSED_BRACKET,
    CLOSED_BRACE
  )

}
