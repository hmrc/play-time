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

object DateHelper extends DateHelper

trait DateHelper {

  def fakeTimeOffsetInMillis: Long = {
    getFakeDateString() match {
      case Some(s: String) =>
        val fakeTime = new LocalDate(s).toDateTimeAtStartOfDay().getMillis
        val currentTime = getCurrentDate().toDateTimeAtStartOfDay.getMillis
        fakeTime - currentTime
      case _ => 0
    }

  }

  def now(): LocalDate = {
    getFakeDateString() match {
      case None => getCurrentDate()
      case Some(d) => new LocalDate(getCurrentDate().toDateTimeAtCurrentTime.getMillis + getFakeDateOffset())
    }
  }

  def getFakeDateString(): Option[String] = {
    sys.props.get("feature.fakeDate")
  }

  def isFakeDateActive(): Boolean = getFakeDateString().nonEmpty

  def getFakeDateOffset(): Long = fakeTimeOffsetInMillis

  // need this so can override in testing
  protected[time] def getCurrentDate() = new LocalDate()
}

