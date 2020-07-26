package com.miya10kei.repository.dictionary;

import com.miya10kei.domain.dictionary.Word;
import com.miya10kei.domain.dictionary.WordId;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

public class DictionaryMapper {

  static ImmutableList<Word> toWords(ResultSet rs) {
    var words = Lists.mutable.<Word>empty();
    try {
      while (rs.next()) {
        words.add(new Word(
            new WordId(rs.getLong("id")),
            rs.getString("name"),
            rs.getString("ruby"),
            rs.getString("description"),
            rs.getString("dictionary")));
      }
      return words.toImmutable();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
