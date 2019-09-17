package com.vitgon.schedule.config;

import com.vitgon.schedule.service.auth.ConnectionSignUpImpl;
import com.vitgon.schedule.service.database.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;


@EnableSocial
@Configuration
@PropertySource("classpath:social-cfg.properties")
public class SocialConfig implements SocialConfigurer {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserService userService;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		FacebookConnectionFactory facebookFactory = new FacebookConnectionFactory(
				env.getProperty("facebook.app.id"),
				env.getProperty("facebook.app.secret"));
		facebookFactory.setScope(env.getProperty("facebook.scope"));
		cfConfig.addConnectionFactory(facebookFactory);
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		
		ConnectionSignUpImpl connectionSignUp = new ConnectionSignUpImpl(userService);
		repository.setConnectionSignUp(connectionSignUp);
		return repository;
	}
	
	@Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,  ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
