package com.miya10kei.repository.client;

import com.miya10kei.configuration.DictConfiguration;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class SqliteClient {

  public <T> T query(Function<ResultSet, T> mapper, String sql, Object... args) throws SQLException {
    try (
        var connection = DriverManager.getConnection(DictConfiguration.readDbUrl());
        var ps = connection.prepareStatement(sql)
    ) {
      for (int i = 0; i < args.length; i++) {
        if (args[i] instanceof Integer) {
          ps.setInt(i + 1, (int) args[i]);
        } else {
          ps.setString(i + 1, (String) args[i]);
        }
      }
      try (var rs = ps.executeQuery()) {
        return mapper.apply(rs);
      }
    }
  }
}
