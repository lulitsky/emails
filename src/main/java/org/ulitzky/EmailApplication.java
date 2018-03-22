package org.ulitzky;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.ulitzky.service.MassSenderService;
import org.ulitzky.service.email.IEmailService;
import org.ulitzky.service.email.MockEmailService;

/**
 * Created by lulitzky on 21.03.18.
 */
@Slf4j
public class EmailApplication {

    public static void main(String[] args) throws Exception {
        CommandLine cmd = parseArgs(args);
        String inputFile = cmd.getOptionValue("input");
        Integer threadsNumber = defineNumberOfThreads(cmd);

        if (inputFile == null) {
            log.info("Usage : EmailApplication -i <fileName>");
            log.error("File name is empty");
            return;
        }

        MassSenderService massSenderService = new MassSenderService(threadsNumber);
        IEmailService mailService = new MockEmailService();
        massSenderService.setEmailService(mailService);

         massSenderService.sendEmailToAll(inputFile);

         log.info("Sent {} mails", mailService.getSendCounter());
    }

    private static Integer defineNumberOfThreads(final CommandLine cmd) {
        Integer threadsNumber = null;
        String threadsNumberStr = cmd.getOptionValue("threads", null);
        if (threadsNumberStr !=null) {
            try {
                threadsNumber = Integer.valueOf(threadsNumberStr);
            } catch (NumberFormatException e) {
                log.warn("Invalid number of threads {} specified. Using the default value", threadsNumberStr);
            }
        }
        return threadsNumber;
    }


    private static CommandLine parseArgs(final String[] args) throws  Exception {
        Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(false);
        options.addOption(input);

        Option threadsNumber = new Option("n", "threads", true, "maximal number of threads");
        threadsNumber.setRequired(false);
        options.addOption(threadsNumber);

        CommandLineParser parser = new DefaultParser();

        return parser.parse(options, args);
    }

}
