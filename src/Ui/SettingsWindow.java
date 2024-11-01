package Ui;

import Pipeline.Pipeline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SettingsWindow extends JFrame {

    public SettingsWindow(Pipeline pipeline) {
        final int buttonHeight = 50;
        final int height = (pipeline.size() + 1) * buttonHeight;
        final int width = 300;

        setTitle("OpenCV Tester Settings");
        setSize(width, height);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //JLabel label = new JLabel("Click the button to change this text");
        //add(label);

        LinkedList<JButton> buttons = new LinkedList<>();
        buttons.push(new JButton("Raw Input"));
        for (int i = 0; i < pipeline.size(); i++) {
            JButton button = new JButton(pipeline.getPipelineStep(i).getName());
            button.setMinimumSize(new Dimension(width, buttonHeight));
            button.setMaximumSize(new Dimension(width, buttonHeight));
            button.setPreferredSize(new Dimension(width, buttonHeight));
            add(button, BorderLayout.EAST);
            buttons.push(button);
        }

        JButton captureButton = new JButton("Capture image");
        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("BUTTON");
            }
        });

        setResizable(false);
        setVisible(true);
    }
}