package Source;

import lombok.Getter;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageSource implements Source {
    @Getter
    private final String path;
    private final Mat image;

    public ImageSource(String path) {
        this.path = path;
        image = Imgcodecs.imread(this.path);
    }

    @Override
    public Mat getFrame() {
        return image;
    }
}