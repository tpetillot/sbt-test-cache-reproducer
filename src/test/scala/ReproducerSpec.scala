import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class ReproducerSpec extends AnyFlatSpec with Matchers {

  "hello.txt" should "contain \"hello\"" in {
    val source = Source.fromResource("hello.txt")
    try source.mkString.trim shouldBe "hello"
    finally source.close()
  }
}
