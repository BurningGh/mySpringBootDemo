package com.example.javacv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lz
 * @date 2019/10/9
 */
public class Demo {
   private BlockingQueue<BufferedImage> queue = new LinkedBlockingQueue<>();

    public BlockingQueue<BufferedImage> getQueue() {
        return queue;
    }

    public void getBatchedImages(String path) {
        File folder = new File(path);
        File[] listFiles = folder.listFiles();
        assert listFiles != null;
        for (int i = 0, listFilesLength = listFiles.length; i < listFilesLength; i++) {
            File filePath = listFiles[i];
            try {
                String full_path = filePath.getPath();
                queue.add(Objects.requireNonNull(readImg(full_path)));
                System.out.println(i + ": " + full_path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedImage readImg(String path) throws IOException {
        File img = new File(path);
        if (!img.exists()) {
            return null;
        }
        return ImageIO.read(img);
    }
}
