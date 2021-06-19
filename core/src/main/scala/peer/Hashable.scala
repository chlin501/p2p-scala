package peer

import com.google.common.hash.HashCode

trait Hashable {
  def hash(): Seq[Byte]
}

case class Code(value: HashCode) {

  override def toString(): String = value.toString

  def toBytes(): Array[Byte] = value.asBytes

  def toInt(): Int = value.asInt

  def toLong(): Long = value.asLong

}
