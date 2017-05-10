package cafebabe

import ClassFileTypes._

case class StackMapAttributeInfo(override val attributeNameIndex: U2, entries: Seq[U1]) extends AttributeInfo(attributeNameIndex, Nil) {
  override def toStream(stream: ByteStream): ByteStream = {
    stream <<
      attributeNameIndex <<
      (2 + entries.size).asInstanceOf[U4] <<
      entries.size.asInstanceOf[U2]

    entries foreach (stream << _)
    stream
  }

  override def size = 8 + entries.size
}
