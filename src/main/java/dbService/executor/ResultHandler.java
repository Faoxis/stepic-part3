package dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sergei on 8/18/16.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
