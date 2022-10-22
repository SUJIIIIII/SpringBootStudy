package com.springboot.study.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// 스프링은 @Configuration이 지정된 클래스를 자바 기반의 설정 파일로 인식합니다.
@Configuration
// 해당 클래스에서 참조할 properties 파일의 위치를 지정합니다.
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {
	
	// Configuration 클래스의 메서드 레벨에만 지정이 가능하며, @Bean이 지정된 객체는 컨테이너에 의해 관리되는 빈(Bean)으로 등록됩니다.
    @Bean
    // @PropertySource에 지정된 파일(application.properties)에서 prefix에 해당하는 spring.datasource.hikari로 시작하는 설정을 모두 읽어 들여 해당 메서드에 매핑(바인딩)합니다.
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
    	// 히카리CP 객체를 생성합니다. 히카리CP는 커넥션 풀(Connection Pool) 라이브러리 중 하나이다.
        return new HikariConfig();
    }
    
	// dataSource 객체를 생성한다. 순수 JDBC는 SQL을 실행할 때마다 커넥션을 맺고 끊는 I/O 작업을 하는데, 이러한 작업은 상당한 리소스를 잡아먹는다고 한다.
	// 이러한 문제의 해결책으로 커넥션 풀이 등장. 커넥션 풀은 커넥션 객체를 생성해두고, 데이터베이스에 접근하는 사용자에게
	// 미리 생성해둔 커넥션을 제공했다가 다시 돌려받는 방법.
	// dataSource는 커넥션 풀을 지원하기 위한 인터페이스이다.
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

}