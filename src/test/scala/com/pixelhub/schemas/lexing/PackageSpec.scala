package com.pixelhub.schemas.lexing

import org.scalatest.{FlatSpec, Matchers}

class PackageSpec extends FlatSpec with Matchers {

  "trailCommas" should "well trail first and last characters" in {
    "\"Youpi !\"".trailCommas shouldBe "Youpi !"
  }

  "surroundedByCommas" should "well add commat at begining and end of string" in {
    "Youpi !".surroundedByCommas shouldBe "\"Youpi !\""
  }

  "asFieldName" should "surround by commas and add a colon" in {
    "name".asFieldName shouldBe "\"name\":"
  }

}
