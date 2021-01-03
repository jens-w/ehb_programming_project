package com.brielage.coursequiz.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@DataJpaTest
class DataSourceTest {
    @SuppressWarnings("FieldMayBeFinal")
    private DataSource dataSource;

    DataSourceTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("EmptyTryBlock")
    @Test
    public void getConnection() throws SQLException {
        // noinspection unused
        try (Connection connection = dataSource.getConnection()) {
        }
    }
}
