package peer

import io.ipfs.multihash.Multihash
import io.ipfs.multibase.Base58

case class Id(bytes: Seq[Byte]) {

  protected[peer] val sipHashCode = Hashable[SipHashCode]().hash(bytes.toSeq)

  protected[peer] val sha256Code = Hashable[Sha256Code]().hash(bytes.toSeq)

  override def hashCode(): Int = sipHashCode.toInt()

  def xor(): BigInt = BigInt(sha256Code.toString(), 16)

  def base58(): String = Base58.encode(bytes.toArray)

}
