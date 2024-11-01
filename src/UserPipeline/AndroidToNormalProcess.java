package UserPipeline;

import Pipeline.PipelineStep;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class AndroidToNormalProcess extends PipelineStep {
    public AndroidToNormalProcess(String name) {
        super(name);
    }

    @Override
    public void processMat(Mat input, Mat output) {
        Imgproc.cvtColor(input, output, Imgproc.COLOR_RGBA2BGR);
    }
}