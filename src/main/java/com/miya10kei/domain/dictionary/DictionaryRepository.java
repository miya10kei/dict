package com.miya10kei.domain.dictionary;

import org.eclipse.collections.api.list.ImmutableList;

public interface DictionaryRepository {

  Dictionary fetchDictionary(DictionaryName name);

  ImmutableList<Dictionary> fetchDictionaries();
}
