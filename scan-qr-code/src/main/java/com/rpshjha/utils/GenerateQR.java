package com.rpshjha.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;

/**
 * Created by IntelliJ IDEA.
 * User: rupeshkumar
 * Date: 18/10/21
 * Time: 4:28 PM
 * To change this template use File | Settings | File and Code Templates.
 */
public class GenerateQR {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateQR.class);

    private static void generate(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException {
        LOGGER.info("generating QR code for text " + data);
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h, map);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
    }

    public static String generate(String qrCodeText, String filename) throws IOException, WriterException {
        String path = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "resources" + separator + filename;
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        GenerateQR.generate(qrCodeText, path, charset, hashMap, 80, 80);
        System.out.println("QR Code created successfully and placed at " + path);
        return path;
    }
}
