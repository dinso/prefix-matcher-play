### Summary
Prefix matching problem is solved using Trie Data Structure

### Time Complexity: 

Insertion (insert(word: String)): O(m), where 'm' is the length of the word being inserted. This is because we traverse the Trie once, up to the length of the word.

Search (countWordsEqualTo(word: String), countWordsStartingWith(prefix: String), findLongestPrefix(prefix: String)): O(m), where 'm' is the length of the word or prefix being searched. Again, we traverse the Trie up to the length of the input.

Deletion (erase(word: String)): O(m), where 'm' is the length of the word being deleted. We potentially traverse the Trie once to find and delete the word.

### Space Complexity

Worst-case: O(N * M), where 'N' is the number of words inserted, and 'M' is the maximum length of a word. This happens when there's minimal overlap between the prefixes of the words (e.g., "apple", "banana", "cherry").

Best-case: O(N), where 'N' is the number of words inserted. This occurs when the words share many common prefixes (e.g., "apple", "applesauce", "applet").


### Code Structure

```text
/main/resources/application.conf - config file
/main/scala/main - entry point for the application which is not used currently
/test/scala/TriePrefixMatcherTest - Prefix Mather Test class which is used to run, test and debug the code
/test/scala/helpers/TrieTest - Tests the Base class for Trie where core logic is present

```