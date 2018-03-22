package org.ulitzky.model;

import org.junit.Test;
import org.ulitzky.exception.InvalidInputException;

import static org.junit.Assert.assertEquals;

/**
 * Created by lulitzky on 22.03.18.
 */
public class RecepientTest {

    @Test
    public void testStrip() {
        assertEquals("abcd1", Recipient.strip("abcd1"));
        assertEquals("abcd2", Recipient.strip("\"abcd2"));
        assertEquals("abcd3", Recipient.strip("abcd3\""));
        assertEquals("abcd4", Recipient.strip("\"abcd4\""));

        assertEquals("abcd5", Recipient.strip("  \tabcd5\n"));

        assertEquals("abcd6", Recipient.strip("      \"     abcd6  \" "));
        assertEquals("abc d7", Recipient.strip("      \"     abc d7  \" "));
    }


    @Test
    public void testValidateEmailValid() throws InvalidInputException {
        Recipient.validateEmail("ulitsky@gmail.com");
    }


    @Test(expected = InvalidInputException.class)
    public void testValidateEmailNoAmmpersant() throws InvalidInputException {
        Recipient.validateEmail("ulitsky-gmail.com");
    }

    @Test
    public void testConstructorValid() throws InvalidInputException {
        Recipient result = new Recipient("name@gmail.com;name;last;");
        assertEquals("name", result.getFirstName());
        assertEquals("last", result.getLastName());
        assertEquals("name@gmail.com", result.getEmail());

        Recipient result1 = new Recipient("name1@gmail.com;name2;last3");
        assertEquals("name2", result1.getFirstName());
        assertEquals("last3", result1.getLastName());
        assertEquals("name1@gmail.com", result1.getEmail());
    }


    @Test(expected = InvalidInputException.class)
    public void testConstructorTooShortl() throws InvalidInputException {
        Recipient result = new Recipient("name@gmail.com;name;");
    }

    @Test(expected = InvalidInputException.class)
    public void testConstructorTooLongl() throws InvalidInputException {
        Recipient result = new Recipient("name@gmail.com;name;last;last");
    }


    @Test(expected = InvalidInputException.class)
    public void testConstructorInvalidEmail() throws InvalidInputException {
        Recipient result = new Recipient("name1-gmail.com;name2;last3");
    }
}
