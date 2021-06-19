package peer

case class Id(bytes: Array[Byte]) {

  protected[peer] val code = Hashable().hash(bytes.toSeq)

  override def hashCode(): Int = code.toInt()

}
