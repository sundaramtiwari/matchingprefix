package com.truecaller.matchingprefix.entity;

import java.util.HashMap;

public class TrieNode {

  private final HashMap<Character, TrieNode> children;
  private boolean endOfWord = false;

  public TrieNode() {
    children = new HashMap<>();
  }

  public HashMap<Character, TrieNode> getChildren() {
    return children;
  }

  public boolean isEndOfWord() {
    return endOfWord;
  }

  public void setEndOfWord(boolean isEndOfWord) {
    this.endOfWord = isEndOfWord;
  }

}