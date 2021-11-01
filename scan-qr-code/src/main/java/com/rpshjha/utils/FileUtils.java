package com.rpshjha.utils;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by IntelliJ IDEA.
 * User: rupeshkumar
 * Date: 19/10/21
 * Time: 12:07 PM
 * To change this template use File | Settings | File and Code Templates.
 */
public class FileUtils {

    public static void moveFileToResources(String fromFile, String toFile) {
        Path source = Paths.get(fromFile);
        Path target = Paths.get(toFile);
        try {
            System.out.println("Moving file from " + fromFile + " to " + toFile);
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readXML(String filePath) throws ParserConfigurationException, IOException, SAXException {
        InputStream fXmlFile = new FileInputStream(filePath);
        if (fXmlFile == null)
            throw new IllegalArgumentException("file not found: " + filePath);

        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (fXmlFile, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }
}
