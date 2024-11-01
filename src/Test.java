import Pipeline.Pipeline;
import Pipeline.PipelineStep;
import Source.Camera;
import Source.ImageSource;
import Ui.PipelineWindow;
import Ui.SettingsWindow;
import UserPipeline.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.util.Vector;

public class Test {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Vector<PipelineStep> pipelineSteps = new Vector<PipelineStep>();
        pipelineSteps.addElement(new AndroidProcess("Android (ARGB)"));
        pipelineSteps.addElement(new AndroidToNormalProcess("Android To BGR"));
        pipelineSteps.addElement(new BgrToYCrCbProcess("BGR To YCrCb"));
        pipelineSteps.addElement(new YCrCbToLumProcess("YCrCb To Lum"));
        pipelineSteps.addElement(new FilterLumRedProcess("Filter Lum Red"));
        pipelineSteps.addElement(new GrayscaleToBgrProcess("Grayscale To Bgr"));
        Pipeline pipeline = new Pipeline(pipelineSteps);

        //Camera camera = new Camera();
        ImageSource camera = new ImageSource("images/sample1.png");
        PipelineWindow pipelineWindow = new PipelineWindow();
        SettingsWindow settingsWindow = new SettingsWindow(pipeline);

        while (true) {
            pipeline.process(camera.getFrame());
            System.out.println(PipelineWindow.matIdx.get() % (pipelineSteps.size() + 1));
            //pipelineWindow.setFrame(pipeline.getProcessedMat(PipelineWindow.matIdx.get() % (pipelineSteps.size() + 1)));

            Mat foreground = new Mat();
            Mat mask = pipeline.getProcessedMat(5);
            Core.bitwise_and(pipeline.getProcessedMat(0), pipeline.getProcessedMat(0), foreground, mask);
            Core.bitwise_not(mask, mask);
            Mat background = new Mat();
            Mat gray = new Mat();
            Imgproc.cvtColor(pipeline.getProcessedMat(4), gray, Imgproc.COLOR_GRAY2BGR);
            Core.bitwise_and(gray, gray, background, mask);
            Mat finalMat = new Mat();
            Core.add(background, foreground, finalMat);
            pipelineWindow.setFrame(finalMat);
        }
    }
}