package Source;

import lombok.Getter;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

import static org.opencv.videoio.Videoio.CAP_V4L;

public class Camera implements Source {
    @Getter
    private VideoCapture camera = null;
    private Mat frame = new Mat();

    public Camera() {
        int maxCameras = 20;
        for (int i = 0; i < maxCameras; i++) {
            VideoCapture curCamera = new VideoCapture(i + CAP_V4L);
            if (curCamera.isOpened()) {
                curCamera.release();
                System.out.println("Camera " + i + " opened.");
                camera = new VideoCapture(i + CAP_V4L);
                break;
            }
            curCamera.release();
        }

        if (camera == null) {
            System.err.println("No cameras available.");
        }
    }

    public Camera(int cameraId) {
        camera = new VideoCapture(cameraId);
        frame = new Mat();
    }

    @Override
    public Mat getFrame() {
        if (camera.isOpened() && !camera.read(frame)) {
            System.err.println("Failed to read frame from camera.");
        }
        else if (!camera.isOpened()) {
            System.err.println("Camera is not opened.");
        }

        return frame;
    }

    public void freeCamera() {
        camera.release();
    }
}
