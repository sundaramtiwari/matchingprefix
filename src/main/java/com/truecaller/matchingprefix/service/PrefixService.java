package com.truecaller.matchingprefix.service;

import com.truecaller.matchingprefix.entity.TrieNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * PrefixService store's the provided prefix's in a trie data structure and provides mechanism to get the longest prefix
 */
@Service
public class PrefixService {

  // Resource File to load the list of prefix in memory
  private static final String PREFIX_FILE_PATH = "src/main/resources/prefix.txt";

  // Pointer to the root node of the trie tree populated with the list of prefixe's
  private static final TrieNode ROOT = new TrieNode();

  // Populates the trie DS with data on creation of bean by Spring
  public PrefixService() {
    this.loadPrefixes();
  }

  /**
   * Method exposed by service to get the longest matching prefix for a given input string
   *
   * @param input String to match against the stored list of prefix's
   * @return Longest matching prefix or "No Match Found" if no existing prefix matches given input
   */
  public String getLongestPrefix(String input) {
    String prefix = this.find(input);
    return (prefix == null || prefix.isBlank()) ? "No Match Found" : prefix;
  }

  /**
   * This method searches the trie node for the longest existing prefix in the tree.
   *
   * @param input String to match against the stored list of prefix's
   * @return Longest matching prefix or null if no match found
   */
  public String find(String input) {
    if (input == null || input.isBlank())
      return null;

    TrieNode current = ROOT;

    StringBuilder prefix = new StringBuilder();
    String result = null;

    for (char ch : input.toCharArray()) {
      if (!current.getChildren().containsKey(ch)) {
        return result;
      }
      prefix.append(ch);
      current = current.getChildren().get(ch);

      if (current.isEndOfWord()) {
        result = prefix.toString();
      }
    }
    return result;
  }

  private void loadPrefixes() {
    try (Stream<String> lines = Files.lines(Paths.get(PREFIX_FILE_PATH), Charset.defaultCharset())) {
      lines.forEachOrdered(this::insert);
    } catch (IOException e) {
      throw new RuntimeException("Something went wrong while loading prefixes", e.getCause());
    }
  }

  /**
   * Method to insert a string to trie tree
   * @param word The string to be stored in trie tree.
   */
  private void insert(String word) {
    TrieNode current = ROOT;
    for (char w: word.toCharArray()) {
      current = current.getChildren().computeIfAbsent(w, c -> new TrieNode());
    }
    current.setEndOfWord(true);
  }
}