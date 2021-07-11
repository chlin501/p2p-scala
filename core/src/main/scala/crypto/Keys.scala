package crypto

import java.security.{
  KeyPairGenerator,
  PrivateKey,
  PublicKey,
  KeyPair => JKeyPair
}

sealed trait KeyType
case object RSA extends KeyType
sealed trait KeySize {
  def length: Int
}
case object Key1024 extends KeySize {
  override def length: Int = 1024
}
case object Key2048 extends KeySize {
  override def length: Int = 2048
}
case object Key3072 extends KeySize {
  override def length: Int = 3072
}
case object Key4096 extends KeySize {
  override def length: Int = 4096
}

object Keys {
  def create(keyType: KeyType = RSA, keySize: KeySize = Key3072): KeyPair =
    keyType match {
      case RSA =>
        val keyGen = KeyPairGenerator.getInstance("RSA")
        keyGen.initialize(keySize.length)
        val keyPair = keyGen.generateKeyPair()
        KeyPair(keyPair)
    }
}
case class KeyPair(pair: JKeyPair) {
  def privateKey: PrivateKey = pair.getPrivate
  def publicKey: PublicKey = pair.getPublic
}
