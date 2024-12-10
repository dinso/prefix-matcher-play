package com.dinso
package service

import helpers.{ConfigHelper, Trie}

import scala.io.Source

// Different Implementations cam be made and use the features available by this trait
trait PrefixMatcher {
  private val filename = ConfigHelper.getStringValue("prefixes-data.fileName")  // configured in resources/application.conf
  protected val prefixes: List[String] = readFile(filename)

  def findLongestPrefix(input: String): String

  private def readFile(filename: String): List[String] = {
    val bufferedSource = Source.fromFile(filename)
    try {
      bufferedSource.getLines.toList
    } finally {
      bufferedSource.close
    }
  }
}
