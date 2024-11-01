package UserPipeline;

import Pipeline.PipelineStep;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class AndroidProcess extends PipelineStep {
    public AndroidProcess(String name) {
        super(name);
    }

    @Override
    public void processMat(Mat input, Mat output) {
        Imgproc.cvtColor(input, output, Imgproc.COLOR_BGR2RGBA);
    }
}
