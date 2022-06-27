package com.fochosa.exam.mutant;

import java.net.InetAddress;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.core.env.Environment;

import com.fochosa.exam.mutant.config.DefaultProfileUtil;

@SpringBootApplication
public class ExamenMutanteApplication {

	private static final Logger log = LoggerFactory.getLogger(ExamenMutanteApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ExamenMutanteApplication.class);
		DefaultProfileUtil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();

		String hostAddress = getHostAddress();
		String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).orElse("http");

		// @formatter:off
		log.info("\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running!\n"
						+ "Access URLs:\n\t"
						+ "Local: \t\t{}://localhost:{}\n\t"
						+ "External: \t{}://{}:{}\n\t"
						+ "Profile(s): \t{}\n"
				+ "----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol, env.getProperty("server.port"),
				protocol, hostAddress, env.getProperty("server.port"),
				env.getActiveProfiles());
		// @formatter:on
	}
	private static String getHostAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			log.warn("The host name could not be determined, using 'localhost' as fallback");
			return "localhost";
		}
	}
}
