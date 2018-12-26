package com.pixelhub.schemas.parsing

import com.pixelhub.schemas.fixtures.handshake
import org.scalatest.{FlatSpec, Matchers}

class SchemaParserSpec extends FlatSpec with Matchers {

  "apply" should "well parse the handshake fixture" in {
    SchemaParser(handshake.Tokenized.content) shouldBe Right(handshake.Parsed.content)
  }

}
