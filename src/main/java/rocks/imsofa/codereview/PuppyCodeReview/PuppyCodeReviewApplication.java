package rocks.imsofa.codereview.PuppyCodeReview;

import jakarta.annotation.PreDestroy;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.sql.DataSource;
import org.hsqldb.server.Server;
import org.hsqldb.server.ServerAcl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@SpringBootApplication
public class PuppyCodeReviewApplication {
    public static void main(String[] args) {
        SpringApplication.run(PuppyCodeReviewApplication.class, args);
    }
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server hsqlServer(@Value("classpath:/hsqldb.properties") Resource props) throws IOException, ServerAcl.AclFormatException {
        Server bean = new Server();
        bean.setProperties(PropertiesLoaderUtils.loadProperties(props));
        return bean;
    }

    @Bean
    @DependsOn("hsqlServer") // This is important!!
    public DataSource getDataSource(
            @Autowired DataSourceProperties dsProps) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(dsProps.getDriverClassName());
        dataSourceBuilder.url(dsProps.getUrl());
        dataSourceBuilder.username(dsProps.getUsername());
        dataSourceBuilder.password(dsProps.getPassword());
        return dataSourceBuilder.build();
    }

    @PreDestroy
    public void destroy() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9002/xdb", "sa", "");
            Statement stmt = conn.createStatement();
            stmt.execute("SHUTDOWN");
            conn.close();
        } catch (Exception ex) {
            LoggerFactory.getLogger(PuppyCodeReviewApplication.class.getName()).error(ex.getMessage(), ex);
        }
    }
}
