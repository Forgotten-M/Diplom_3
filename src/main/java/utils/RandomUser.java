package utils;

import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import model.User;

public class RandomUser {
    Faker faker = new Faker();

    // генерация валидного пользователя
    public User getUser() {
        return User.builder()
                .email(String.format("%s@yandex.ru", faker.name().username()))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .name(faker.name().fullName())
                .build();
    }
    // генерация пользователя с паролем менее 6 символов
    public String getInvalidPassword() {
        return RandomStringUtils.randomAlphanumeric(5);
    }
}