package com.example.ShopAPI.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.Base64;
import javax.imageio.ImageIO;

public class ByteArrayGenerator {
    public static byte[] generateImageByteArray(int width, int height, Color color) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", outputStream);
            return outputStream.toByteArray();
        }
    }

    public static String generateBase64Image(int width, int height, Color color) throws IOException {
        byte[] imageBytes = generateImageByteArray(width, height, color);
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static void main(String[] args) throws IOException {
        int width = 4;
        int height = 6;
        Color color = Color.BLACK;
        String base64Image = generateBase64Image(width, height, color);
        System.out.println(base64Image);

    }
}
