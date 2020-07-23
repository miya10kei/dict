package com.miya10kei.domain.dictionary;

import lombok.Getter;

public class NotFoundDictionary extends RuntimeException {

  @Getter
  private final String dictionaryName;

  public NotFoundDictionary(String dictionaryName) {
    super();
    this.dictionaryName = dictionaryName;
  }
}
