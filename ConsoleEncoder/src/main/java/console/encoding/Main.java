/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.encoding;

import console.encoding.encoder.Encoder;
import console.encoding.encoder.algorithm.CaesarAlgorithm;
import console.encoding.encoder.algorithm.ShiftCaesarAlgorithm;
import console.encoding.encoder.algorithm.UnicodeCaesarAlgorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final CommandLineParser clp = new DefaultParser();
        final Options optns = new Options();
        addOptions(optns);

        try (final Encoder encoder = new Encoder()) {
            CommandLine cl = clp.parse(optns, args);

            checkInOption(cl, encoder);
            checkDataOption(cl, encoder);
            checkOutOption(cl, encoder);
            checkAlgOption(cl, encoder);
            checkKeyOption(cl, encoder);
            checkModeOption(cl, encoder);
        } catch (NullPointerException | ParseException | IOException | IllegalArgumentException | ClassCastException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Press 't' to print stack trace...");

            try {
                int button = new InputStreamReader(System.in).read();
                if (button == (int) 't') {
                    ex.printStackTrace(System.out);
                }
            } catch (IOException e) {
            }
        }

        closeProgram();
    }

    /**
     * Добавление названия и описания к параметрам коммандной строки.
     *
     * @param optns контейнер для параметров
     */
    private static void addOptions(Options optns) {
        Option mode = Option.builder("m").longOpt("mode").numberOfArgs(1).valueSeparator(' ')
                .desc("команда управления, принимает два значения enc/dec, где enc = encription, dec = decription")
                .build();
        optns.addOption(mode);

        Option in = Option.builder("i").longOpt("in").numberOfArgs(1).valueSeparator(' ')
                .desc("путь к файлу с данными для обработки")
                .build();
        optns.addOption(in);

        Option out = Option.builder("o").longOpt("out").numberOfArgs(1).valueSeparator(' ')
                .desc("путь к файлу в который нужно сохранить результат (создаётся в случае отсутствия)")
                .build();
        optns.addOption(out);

        Option key = Option.builder("k").longOpt("key").numberOfArgs(1).valueSeparator(' ')
                .desc("ключ для сдвига (количество позиций на который нужно сдвинуть символ)")
                .build();
        optns.addOption(key);

        Option alg = Option.builder("a").longOpt("alg").numberOfArgs(1).valueSeparator(' ')
                .desc("тип алгоритма, принимает одно из двух значений unicode/shift")
                .build();
        optns.addOption(alg);

        Option data = Option.builder("d").longOpt("data").numberOfArgs(1).valueSeparator(' ')
                .desc("данные для шифрования")
                .build();
        optns.addOption(data);
    }

    /**
     * Проверка входных данных из файла.
     *
     * @param cl хранилище значений параметров коммандной строки
     * @param encoder кодировщик/декодировщик
     * @throws FileNotFoundException
     */
    private static void checkInOption(CommandLine cl, Encoder encoder) throws FileNotFoundException {
        if (cl.hasOption("in")) {
            final String in = cl.getOptionValue("in");
            encoder.setInputData(new File(in));
        }
    }

    /**
     * Проверка входных данных из коммандной строки.
     *
     * @param cl хранилище значений параметров коммандной строки
     * @param encoder кодировщик/декодировщик
     */
    private static void checkDataOption(CommandLine cl, Encoder encoder) {
        if (cl.hasOption("data")) {
            final String data = cl.getOptionValue("data");
            encoder.setInputData(data);
        }
    }

    /**
     * Проверка выходного потока.
     *
     * @param cl хранилище значений параметров коммандной строки
     * @param encoder кодировщик/декодировщик
     * @throws IOException
     */
    private static void checkOutOption(CommandLine cl, Encoder encoder) throws IOException {
        if (cl.hasOption("out")) {
            final String out = cl.getOptionValue("out");
            encoder.setOutputData(new File(out));
        }
    }

    /**
     * Проверка выбранного алгоритма.
     *
     * @param cl хранилище значений параметров коммандной строки
     * @param encoder кодировщик/декодировщик
     * @throws IllegalArgumentException
     */
    private static void checkAlgOption(CommandLine cl, Encoder encoder) throws IllegalArgumentException {
        if (cl.hasOption("alg")) {
            final String alg = cl.getOptionValue("alg");
            switch (alg) {
                case "shift":
                    encoder.setAlgorithm(new ShiftCaesarAlgorithm(0));
                    break;
                case "unicode":
                    encoder.setAlgorithm(new UnicodeCaesarAlgorithm(0));
                    break;
                default:
                    throw new IllegalArgumentException("unknown algorithm");
            }
        }
    }

    /**
     * Проверка ключа шифрования.
     *
     * @param cl хранилище значений параметров коммандной строки
     * @param encoder кодировщик/декодировщик
     * @throws ClassCastException
     */
    private static void checkKeyOption(CommandLine cl, Encoder encoder) throws ClassCastException {
        if (cl.hasOption("key")) {
            final int key = Integer.parseInt(cl.getOptionValue("key"));
            ((CaesarAlgorithm) encoder.getAlgorithm()).setKey(key);
        }
    }

    /**
     * Проверка режима работы приложения и выполнение, поставленнай задачи.
     *
     * @param cl хранилище значений параметров коммандной строки
     * @param encoder кодировщик/декодировщик
     * @throws IllegalArgumentException
     * @throws IOException
     */
    private static void checkModeOption(CommandLine cl, Encoder encoder) throws IllegalArgumentException, IOException {
        if (cl.hasOption("mode")) {
            final String mode = cl.getOptionValue("mode");
            switch (mode) {
                case "enc":
                    encoder.encode();
                    break;
                case "dec":
                    encoder.decode();
                    break;
                default:
                    throw new IllegalArgumentException("unknown mode");
            }
        } else {
            encoder.encode();
        }
    }

    /**
     * Завершение работы приложения.
     */
    private static void closeProgram() {
        System.out.println();
        System.out.print("Press any key to close program...");
        try {
            System.in.read();
        } catch (IOException ex) {

        }
    }
}
