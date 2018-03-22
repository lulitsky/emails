package org.ulitzky.service;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.ulitzky.TestUtils;
import org.ulitzky.service.email.MockEmailService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by lulitzky on 21.03.18.
 */
public class EmailSenderTest {

    private static MassSenderService createMassSerderService(final Integer numberOfThreads ) {
        MassSenderService massSenderService = new MassSenderService(numberOfThreads);
        massSenderService.setEmailService(new MockEmailService());

        return massSenderService;
    }

    @Test
    public void testSendSmallFileSingleThread() throws IOException {
        long size = RandomUtils.nextInt(10) + 1;

        String fileName =  TestUtils.createTestInputFile(size);

        System.out.println("Created file " + fileName);

        MassSenderService massSenderService = createMassSerderService(1);
        massSenderService.sendEmailToAll(fileName);

        assertEquals(size, massSenderService.getSendCounter().longValue());

    }


    @Test
    public void testSendSmallFileThreadsForEachMail() throws IOException {
        int size = RandomUtils.nextInt(10) + 1;

        String fileName =  TestUtils.createTestInputFile(size);

        System.out.println("Created file " + fileName);

        MassSenderService massSenderService = createMassSerderService(size);
        massSenderService.sendEmailToAll(fileName);

        assertEquals(size, massSenderService.getSendCounter().longValue());
    }


    @Test
    public void testSendSmallFileRandomNumberOfThreadsl() throws IOException {
        int size = RandomUtils.nextInt(10) + 1;

        String fileName =  TestUtils.createTestInputFile(size);

        System.out.println("Created file " + fileName);

        MassSenderService massSenderService = createMassSerderService(RandomUtils.nextInt(size-1) + 1);
        massSenderService.sendEmailToAll(fileName);

        assertEquals(size, massSenderService.getSendCounter().longValue());
    }


    @Test
    public void testSendSmallFileDefaultNumberOfThreadsl() throws IOException {
        int size = RandomUtils.nextInt(10) + 1;

        String fileName =  TestUtils.createTestInputFile(size);

        System.out.println("Created file " + fileName);

        MassSenderService massSenderService = createMassSerderService(null);
        massSenderService.sendEmailToAll(fileName);

        assertEquals(size, massSenderService.getSendCounter().longValue());
    }
}
