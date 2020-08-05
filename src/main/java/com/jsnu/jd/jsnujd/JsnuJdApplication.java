package com.jsnu.jd.jsnujd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author 魏荣轩
 */
@SpringBootApplication
public class JsnuJdApplication implements CommandLineRunner {
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;


    public static void main(String[] args) {
        SpringApplication.run(JsnuJdApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Connection conn = dataSource.getConnection();
        conn.close();
    }
}
