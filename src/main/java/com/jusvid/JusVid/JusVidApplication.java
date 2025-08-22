package com.jusvid.JusVid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.jusvid.JusVid.model")
@EnableJpaRepositories(basePackages = "com.jusvid.JusVid.repository")
@SpringBootApplication
public class JusVidApplication {

	public static void main(String[] args) {
		SpringApplication.run(JusVidApplication.class, args);
	}

}
