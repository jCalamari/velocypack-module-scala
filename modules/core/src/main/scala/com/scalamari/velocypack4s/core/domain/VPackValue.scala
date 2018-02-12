package com.scalamari.velocypack4s.core.domain

import java.util.Date

private[velocypack4s] sealed trait VPackValue

private[velocypack4s] final case class VPackObject(fields: Vector[(String, VPackValue)]) extends VPackValue

private[velocypack4s] final case class VPackArray(items: Vector[VPackValue]) extends VPackValue

private[velocypack4s] final case class VPackString(value: String) extends VPackValue

private[velocypack4s] final case class VPackDouble(value: Double) extends VPackValue

private[velocypack4s] final case class VPackInt(value: Int) extends VPackValue

private[velocypack4s] final case class VPackFloat(value: Float) extends VPackValue

private[velocypack4s] final case class VPackLong(value: Long) extends VPackValue

private[velocypack4s] final case class VPackShort(value: Short) extends VPackValue

private[velocypack4s] final case class VPackChar(value: Char) extends VPackValue

private[velocypack4s] final case class VPackByte(value: Byte) extends VPackValue

private[velocypack4s] final case class VPackDate(value: Date) extends VPackValue

private[velocypack4s] final case class VPackBigDecimal(value: BigDecimal) extends VPackValue

private[velocypack4s] final case class VPackBigInt(value: BigInt) extends VPackValue

private[velocypack4s] final case class VPackBoolean(value: Boolean) extends VPackValue

private[velocypack4s] case object VPackNull extends VPackValue

private[velocypack4s] case object VPackEnd extends VPackValue

private[velocypack4s] object VPackArray {

  def apply(fields: VPackValue*): VPackArray = VPackArray(fields.toVector)

}

private[velocypack4s] object VPackObject {

  def apply(fields: (String, VPackValue)*): VPackObject = VPackObject(fields.toVector)

}
