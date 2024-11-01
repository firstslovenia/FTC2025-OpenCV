package Ui;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Utils {
    public static Mat stripAlphaChannel(Mat mat) {
        if (mat.channels() == 4) {
            Mat rgbMat = new Mat();
            Imgproc.cvtColor(mat, rgbMat, Imgproc.COLOR_BGRA2BGR);
            return rgbMat;
        }
        else {
            return mat;
        }
    }

    public static BufferedImage matToBufferedImage(Mat mat) {
        mat = stripAlphaChannel(mat);
        if (!mat.empty()) {
            int type;
            if (mat.channels() == 1) {
                type = BufferedImage.TYPE_BYTE_GRAY;
            } else if (mat.channels() == 3) {
                type = BufferedImage.TYPE_3BYTE_BGR;
            } else if (mat.channels() == 4) {
                type = BufferedImage.TYPE_4BYTE_ABGR;
            } else {
                throw new IllegalArgumentException("Unsupported number of channels: " + mat.channels());
            }

            int bufferSize = mat.channels() * mat.cols() * mat.rows();
            byte[] b = new byte[bufferSize];
            mat.get(0, 0, b);

            BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
            final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            System.arraycopy(b, 0, targetPixels, 0, b.length);

            return image;
        }

        return null;
    }
}