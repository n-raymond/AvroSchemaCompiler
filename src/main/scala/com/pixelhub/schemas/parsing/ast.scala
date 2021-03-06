package com.pixelhub.schemas.parsing

import scala.collection.immutable.{Map => ScalaMap}

object ast {

  // TODO: You need to add protocol ast

  sealed trait Schema
  case class Records(name: String, namespace: String, doc: Option[String], aliases: List[String], fields: List[Field]) extends Schema
  case class Enum(name: String, namespace: String, doc: Option[String], aliases: List[String], symbols: List[String]) extends Schema
  case class Array(items: Schema) extends Schema
  case class Map(values: Schema) extends Schema
  case class Union(schemas: List[Schema]) extends Schema
  case class Fixed(name: String, namespace: String, aliases: List[String], size: Integer) extends Schema
  case object NullType extends Schema
  case object BooleanType extends Schema
  case object IntType extends Schema
  case object LongType extends Schema
  case object FloatType extends Schema
  case object DoubleType extends Schema
  case object BytesType extends Schema
  case object StringType extends Schema

  case class Field(name: String, doc: Option[String], aliases: List[String], typ: FieldType, default: Option[Expression], order: Option[Order])

  sealed trait FieldType
  case class SchemaType(schema: Schema)
  case class NamedType(name: String)

  sealed trait Expression
  case class RecordExpr(value: ScalaMap[String, Expression]) extends Expression
  case class EnumExpr(value: String) extends Expression
  case class ArrayExpr(value: List[Expression]) extends Expression
  case class MapExpr(value: ScalaMap[String, Expression]) extends Expression
  case class FixedExpr(value: String) extends Expression
  case object NullExpr extends Expression
  case class BooleanExpr(value: Boolean) extends Expression
  case class IntExpr(value: Int) extends Expression
  case class LongExpr(value: Long) extends Expression
  case class FloatExpr(value: Float) extends Expression
  case class BytesType(value: Byte) extends Expression
  case class StringType(value: String) extends Expression

  sealed trait Order
  case object Asc extends Order
  case object Desc extends Order
  case object Ignore extends Order

}
