package com.mycompany.suspectra_facematch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Utility to preprocess color images to a sketch-like, background-reduced form
 * for more robust local matching against sketches.
 *
 * Input folder:  src/main/java/com/mycompany/suspectra_facematch/colorimages
 * Output folder: src/main/java/com/mycompany/suspectra_facematch/colorimages_processed
 */
public class PreprocessColorImages {

    private static final String INPUT_DIR  = "src/main/java/com/mycompany/suspectra_facematch/colorimages";
    private static final String OUTPUT_DIR = "src/main/java/com/mycompany/suspectra_facematch/colorimages_processed";

    public static void main(String[] args) {
        File inDir  = new File(INPUT_DIR);
        File outDir = new File(OUTPUT_DIR);

        if (!inDir.exists() || !inDir.isDirectory()) {
            System.err.println("Input folder not found: " + inDir.getAbsolutePath());
            return;
        }
        if (!outDir.exists() && !outDir.mkdirs()) {
            System.err.println("Could not create output folder: " + outDir.getAbsolutePath());
            return;
        }

        File[] files = inDir.listFiles((d, name) -> {
            String n = name.toLowerCase();
            return n.endsWith(".jpg") || n.endsWith(".jpeg") || n.endsWith(".png");
        });
        if (files == null || files.length == 0) {
            System.out.println("No images found in " + inDir.getAbsolutePath());
            return;
        }

        System.out.println("Preprocessing " + files.length + " images...");

        int processed = 0;
        for (File f : files) {
            try {
                BufferedImage img = ImageIO.read(f);
                if (img == null) {
                    continue;
                }

                BufferedImage out = toSketchLike(img, 200, 200);
                File outFile = new File(outDir, f.getName());
                String format = getFormat(outFile.getName());
                ImageIO.write(out, format, outFile);
                processed++;
                System.out.println("Processed: " + f.getName());
            } catch (IOException e) {
                System.err.println("Failed: " + f.getName() + " - " + e.getMessage());
            }
        }

        System.out.println("Done. Successfully processed " + processed + " image(s).");
    }

    private static String getFormat(String name) {
        String n = name.toLowerCase();
        if (n.endsWith(".png")) return "png";
        return "jpg";
    }

    // Convert to grayscale, apply mild blur, then edge enhancement to look more sketch-like
    private static BufferedImage toSketchLike(BufferedImage src, int W, int H) {
        // Resize to standard size
        BufferedImage resized = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resized.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, W, H);
        g.drawImage(src.getScaledInstance(W, H, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        g.dispose();

        // Grayscale
        BufferedImage gray = new BufferedImage(W, H, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D gGray = gray.createGraphics();
        gGray.drawImage(resized, 0, 0, null);
        gGray.dispose();

        // Simple box blur to soften background/texture
        BufferedImage blurred = new BufferedImage(W, H, BufferedImage.TYPE_BYTE_GRAY);
        int radius = 2;
        int size = radius * 2 + 1;
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int sum = 0;
                int count = 0;
                for (int dy = -radius; dy <= radius; dy++) {
                    int yy = y + dy;
                    if (yy < 0 || yy >= H) continue;
                    for (int dx = -radius; dx <= radius; dx++) {
                        int xx = x + dx;
                        if (xx < 0 || xx >= W) continue;
                        int val = gray.getRGB(xx, yy) & 0xff;
                        sum += val;
                        count++;
                    }
                }
                int avg = count > 0 ? sum / count : (gray.getRGB(x, y) & 0xff);
                int rgb = (avg << 16) | (avg << 8) | avg;
                blurred.setRGB(x, y, rgb);
            }
        }

        // Edge enhancement: original gray - blurred => emphasize edges
        BufferedImage edge = new BufferedImage(W, H, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int gPix = gray.getRGB(x, y) & 0xff;
                int bPix = blurred.getRGB(x, y) & 0xff;
                int e = gPix - bPix + 128; // shift to mid-gray
                if (e < 0) e = 0;
                if (e > 255) e = 255;
                int rgb = (e << 16) | (e << 8) | e;
                edge.setRGB(x, y, rgb);
            }
        }

        return edge;
    }
}
