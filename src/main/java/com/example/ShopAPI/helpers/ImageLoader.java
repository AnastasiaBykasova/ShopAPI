package com.example.ShopAPI.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageLoader {

    public static String loadImageBase64(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        byte[] imageBytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static void main(String[] args) {
        String imagePath = "src/main/resources/static/1.jpg";
        try {
            String base64Image = loadImageBase64(imagePath);
            System.out.println("Изображение успешно загружено и преобразовано в base64.");
            System.out.println(base64Image);

        } catch (IOException e) {
            System.err.println("Ошибка при загрузке изображения: " + e.getMessage());
        }
    }
}
