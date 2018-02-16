package com.scalamari.velocypack4s

import com.arangodb.velocypack.{VPackBuilder, VPackSerializationContext, VPackSerializer}
import com.scalamari.velocypack4s.core.compiler.VPackCompiler
import com.scalamari.velocypack4s.core.domain._
import com.scalamari.velocypack4s.core.format._

package object core extends BasicFormats with CollectionFormats {

  def serializer[A](implicit enc: VPackFormat[A]): VPackSerializer[A] = new VPackSerializer[A] {
    override def serialize(builder: VPackBuilder, attribute: String, value: A, context: VPackSerializationContext): Unit = {
      VPackCompiler.toSlice(enc.write(value), builder, attribute)
    }
  }

  class SerializationException(message: String) extends RuntimeException(message)

  class DeserializationException(message: String) extends RuntimeException(message)

  def serializationError(message: String) = throw new SerializationException(message)

  def deserializationError(message: String) = throw new DeserializationException(message)

  implicit class RichAny[T](value: T) {
    def toVPack(implicit ev: VPackWriter[T]): VPackValue = ev.write(value)
  }

  implicit class RichVPackValue(value: VPackValue) {
    def convertTo[T](implicit ev: VPackReader[T]): T = ev.read(value)
  }

}
