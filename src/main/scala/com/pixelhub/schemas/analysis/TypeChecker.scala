package com.pixelhub.schemas.analysis

class TypeChecker {

  /*
   * TODO: Check names and namespaces validity
   * "[a-zA-Z_][a-zA-Z0-9_]*" <- names
   * "[a-zA-Z_][a-zA-Z0-9_]*(\\.[a-zA-Z_][a-zA-Z0-9_]*)*" <- namespaces
   * Don't forget the different rules....
   */

  /* Tests...

  /* Identifiers */

  "identifier" should "well parse a valid identifier" in {
    shouldMatchParsingResult(Lexer.identifier, validIdentifier, IDENTIFIER(validIdentifier.trailCommas))
  }

  it should "fail with an invalid identifier" in {
    shouldFailToParse(Lexer.identifier, invalidEntry)
  }

  it should "fail to reckognize a dotedIdentifier" in {
    shouldFailToParse(Lexer.identifier, validDotedIdentifier)
  }


  /* Doted Identifiers */

  "dotedIdentifier" should "well parse a valid doted identifier" in {
    shouldMatchParsingResult(Lexer.dotedIdentifier, validIdentifier, DOTED_IDENTIFIER(validIdentifier.trailCommas))
    shouldMatchParsingResult(Lexer.dotedIdentifier, validDotedIdentifier, DOTED_IDENTIFIER(validDotedIdentifier.trailCommas))
  }

  it should "fail with an invalid identifier" in {
    shouldFailToParse(Lexer.dotedIdentifier, invalidEntry)
    shouldFailToParse(Lexer.dotedIdentifier, "\".abc\"")
  }

   */

}
