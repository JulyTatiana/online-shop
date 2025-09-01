package com.shop.model;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class SpecialWordCounter {

    private static final Logger LOGGER = LogManager.getLogger(SpecialWordCounter.class);

    public static void countAndWrite(String inputFilePath, String outputFilePath) {
        try {
            File inputFile = new File(inputFilePath);
            String content = FileUtils.readFileToString(inputFile, StandardCharsets.UTF_8).toLowerCase();

            String[] words = StringUtils.split(content, " \n\t\r.,!?;:()[]{}'\"");

            List<String> specialWords = Arrays.asList("in", "your", "payment", "discount", "admin");
            StringBuilder results = new StringBuilder();

            for (String word : specialWords) {
                int count = 0;
                for (String w : words) {
                    if (StringUtils.equalsIgnoreCase(w, word)) {
                        count++;
                    }
                }
                results.append("Word '").append(word).append("' appears ").append(count).append(" times.\n");
            }

            File outputFile = new File(outputFilePath);
            FileUtils.writeStringToFile(outputFile, results.toString(), StandardCharsets.UTF_8, true);

        } catch (IOException e) {
            LOGGER.error("Error reading or writing file: " + e.getMessage());
        }
    }
}
