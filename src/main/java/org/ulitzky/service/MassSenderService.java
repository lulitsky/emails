package org.ulitzky.service;

import lombok.extern.slf4j.Slf4j;
import org.ulitzky.exception.InvalidInputException;
import org.ulitzky.model.Recipient;
import org.ulitzky.service.email.IEmailService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lulitzky on 21.03.18.
 */
@Slf4j
public class MassSenderService {


    private IEmailService emailService;
    private ThreadPoolExecutor executor;

    public MassSenderService(final Integer maxNumberOfThreads) {
        executor = ExecutorFactory.createExecutor(maxNumberOfThreads);
    }

    public void sendEmailToAll(final String inFileName) throws IOException {
        try {
            Files.lines(Paths.get(inFileName)).forEach(line -> sendEmail(line));
        } finally {
            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                log.error("Error in execution: {}", e.getMessage());
            }
        }
    }

    private void sendEmail(final String input) {
        try {
            Recipient recepient = new Recipient(input);
            executor.submit(new EmailSender(recepient));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public Long getSendCounter() {
        return emailService.getSendCounter();
    }

    public void setEmailService(final IEmailService emailService) {
        this.emailService = emailService;
    }

    class EmailSender implements Runnable {

        private Recipient recepient;

        EmailSender(final Recipient recepient) {
            this.recepient = recepient;
        }

        @Override
        public void run() {
            emailService.sendDefaultEmail(recepient);
        }
    }


}
