package com.dinso

import Impl.TriePrefixMatcher

class TriePrefixMatcherTest extends org.scalatest.funsuite.AnyFunSuite {


  test("Trie should build correctly with sample prefixes") {
    val trie = new TriePrefixMatcher()
    trie.init()
    assert(trie.countWordsStartingWith("2y3f") == 29)
    assert(trie.countWordsEqualTo("xyiuoXZV") == 1)
  }

  test("TriePrefixMatcher") {
    val triePrefixMatcher = new TriePrefixMatcher()
    triePrefixMatcher.init()
    assert(triePrefixMatcher.findLongestPrefix("tru") === "tru")
  }

  test("findLongestPrefix should return correct longest prefixes for sample data") {
    val trie = new TriePrefixMatcher()
    trie.init()
    assert(trie.findLongestPrefix("2y3fKTSabc") == "2y3fKTS")
    assert(trie.findLongestPrefix("xyiuoXZV123") == "xyiuoXZV")
    assert(trie.findLongestPrefix("KAWeqI") == "KAWeqI")
    assert(trie.findLongestPrefix("Xfhddddd") == "Xfhdd")
    assert(trie.findLongestPrefix("pqYLoZzzzz") == "pqYLoZ")
  }

  test("Trie should handle prefixes with no matches in sample data") {
    val trie = new TriePrefixMatcher()
    trie.init()
    assert(trie.findLongestPrefix("nonexistent") == "")
    assert(trie.findLongestPrefix("zzzzzzzzzz") == "")
  }

  test("Trie should handle empty prefix") {
    val trie = new TriePrefixMatcher()
    trie.init()
    assert(trie.findLongestPrefix("") == "")
  }

}
