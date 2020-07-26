package com.miya10kei.repository.client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public interface DbClient {

  <T> T query(Function<ResultSet, T> mapper, String sql, Object... args) throws SQLException;
}
