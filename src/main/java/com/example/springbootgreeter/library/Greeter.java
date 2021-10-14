package com.example.springbootgreeter.library;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import static com.example.springbootgreeter.library.GreeterParams.*;

@AllArgsConstructor
public class Greeter {

    private GreetingProperties greetingProperties;

    public String greet() {
        return greet(LocalDateTime.now());
    }

    private String greet(LocalDateTime localDateTime) {

        String name = greetingProperties.getProperty(USER_NAME);
        int hourOfDay = localDateTime.getHour();

        if (hourOfDay >= 5 && hourOfDay < 12) {
            return String.format("Hello %s, %s", name, greetingProperties.get(MORNING_MESSAGE));
        } else if (hourOfDay >= 12 && hourOfDay < 17) {
            return String.format("Hello %s, %s", name, greetingProperties.get(AFTERNOON_MESSAGE));
        } else if (hourOfDay >= 17 && hourOfDay < 20) {
            return String.format("Hello %s, %s", name, greetingProperties.get(EVENING_MESSAGE));
        } else {
            return String.format("Hello %s, %s", name, greetingProperties.get(NIGHT_MESSAGE));
        }
    }
}
