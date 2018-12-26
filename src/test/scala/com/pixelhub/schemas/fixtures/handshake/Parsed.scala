package com.pixelhub.schemas.fixtures.handshake

import com.pixelhub.schemas.parsing.ast._

object Parsed {

  val content: Schema = {
    Record(
      name = "HandshakeRequest",
      namespace = Some("org.apache.avro.ipc"),
      fields = List(
        Field(
          name = "clientHash",
          typ = SchemaType(
            Fixed(
              name = "MD5",
              size = 16
            )
          )
        ),
        Field(
          name = "clientProtocol",
          typ = SchemaType(
            Union(List(NullType, StringType))
          )
        ),
        Field(
          name = "serverHash",
          typ = NamedType("MD5")
        ),
        Field(
          name = "meta",
          typ = SchemaType(
            Union(
              List(
                NullType,
                Map(BytesType)
              )
            )
          )
        )
      )
    )
  }

}
