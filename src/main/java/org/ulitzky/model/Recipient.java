package org.ulitzky.model;

import lombok.extern.slf4j.Slf4j;
import org.ulitzky.exception.InvalidInputException;

import java.io.Serializable;

/**
 * Created by lulitzky on 21.03.18.
 */
@Slf4j
public class Recipient implements Serializable {

    private String firstName;
    private String lastName;
    private String email;

    public Recipient (final String inLine) throws InvalidInputException {
        String input[] = inLine.split(";");
        if (input.length != 3) {
            StringBuilder errorSb = new StringBuilder();
            errorSb.append("Invalid inputLine '").append(inLine).append("', expected 3 words separated by ';'");

            log.error(errorSb.toString());
            throw new InvalidInputException(errorSb.toString());
        }
        this.email = strip(input[0]);
        validateEmail(this.email);

        this.firstName = strip(input[1]);
        this.lastName = strip(input[2]);
    }

    static void validateEmail(final String email) throws InvalidInputException{
        if (! email.contains("@")) {
            StringBuilder errorSb = new StringBuilder();
            errorSb.append("Invalid email ").append(email);
            log.error(errorSb.toString());
            throw new InvalidInputException(errorSb.toString());
        }
    }

    static String strip(final String s) {
        String trimmed = s.trim();
        StringBuilder sb = new StringBuilder(trimmed);

        if (trimmed.endsWith("\"")) {
            sb.deleteCharAt(trimmed.length()-1);
        }
        if (trimmed.startsWith("\"")) {
            sb.deleteCharAt(0);
        }
        return sb.toString().trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
