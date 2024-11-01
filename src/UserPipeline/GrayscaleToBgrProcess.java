package UserPipeline;

import Pipeline.PipelineStep;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class GrayscaleToBgrProcess extends PipelineStep {
    public GrayscaleToBgrProcess(String name) {
        super(name);
    }

    @Override
    public void processMat(Mat input, Mat output) {
        Imgproc.cvtColor(input, output, Imgproc.COLOR_GRAY2BGR);
    }
}
