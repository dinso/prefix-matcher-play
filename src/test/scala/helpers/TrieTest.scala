package com.dinso
package helpers

import org.scalatest.funsuite.AnyFunSuiteLike

class TrieTest extends AnyFunSuiteLike {

  test("Trie should insert words correctly and countWordsEqualTo should return correct count") {
    val trie = new Trie()
    trie.insert("apple")
    trie.insert("app")
    trie.insert("apple")
    assert(trie.countWordsEqualTo("apple") == 2)
    assert(trie.countWordsEqualTo("app") == 1)
    assert(trie.countWordsEqualTo("banana") == 0)
  }

  test("Trie should count words starting with a given prefix correctly") {
    val trie = new Trie()
    trie.insert("apple")
    trie.insert("app")
    trie.insert("apricot")
    assert(trie.countWordsStartingWith("app") == 2)
    assert(trie.countWordsStartingWith("a") == 3)
    assert(trie.countWordsStartingWith("ap") == 3)
    assert(trie.countWordsStartingWith("b") == 0)
  }

  test("Trie should erase words correctly") {
    val trie = new Trie()
    trie.insert("apple")
    trie.insert("app")
    trie.insert("apple")
    trie.erase("apple")
    assert(trie.countWordsEqualTo("apple") == 1)
    trie.erase("apple")
    assert(trie.countWordsEqualTo("apple") == 0)
    assert(trie.countWordsStartingWith("app") == 1)
  }

  test("Trie should handle an empty trie") {
    val trie = new Trie()
    assert(trie.countWordsEqualTo("apple") == 0)
    assert(trie.countWordsStartingWith("app") == 0)
  }

  test("Trie should handle inserting and erasing the same word multiple times") {
    val trie = new Trie()
    trie.insert("apple")
    trie.erase("apple")
    trie.insert("apple")
    trie.insert("apple")
    trie.erase("apple")
    assert(trie.countWordsEqualTo("apple") == 1)
  }

  test("Trie should handle case-sensitive input") {
    val trie = new Trie()
    trie.insert("apple")
    trie.insert("Apple")
    assert(trie.countWordsEqualTo("apple") == 1)
    assert(trie.countWordsEqualTo("Apple") == 1)
  }

  test("Trie should handle words with numbers") {
    val trie = new Trie()
    trie.insert("apple123")
    assert(trie.countWordsEqualTo("apple123") == 1)
    assert(trie.countWordsStartingWith("apple1") == 1)
  }

  test("Trie should handle special characters") {
    val trie = new Trie()
    assertThrows[IllegalArgumentException] {
      trie.insert("apple!")
    }
  }

  test("findLongestPrefix should return the longest prefix") {
    val trie = new Trie()
    trie.insert("apple")
    trie.insert("app")
    trie.insert("apricot")
    assert(trie.findLongestPrefixes("applepie") == "apple")
    assert(trie.findLongestPrefixes("appreciate") == "app")
    assert(trie.findLongestPrefixes("banana") == "")
  }

  test("findLongestPrefix should handle an empty trie") {
    val trie = new Trie()
    assert(trie.findLongestPrefixes("apple") == "")
  }

  test("findLongestPrefix should handle prefixes that are not words") {
    val trie = new Trie()
    trie.insert("apple")
    assert(trie.findLongestPrefixes("app") == "app")
  }

  test("findLongestPrefix should handle case-sensitive input") {
    val trie = new Trie()
    trie.insert("apple")
    trie.insert("Apple")
    assert(trie.findLongestPrefixes("applepie") == "apple")
    assert(trie.findLongestPrefixes("Applepie") == "Apple")
  }

  test("findLongestPrefix should handle words with numbers") {
    val trie = new Trie()
    trie.insert("apple123")
    assert(trie.findLongestPrefixes("apple12345") == "apple123")
  }

  test("findLongestPrefix should handle special characters") {
    val trie = new Trie()
    trie.insert("apple")
    assertThrows[IllegalArgumentException] {
      trie.findLongestPrefixes("apple!") == "apple"
    }
  }


}
