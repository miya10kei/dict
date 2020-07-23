package com.miya10kei.repository.dictionary;

import com.miya10kei.domain.dictionary.Dictionary;
import com.miya10kei.domain.dictionary.DictionaryName;
import com.miya10kei.domain.dictionary.DictionaryRepository;
import com.miya10kei.domain.dictionary.NotFoundDictionary;
import com.miya10kei.domain.dictionary.Word;
import com.miya10kei.repository.client.SqliteClient;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.eclipse.collections.api.list.ImmutableList;

@RequiredArgsConstructor
public class DictionaryRepositoryImpl implements DictionaryRepository {

  private final SqliteClient client;

  @Override
  public Dictionary fetchDictionary(DictionaryName dictionaryName) {
    var sql = "SELECT id, name, ruby, description, dictionary FROM word WHERE dictionary = ?";
    try {
      var words = client.query(DictionaryMapper::toWords, sql, dictionaryName.getValue());
      if (words.isEmpty()) {
        throw new NotFoundDictionary(dictionaryName.getValue());
      }
      return new Dictionary(dictionaryName, words.toImmutable());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  public ImmutableList<Dictionary> fetchDictionaries() {
    var sql = "SELECT id, name, ruby, description, dictionary FROM word";
    try {
      var words = client.query(DictionaryMapper::toWords, sql);
      return words.groupBy(Word::getDictionaryName).keyMultiValuePairsView()
          .collect(it -> new Dictionary(new DictionaryName(it.getOne()), it.getTwo().toList().toImmutable()))
          .toList()
          .toImmutable();
    } catch (
        SQLException e) {
      throw new RuntimeException(e);
    }
  }
}