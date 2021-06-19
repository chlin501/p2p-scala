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

  implicit val sipHashable = new Hashable[HashCode] {

    val sipHash = Hashing.sipHash24()
    val hasher = sipHash.newHasher()

    override def hash(bytes: Seq[Byte]): Code =
      SipHashCode(sipHash, hasher, bytes)

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
    hasher: Hasher,
    bytes: Seq[Byte]
) extends Code {

  protected[peer] val hasCode = hasher.putBytes(bytes.toArray)

  override def toString(): String = hashCode.toString

  override def toBytes(): Array[Byte] = bytes.toArray

  override def toInt(): Int = hashCode.toInt

  override def toLong(): Long = hashCode.toLong

}
