package Pipeline;

import lombok.Getter;
import org.opencv.core.Mat;

import java.util.concurrent.Callable;

public abstract class PipelineStep {
    private final Mat output = new Mat();
    private final String name;

    public PipelineStep(String name) {
        this.name = name;
    }

    public Mat process(Mat input) {
        processMat(input, output);
        return output;
    }

    public abstract void processMat(Mat input, Mat output);

    public String getName() {
        return name;
    }
}
