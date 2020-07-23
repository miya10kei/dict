package com.miya10kei.domain.dictionary;

import lombok.Value;

@Value
public class Word {

  private final WordId id;
  private final String name;
  private final String ruby;
  private final String description;
  private final String dictionaryName;

  public boolean isLike(String keyword) {
    var lower = keyword.toLowerCase();
    return name.toLowerCase().contains(lower) || ruby.toLowerCase().contains(lower);
  }
}
