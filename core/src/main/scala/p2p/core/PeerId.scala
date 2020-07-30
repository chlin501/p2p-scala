package p2p.core

import scala.util.Either

object PeerId {
  def from(bytes: Array[Byte]): Either[Throwable, PeerId] = {
    if (32 > bytes.size || 50 < bytes.size)
      Left(new IllegalArgumentException(s"Invalid peer length: ${bytes.size}"))
    else
      Right(PeerId(bytes.toSeq))
  }
}
case class PeerId(bytes: Seq[Byte])