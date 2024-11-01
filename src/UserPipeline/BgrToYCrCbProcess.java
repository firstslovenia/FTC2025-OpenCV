package UserPipeline;

import Pipeline.PipelineStep;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class BgrToYCrCbProcess extends PipelineStep {
    public BgrToYCrCbProcess(String name) {
        super(name);
    }

    @Override
    public void processMat(Mat input, Mat output) {
        Imgproc.cvtColor(input, output, Imgproc.COLOR_BGR2YCrCb);
    }
}
