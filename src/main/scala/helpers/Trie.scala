package com.dinso
package helpers

class Trie {
  private var root: TrieNode = new TrieNode()


  /**
   * Generates a Trie with the input list of data
   *
   * @param prefixes
   */
  def generate(prefixes: List[String]): Unit = {
    prefixes.foreach(prefix => {
      insert(prefix)
    })
  }

  /**
   * Inserts the string word into the trie.
   *
   * @param word
   */
  def insert(word: String): Unit = {
    var cur = root
    for (ch <- word.toCharArray) {
      val index = getIndex(ch) // Use the getIndex method to calculate the index
      if (cur.next(index) == null) {
        cur.next(index) = new TrieNode()
      }
      cur = cur.next(index)
    }
    cur.count += 1
  }

  /**
   * Helper method to calculate the index for a given character
   *
   * @param ch
   * @return
   */
  private def getIndex(ch: Char): Int = {
    if (ch >= '0' && ch <= '9') {
      ch - '0' + 52 // Adjust index for digits
    } else if (ch >= 'a' && ch <= 'z') {
      ch - 'a'
    } else if (ch >= 'A' && ch <= 'Z') {
      ch - 'A' + 26 // Adjust index for uppercase letters
    } else {
      throw new IllegalArgumentException(s"Invalid character: $ch") // Handle invalid characters
    }
  }

  /**
   * Returns the number of instances of the string word in the trie.
   *
   * @param word
   * @return
   */
  def countWordsEqualTo(word: String): Int = {
    var cur = root
    for (ch <- word.toCharArray) {
      val index = getIndex(ch)
      if (cur.next(index) == null) {
        return 0
      }
      cur = cur.next(index)
    }
    cur.count
  }

  /**
   * Finds Longest prefix string from the trie data
   * @param prefix
   * @return
   */
  def findLongestPrefixes(prefix: String): String = {
    var cur = root
    var longestPrefix = ""
    var currentPrefix = new StringBuilder()

    for (ch <- prefix.toCharArray) {
      val index = getIndex(ch)
      if (cur.next(index) == null) {
        return longestPrefix // Prefix not found, return the longest found so far
      }
      currentPrefix.append(ch) // Append the current character to the prefix
      cur = cur.next(index) // Move to the next node in the Trie
      if (cur.count > 0) {
        longestPrefix = currentPrefix.toString() // Update longestPrefix if a word ends here
      }
    }
    println(s"curr:$currentPrefix, longest:$longestPrefix")
    if(longestPrefix.nonEmpty)
      longestPrefix // Return the longest prefix found
    else currentPrefix.toString()
  }

  /**
   * Returns the number of strings in the trie that have the string prefix as a prefix.
   *
   * @param prefix
   * @return
   */
  def countWordsStartingWith(prefix: String): Int = {
    var cur = this.root
    for (ch <- prefix.toCharArray) {
      val index = getIndex(ch)
      if (cur.next(index) == null) {
        return 0 // Prefix not found, no words start with it
      }
      cur = cur.next(index) // Move to the next node
    }

    // Found the node at the end of the prefix, now count all words below it
    def countDescendants(node: TrieNode): Int = {
      if (node == null) 0
      else {
        val descendantCount = node.next.filter(_ != null).map(countDescendants).sum
        descendantCount + (if (node.count > 0) 1 else 0) // Add 1 if the current node marks a word
      }
    }
    countDescendants(cur)
  }

  /**
   * Count total number of words in root's subtree, include root itself.
   *
   * @param root
   * @return
   */
  private def countTotalWords(root: TrieNode): Int = {
    if (root == null) {
      return 0
    }
    var totalCount = 0
    for (n <- root.next) {
      totalCount += countTotalWords(n)
    }
    totalCount + root.count
  }


  /**
   * Erases the string word from the trie.
   *
   * @param word
   */
  def erase(word: String): Unit = {
    this.root = deleteByRecursion(this.root, word, 0)
  }

  private def deleteByRecursion(node: TrieNode, word: String, level: Int): TrieNode = {
    if (node == null) {
      return null
    }
    if (level == word.length) {
      node.count -= 1
      if (node.count != 0 || hasChild(node)) {
        return node
      } else {
        return null
      }
    } else {
      val idx = getIndex(word.charAt(level))
      node.next(idx) = deleteByRecursion(node.next(idx), word, level + 1)
      if (!hasChild(node) && node.count == 0 && level != 0) {
        return null
      }
    }
    node
  }

  private def hasChild(node: TrieNode): Boolean = {
    for (n <- node.next) {
      if (n != null) {
        return true
      }
    }
    false
  }
}

class TrieNode {
  var count: Int = 0
  val next: Array[TrieNode] = Array.ofDim[TrieNode](62) // Use Array.ofDim instead of Array.fill
}
