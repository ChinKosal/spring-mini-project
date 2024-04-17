package com.mybatis.springminiproject.config;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UUIDTypeHandler extends BaseTypeHandler<UUID> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, UUID uuid, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i, uuid.toString(), jdbcType.TYPE_CODE);
    }

    @Override
    public UUID getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String uuidString = resultSet.getString(s);
        return uuidString != null ? UUID.fromString(uuidString) : null;
    }

    @Override
    public UUID getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String uuidString = resultSet.getString(i);
        return uuidString != null ? UUID.fromString(uuidString) : null;
    }

    @Override
    public UUID getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String uuidString = callableStatement.getString(i);
        return uuidString != null ? UUID.fromString(uuidString) : null;
    }
}
