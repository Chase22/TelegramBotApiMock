package io.github.Chase22.telegram.telegrambotapimock.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class User {
    @JsonProperty(value = "id")
    private final Long id;

    @JsonProperty(value = "is_bot")
    private final Boolean isBot;

    @JsonProperty(value = "first_name")
    private final String firstName;

    @JsonProperty("last_name")
    private final String lastName;

    @JsonProperty("username")
    private final String username;

    @JsonProperty("language_code")
    private final String languageCode;

    public User(final Long id, final Boolean isBot, final String firstName, final String lastName, final String username, final String languageCode) {
        this.id = id;
        this.isBot = isBot;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.languageCode = languageCode;
    }

    public Long getId() {
        return id;
    }

    public Boolean getBot() {
        return isBot;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(isBot, user.isBot) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(username, user.username) &&
                Objects.equals(languageCode, user.languageCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isBot, firstName, lastName, username, languageCode);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("isBot=" + isBot)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("username='" + username + "'")
                .add("languageCode='" + languageCode + "'")
                .toString();
    }
}
