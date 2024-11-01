package UserPipeline;

import Pipeline.PipelineStep;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class YCrCbToLumProcess extends PipelineStep {
    public YCrCbToLumProcess(String name) {
        super(name);
    }

    @Override
    public void processMat(Mat input, Mat output) {
        Core.extractChannel(input, output, 2);
    }
}
