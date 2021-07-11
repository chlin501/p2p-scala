package peer

import crypto.{Key3072, KeyPair, Keys, RSA}
import io.ipfs.multibase.Base58

import java.security.{MessageDigest, PublicKey}

object Id {

  def fromBase58(base58str: String): Id =
    Id(Base58.decode(base58str).toSeq)

  def fromPublicKey(
      pubkey: PublicKey,
      inlining: Boolean = true,
      maxInlineKeyLength: Int = 42
  ): Id = {
    val bytes = pubkey.getEncoded
    // TODO: Here it allows to have different choices sch as sha256 and 0x00
    //       See https://github.com/libp2p/py-libp2p/blob/master/libp2p/peer/id.py#L10
    //val algorithm = sha2_256
    //if (inlining && (bytes.length <= maxInlineKeyLength))
    //  0x00
    //else sha2_256
    val sha256 = MessageDigest.getInstance("SHA-256")
    Id(sha256.digest(bytes).toSeq)
  }

  def fromKeyPair(keypairOpt: Option[KeyPair] = None): Id = {
    val pubKey = keypairOpt match {
      case Some(keypair) => keypair.publicKey
      case None =>
        val keypair = Keys.create(keyType = RSA, keySize = Key3072)
        keypair.publicKey
    }
    fromPublicKey(pubKey)
  }

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
