package org.rza.mpei.config;

import com.clickhouse.jdbc.ClickHouseDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

import static java.lang.String.format;

@Data
@Slf4j
@Component
@ConfigurationProperties("clickhouse")
public class ClickHouseProperties {

    private String host;
    private String port;
    private String database;
    private String user;
    private String password;


    //Настройка для JDBC
/*    @Bean
    public ClickHouseDataSource dataSource() throws SQLException {
        log.debug("Clickhouse connection params: host: {}, port {}, database {}", host, port, database);
        return new ClickHouseDataSource(
            format("jdbc:ch:http://%s:%s", host, port));
    }*/

    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        return new ClickHouseDataSource(format("jdbc:ch:http://%s:%s", host, port));
    }
}
