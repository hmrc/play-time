/*
 * Copyright 2024 HM Revenue & Customs
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

import java.time.temporal.ChronoUnit
import java.time.{LocalDate, LocalDateTime, ZoneOffset}
import scala.util.Try

object DateHelper extends DateHelper

trait DateHelper {

  def fakeTimeOffsetInMillis(): Long = {
    getFakeDateString match {
      case Some(s: String) =>
        val fakeTime = {
          Try(LocalDate.parse(s))
            .getOrElse(LocalDateTime.parse(s).toLocalDate).atStartOfDay()
            .toInstant(ZoneOffset.UTC).toEpochMilli
        }
        val currentTime = getCurrentDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli
        fakeTime - currentTime
      case _ => 0
    }

  }

  def now(): LocalDate = {
    getFakeDateString match {
      case None => getCurrentDate
      case Some(_) => getCurrentDate.atStartOfDay().plus(getFakeDateOffset, ChronoUnit.MILLIS).toLocalDate
    }
  }

  def getFakeDateString: Option[String] = {
    Option(System.getProperty("feature.fakeDate"))
  }

  def getFakeDateLongString: Option[String] = {
    getFakeDateString.map(_ + ":00")
  }

  def isFakeDateActive: Boolean = getFakeDateString.nonEmpty

  def getFakeDateOffset: Long = fakeTimeOffsetInMillis()

  def isNowSetAhead: Boolean = isFakeDateActive && getCurrentDate.isBefore(now())

  // need this so can override in testing
  protected[time] def getCurrentDate = LocalDate.now()
}
