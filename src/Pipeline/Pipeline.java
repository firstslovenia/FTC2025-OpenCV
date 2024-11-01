package Pipeline;

import lombok.Getter;
import org.opencv.core.Mat;

import java.util.LinkedList;
import java.util.Vector;

public class Pipeline {
    private final Vector<Mat> processedMats = new Vector<>();
    private final Vector<PipelineStep> pipelineSteps;

    public Pipeline(Vector<PipelineStep> pipelineSteps) {
        this.pipelineSteps = pipelineSteps;
    }

    public void process(Mat frame) {
        Mat curMat = frame;

        processedMats.clear();
        processedMats.addElement(curMat);

        for (PipelineStep step : pipelineSteps) {
            curMat = step.process(curMat);
            processedMats.addElement(curMat);
        }
    }

    public Mat getProcessedMat(int i) {
        if (i < 0 || i >= processedMats.size()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + processedMats.size());
        }

        return processedMats.elementAt(i);
    }

    public PipelineStep getPipelineStep(int i) {
        if (i < 0 || i >= pipelineSteps.size()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + pipelineSteps.size());
        }

        return pipelineSteps.elementAt(i);
    }

    public int size() {
        return pipelineSteps.size();
    }
}