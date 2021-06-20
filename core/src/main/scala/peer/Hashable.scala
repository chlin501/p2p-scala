package peer

import com.google.common.hash.HashCode
import com.google.common.hash.Hasher
import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing

trait Hashable[T] {

  def hash(bytes: Seq[Byte]): Code

}
object Hashable {

  def apply[T: Hashable](): Hashable[T] = implicitly[Hashable[T]]

  implicit val sipHashable = new Hashable[SipHashCode] {

    override def hash(bytes: Seq[Byte]): Code =
      SipHashCode(Hashing.sipHash24(), bytes)
  }

  implicit val sha256Hashable = new Hashable[Sha256Code] {
    override def hash(bytes: Seq[Byte]): Code =
      Sha256Code(Hashing.sha256(), bytes)
  }
}

trait Code {

  def toString(): String

  def toBytes(): Array[Byte]

  def toInt(): Int

  def toLong(): Long

}

protected[peer] case class SipHashCode(
    sipHash: HashFunction,
    bytes: Seq[Byte]
) extends Code {

  override def toString(): String =
    sipHash.newHasher().putBytes(bytes.toArray).hash().toString

  override def toBytes(): Array[Byte] = bytes.toArray

  override def toInt(): Int =
    sipHash.newHasher().putBytes(bytes.toArray).hash().asInt()

  override def toLong(): Long =
    sipHash.newHasher().putBytes(bytes.toArray).hash().asLong()

}

protected[peer] case class Sha256Code(
    sha256Hash: HashFunction,
    bytes: Seq[Byte]
) extends Code {

  override def toString(): String =
    sha256Hash.newHasher().putBytes(bytes.toArray).hash().toString

  override def toBytes(): Array[Byte] = bytes.toArray

  override def toInt(): Int =
    sha256Hash.newHasher().putBytes(bytes.toArray).hash().asInt

  override def toLong(): Long =
    sha256Hash.newHasher().putBytes(bytes.toArray).hash().asLong

}
