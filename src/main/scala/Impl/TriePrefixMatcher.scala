package com.dinso
package Impl

import helpers.Trie
import service.PrefixMatcher

class TriePrefixMatcher() extends Trie with PrefixMatcher {

  def init(): Unit = {
    this.generate(prefixes)
  }

  // Find the longest matching prefix for the given input string
  def findLongestPrefix(input: String): String = {
    findLongestPrefixes(input)
  }

}
