package com.miya10kei.domain.dictionary;

import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

@Value
public class Dictionary {

  private final DictionaryName name;
  private final ImmutableList<Word> words;

  public ImmutableList<Word> search(String keyword) throws NotFoundWord {
    var words = this.words.selectWith(Word::isLike, keyword);
    if (words.isEmpty()) {
      throw new NotFoundWord(keyword);
    }
    return words;
  }
}
