package peer

import com.google.common.hash.Hashing

object Id {
  def hash(hashable: Hashable): Code = {
    val hashFn = Hashing.sipHash24()
    return Code(hashFn.newHasher().putBytes(hashable.hash().toArray).hash)
  }
}
case class Id(bytes: Array[Byte]) {

  override def hashCode(): Int =
    Id.hash(new Hashable {
      override def hash(): Seq[Byte] = bytes.toSeq
    }).toInt()

}
