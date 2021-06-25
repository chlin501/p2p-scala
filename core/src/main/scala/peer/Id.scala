package peer

import io.ipfs.multibase.Base58

object Id {
  def fromBase58(base58str: String): Id =
    Id(Base58.decode(base58str).toSeq)
}
case class Id(bytes: Seq[Byte]) {

  protected[peer] val sipHashCode: Code =
    Hashable[SipHashCode]().hash(bytes)

  protected[peer] val sha256Code: Code =
    Hashable[Sha256Code]().hash(bytes)

  override def hashCode(): Int = sipHashCode.toInt()

  def xor(): BigInt = BigInt(sha256Code.toString, 16)

  def base58(): String = Base58.encode(bytes.toArray)

}
