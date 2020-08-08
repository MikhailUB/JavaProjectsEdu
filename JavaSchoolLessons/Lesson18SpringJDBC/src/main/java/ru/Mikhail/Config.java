package ru.Mikhail;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("ru.Mikhail")
public class Config {

    @Bean
    public DriverManagerDataSource dataSource() {
        return new DriverManagerDataSource("jdbc:sqlite:mydatabase.sqlite");
    }


}
