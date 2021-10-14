package com.pcfs.springbootgreeter.config;

import com.pcfs.springbootgreeter.library.Greeter;
import com.pcfs.springbootgreeter.library.GreetingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.pcfs.springbootgreeter.library.GreeterParams.*;

@Configuration
@ConditionalOnClass(Greeter.class)
@EnableConfigurationProperties(Greetings.class)
public class GreetingAutoConfiguration {

    @Autowired
    private Greetings greetings;

    @Bean
    @ConditionalOnMissingBean
    public GreetingProperties greeterConfig(){

        String userName = greetings.getUserName() == null ? System.getProperty("user.name") : greetings.getUserName();
        String morningMessage = greetings.getMorningMessage() == null ? "Good Morning" : greetings.getMorningMessage();
        String afternoonMessage = greetings.getAfternoonMessage() == null ? "Good Afternoon" : greetings.getAfternoonMessage();
        String eveningMessage = greetings.getEveningMessage() == null ? "Good Evening" : greetings.getEveningMessage();
        String nightMessage = greetings.getNightMessage() == null ? "Good Night" : greetings.getNightMessage();

        GreetingProperties greetings = new GreetingProperties();

        greetings.put(USER_NAME, userName);
        greetings.put(MORNING_MESSAGE, morningMessage);
        greetings.put(AFTERNOON_MESSAGE, afternoonMessage);
        greetings.put(EVENING_MESSAGE, eveningMessage);
        greetings.put(NIGHT_MESSAGE, nightMessage);

        return greetings;
    }

    @Bean
    @ConditionalOnMissingBean
    public Greeter greeter(GreetingProperties greetingProperties) {
        return new Greeter(greetingProperties);
    }
}
