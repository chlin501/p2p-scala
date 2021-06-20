package peer

case class Id(bytes: Seq[Byte]) {

  protected[peer] val sipHashCode = Hashable[SipHashCode]().hash(bytes.toSeq)

  protected[peer] val sha256Code = Hashable[Sha256Code]().hash(bytes.toSeq)

  override def hashCode(): Int = sipHashCode.toInt()

  def xor(): Int = sha256Code.toInt()

}
