package Ui;

import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class PipelineWindow extends JFrame {
    private final JLabel imageLabel;
    public static AtomicInteger matIdx = new AtomicInteger(0);

    public PipelineWindow() {
        setTitle("OpenCV Tester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLayout(new BorderLayout(10, 10));

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        JLabel label = new JLabel("Click the button to change this text");
        label.setBounds(30, 20, 240, 30);

        JButton nextButton = new JButton("Capture image");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("BUTTON");
                matIdx.addAndGet(1);
            }
        });

        add(nextButton, BorderLayout.EAST);

        setVisible(true);
    }

    public void setFrame(Mat mat) {
        if (mat.empty()) return;

        ImageIcon imageIcon = new ImageIcon(Utils.matToBufferedImage(mat));
        imageLabel.setIcon(imageIcon);
    }
}
