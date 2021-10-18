package com.rpshjha.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: rupeshkumar
 * Date: 18/10/21
 * Time: 4:28 PM
 * To change this template use File | Settings | File and Code Templates.
 */
public class GenerateQR {

    public static void generate(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException {

        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h, map);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
    }
}