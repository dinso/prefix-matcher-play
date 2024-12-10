package com.dinso
package helpers

import com.typesafe.config.ConfigFactory

object ConfigHelper {

  private val config = ConfigFactory.load()

  def getStringValue(path: String): String = config.getString(path)

  def getIntValue(path: String): Int = config.getInt(path)

  // Add more methods for other data types as needed
}
