package org.ulitzky.service.email;

import lombok.extern.slf4j.Slf4j;
import org.ulitzky.model.Recipient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lulitzky on 21.03.18.
 */
@Slf4j
public class MockEmailService implements  IEmailService {

    private AtomicLong counter = new AtomicLong();

    @Override
    public void sendDefaultEmail(final Recipient recipient) {
        sendEmail(recipient, "");
    }

    @Override
    public void sendEmail(final Recipient recipient, final String emailText){

        try {
            TimeUnit.MILLISECONDS.sleep(500);
            log.info("Mail successfully sent to {} {}, email {}", recipient.getFirstName(), recipient.getLastName(), recipient.getEmail());
            counter.getAndIncrement();

        } catch (InterruptedException e) {
           log.error("Error in sending mail to email {}", recipient.getEmail(), e);
        }
    }

    @Override
    public Long getSendCounter() {
        return counter.get();
    }
}
