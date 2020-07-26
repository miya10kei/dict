package com.miya10kei.repository.client;

import com.miya10kei.configuration.DictConfiguration;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;
import org.sqlite.SQLiteJDBCLoader;

public class SqliteClient implements DbClient {

  @Override
  public <T> T query(Function<ResultSet, T> mapper, String sql, Object... args) throws SQLException {
    System.out.println(System.mapLibraryName("sqlitejdbc"));
    System.out.println(SQLiteJDBCLoader.class.getResource("/org/sqlite/native/Linux/x86_64/libsqlitejdbc.so"));
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
