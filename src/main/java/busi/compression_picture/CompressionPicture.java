package busi.compression_picture;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class CompressionPicture {

    public void compressionPicture(File inputFile, File outputFile, float quality, int targetWidth, int targetHeight) throws IOException {
        // 读取图片
        BufferedImage image = ImageIO.read(inputFile);

        // 设置压缩参数
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("JPG");
        ImageWriter writer = writers.next();
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality); // 设置压缩质量

        // 调整图片尺寸
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(image.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH), 0, 0, null);

        // 写出压缩和调整尺寸后的图片
        writer.setOutput(ImageIO.createImageOutputStream(outputFile));
        writer.write(null, new IIOImage(resizedImage, null, null), param);

        writer.dispose();
    }

    public static void main(String[] args) {
        String inputPicturePath = "C:\\Users\\86176\\Desktop\\DSC_0040.jpg";
        String outputPicturePath = "C:\\Users\\86176\\Desktop\\yys.jpg";
        CompressionPicture compressionPicture = new CompressionPicture();
        try {
            File inputFile = new File(inputPicturePath); // 原始图片文件
            File outputFile = new File(outputPicturePath); // 压缩后保存的文件
            float quality = 0.7f; // 压缩质量 (0.0 - 1.0)
            int targetWidth = 800; // 目标宽度
            int targetHeight = 600; // 目标高度
            compressionPicture.compressionPicture(inputFile, outputFile, quality, targetWidth, targetHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
