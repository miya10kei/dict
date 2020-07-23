package com.miya10kei.domain.dictionary;

import lombok.Getter;

public class NotFoundWord extends RuntimeException {

  @Getter
  private final String keyword;

  public NotFoundWord(String keyword) {
    super();
    this.keyword = keyword;
  }
}
