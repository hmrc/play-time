/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.cato.time

import org.joda.time.LocalDate
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

class DateHelperSpec extends WordSpec with MockitoSugar with Matchers with BeforeAndAfter {

  case class TestDateHelper(fakeCurrentDate: String = new LocalDate().toString) extends DateHelper {

    override def getCurrentDate(): LocalDate = {
      new LocalDate(fakeCurrentDate)
    }
  }

  override protected def before(fun: => Any): Unit = clearFakeDateString()

  "DateHelper" should {

    "return empty fakeTimeOffset when not set" in  {
      val pretendActualDate: String = "2001-01-01"
      val dateHelper = TestDateHelper(pretendActualDate)
      dateHelper.getFakeDateOffset() shouldBe 0
    }

    "return fakeTimeOffset with date in future" in  {
      val fakeDate: String = "2001-01-02"
      val pretendActualDate: String = "2001-01-01"

      setFakeDateString(fakeDate)
      val dateHelper = TestDateHelper(pretendActualDate)

      dateHelper.getFakeDateOffset() shouldBe 86400000L //millis in 1 day
    }

    "return fakeTimeOffset with date in past" in  {
      val fakeDate: String = "2001-01-01"
      val pretendActualDate: String = "2001-01-02"

      setFakeDateString(fakeDate)
      val dateHelper = TestDateHelper(pretendActualDate)

      dateHelper.getFakeDateOffset() shouldBe(-86400000L) //millis in 1 day
    }

    "return date specified by sys prop when asked" in {
      val dateString: String = "2001-01-02"
      val pretendActualDate: String = "2001-01-01"

      setFakeDateString(dateString)
      val dateHelper = TestDateHelper(pretendActualDate)

      dateHelper.now() shouldBe new LocalDate(dateString)
    }

    "return date specified by sys prop when asked (always tomorrow)" in {

      val dateString: String = new LocalDate().plusDays(1).toDateTimeAtStartOfDay.toLocalDate.toString
      setFakeDateString(dateString)

      val dateHelper = TestDateHelper()
      dateHelper.now() shouldBe new LocalDate().plusDays(1).toDateTimeAtStartOfDay().toLocalDate
    }

    "detect time set ahead" in {
      val dateString: String = new LocalDate().plusDays(1).toDateTimeAtStartOfDay.toLocalDate.toString
      setFakeDateString(dateString)

      val dateHelper = TestDateHelper()
      dateHelper.isNowSetAhead shouldBe true
    }

    "return now in long String format" in {
      val dateString: String = new LocalDate(2052, 12, 31).toDateTimeAtStartOfDay.toLocalDate.toString
      setFakeDateString(dateString)

      val dateHelper = TestDateHelper()
      dateHelper.getFakeDateLongString().get shouldBe "2052-12-31T00:00:00"
    }
  }


  private def clearFakeDateString() = sys.props.remove("feature.fakeDate")

  private def setFakeDateString(dateString: String): Option[String] = sys.props.put("feature.fakeDate", dateString)
}
