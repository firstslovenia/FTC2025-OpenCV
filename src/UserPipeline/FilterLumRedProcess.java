package UserPipeline;

import Pipeline.PipelineStep;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class FilterLumRedProcess extends PipelineStep {
    public FilterLumRedProcess(String name) {
        super(name);
    }

    @Override
    public void processMat(Mat input, Mat output) {
        double lowerBound = 0;
        double upperBound = 90;
        Core.inRange(input, new Scalar(lowerBound), new Scalar(upperBound), output);
    }
}
