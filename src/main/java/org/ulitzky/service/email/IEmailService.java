package org.ulitzky.service.email;

import org.ulitzky.model.Recipient;

/**
 * Created by lulitzky on 21.03.18.
 */
public interface IEmailService {

    void sendDefaultEmail(final Recipient recipient);

    void sendEmail(final Recipient recipient, final String emailText) throws InterruptedException;


    Long getSendCounter();
}
