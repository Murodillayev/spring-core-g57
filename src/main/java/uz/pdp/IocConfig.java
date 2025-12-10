package uz.pdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class IocConfig {

    @Bean("postgres")
    public DataSource dataSourcePostgres() {
        return new DataSource(
                "jdbc:postgresql://localhost:5432/test",
                "postgres",
                "123",
                "org.postgresql.Driver"
        );
    }

    @Bean
    public DataSource dataSourceMySql() {
        return new DataSource(
                "jdbc:mysql://localhost:5432/test",
                "mysql",
                "1111",
                "org.mysql.Driver"
        );
    }

}

