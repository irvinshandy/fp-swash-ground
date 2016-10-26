package com.irvinshandy.finalproject;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class ConfigTuner {
    public JPanel configTuner;
    private JButton depthPGainDecrease;
    private JButton depthPGainIncrease;
    private JTextField depthPGainText;
    private JButton depthDGainDecrease;
    private JButton depthDGainIncrease;
    private JTextField depthDGainText;
    private JButton depthIGainDecrease;
    private JButton depthIGainIncrease;
    private JTextField depthIGainText;
    private JButton rollPGainDecrease;
    private JButton rollPGainIncrease;
    private JTextField rollPGainText;
    private JButton rollDGainDecrease;
    private JButton rollDGainIncrease;
    private JTextField rollDGainText;
    private JButton rollIGainDecrease;
    private JButton rollIGainIncrease;
    private JTextField rollIGainText;
    private JButton pitchPGainDecrease;
    private JButton pitchPGainIncrease;
    private JTextField pitchPGainText;
    private JButton pitchDGainDecrease;
    private JButton pitchDGainIncrease;
    private JTextField pitchDGainText;
    private JButton pitchIGainDecrease;
    private JButton pitchIGainIncrease;
    private JTextField pitchIGainText;
    private JButton depthControlDecrease;
    private JButton depthControlIncrease;
    private JTextField depthControlText;
    private JButton rollControlDecrease;
    private JButton rollControlIncrease;
    private JTextField rollControlText;
    private JButton pitchControlDecrease;
    private JButton pitchControlIncrease;
    private JTextField pitchControlText;
    private JPanel controlConstantsTuner;
    private JPanel manualControlTuner;
    private JPanel depthConstantsTuner;
    private JPanel rollConstantsTuner;
    private JPanel pitchConstantsTuner;
    private JPanel manualDepthTuner;
    private JPanel manualRollTuner;
    private JPanel manualPitchTuner;
    private JPanel depthPGainTuner;
    private JPanel depthDGainTuner;
    private JPanel depthIGainTuner;
    private JPanel rollDGainTuner;
    private JPanel rollPGainTuner;
    private JPanel rollIGainTuner;
    private JPanel pitchPGainTuner;
    private JPanel pitchDGainTuner;
    private JPanel pitchIGainTuner;
    private JButton switchToManualControlButton;
    private JButton switchToAutomaticControlButton;
    private JSlider throttleSlider;
    private JPanel throttleTuner;
    private JTextField vesselRollText;
    private JTextField vesselPitchText;
    private JTextField vesselYawText;
    private JTextField vesselDepthText;
    private JTextField servoPitchText;
    private JTextField servoRollLeftText;
    private JTextField servoRollRightText;
    private JTextField servoRudderText;
    private JRadioButton automaticIndicator;
    private JRadioButton manualIndicator;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField controlCycleText;

    public ConfigTuner() {
        JFrame frame = new JFrame("ConfigTuner");
        frame.setContentPane(configTuner);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        saveButton.addActionListener(new SaveButtonClickListener());
        loadButton.addActionListener(new LoadButtonClickListener());

        depthPGainDecrease.addActionListener(new DepthPGainDecreaseButtonClickListener());
        depthPGainIncrease.addActionListener(new DepthPGainIncreaseButtonClickListener());
        depthIGainDecrease.addActionListener(new DepthIGainDecreaseButtonClickListener());
        depthIGainIncrease.addActionListener(new DepthIGainIncreaseButtonClickListener());
        depthDGainDecrease.addActionListener(new DepthDGainDecreaseButtonClickListener());
        depthDGainIncrease.addActionListener(new DepthDGainIncreaseButtonClickListener());

        rollPGainDecrease.addActionListener(new RollPGainDecreaseButtonClickListener());
        rollPGainIncrease.addActionListener(new RollPGainIncreaseButtonClickListener());
        rollIGainDecrease.addActionListener(new RollIGainDecreaseButtonClickListener());
        rollIGainIncrease.addActionListener(new RollIGainIncreaseButtonClickListener());
        rollDGainDecrease.addActionListener(new RollDGainDecreaseButtonClickListener());
        rollDGainIncrease.addActionListener(new RollDGainIncreaseButtonClickListener());

        pitchPGainDecrease.addActionListener(new PitchPGainDecreaseButtonClickListener());
        pitchPGainIncrease.addActionListener(new PitchPGainIncreaseButtonClickListener());
        pitchIGainDecrease.addActionListener(new PitchIGainDecreaseButtonClickListener());
        pitchIGainIncrease.addActionListener(new PitchIGainIncreaseButtonClickListener());
        pitchDGainDecrease.addActionListener(new PitchDGainDecreaseButtonClickListener());
        pitchDGainIncrease.addActionListener(new PitchDGainIncreaseButtonClickListener());

        depthControlDecrease.addActionListener(new ManualDepthDecreaseButtonClickListener());
        depthControlIncrease.addActionListener(new ManualDepthIncreaseButtonClickListener());
        rollControlDecrease.addActionListener(new ManualRollDecreaseButtonClickListener());
        rollControlIncrease.addActionListener(new ManualRollIncreaseButtonClickListener());
        pitchControlDecrease.addActionListener(new ManualPitchDecreaseButtonClickListener());
        pitchControlIncrease.addActionListener(new ManualPitchIncreaseButtonClickListener());

        switchToAutomaticControlButton.addActionListener(new SwitchToAutomaticButtonClickListener());
        switchToManualControlButton.addActionListener(new SwitchToManualButtonClickListener());

        throttleSlider.addChangeListener(new ThrottleListener());

        ButtonGroup controlIndicator = new ButtonGroup();
        controlIndicator.add(automaticIndicator);
        controlIndicator.add(manualIndicator);

        loadConfig();
    }

    private void switchToManualControl() {
        for (Component component : manualControlTuner.getComponents()) {
            if (component instanceof JPanel) {
                component.setEnabled(true);
                for (Component component1 : ((JPanel) component).getComponents()) {
                    component1.setEnabled(true);
                }
            }
        }
        for (Component component : controlConstantsTuner.getComponents()) {
            if (component instanceof JPanel) {
                component.setEnabled(false);
                for (Component component1 : ((JPanel) component).getComponents()) {
                    if (component1 instanceof JPanel) {
                        component1.setEnabled(false);
                        for (Component component2 : ((JPanel) component1).getComponents()) {
                            component2.setEnabled(false);
                        }
                    }
                }
            }
        }
        switchToManualControlButton.setEnabled(false);
        switchToAutomaticControlButton.setEnabled(true);
        manualIndicator.setSelected(true);
    }

    private void switchToAutomaticControl() {
        for (Component component : manualControlTuner.getComponents()) {
            if (component instanceof JPanel) {
                component.setEnabled(false);
                for (Component component1 : ((JPanel) component).getComponents()) {
                    component1.setEnabled(false);
                }
            }
        }
        for (Component component : controlConstantsTuner.getComponents()) {
            if (component instanceof JPanel) {
                component.setEnabled(true);
                for (Component component1 : ((JPanel) component).getComponents()) {
                    if (component1 instanceof JPanel) {
                        component1.setEnabled(true);
                        for (Component component2 : ((JPanel) component1).getComponents()) {
                            component2.setEnabled(true);
                        }
                    }
                }
            }
        }
        switchToManualControlButton.setEnabled(true);
        switchToAutomaticControlButton.setEnabled(false);
        automaticIndicator.setSelected(true);
    }

    private void saveConfig() {
        try {
            File file = new File("config.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("DEPTH_P_GAIN = " + String.valueOf(CommHandler.getSentPayloadValue(1)));
            bw.newLine();
            bw.write("DEPTH_I_GAIN = " + String.valueOf(CommHandler.getSentPayloadValue(2)));
            bw.newLine();
            bw.write("DEPTH_D_GAIN = " + String.valueOf(CommHandler.getSentPayloadValue(3)));
            bw.newLine();
            bw.write("ROLL_P_GAIN = " + String.valueOf(CommHandler.getSentPayloadValue(4)));
            bw.newLine();
            bw.write("ROLL_I_GAIN = " + String.valueOf(CommHandler.getSentPayloadValue(5)));
            bw.newLine();
            bw.write("ROLL_D_GAIN = " + String.valueOf(CommHandler.getSentPayloadValue(6)));
            bw.newLine();
            bw.write("PITCH_P_GAIN = " + String.valueOf(CommHandler.getSentPayloadValue(7)));
            bw.newLine();
            bw.write("PITCH_I_GAIN = " + String.valueOf(CommHandler.getSentPayloadValue(8)));
            bw.newLine();
            bw.write("PITCH_D_GAIN = " + String.valueOf(CommHandler.getSentPayloadValue(9)));
            bw.newLine();
            bw.write("THROTTLE = " + String.valueOf(CommHandler.getSentPayloadValue(10)));
            bw.newLine();
            bw.write("MANUAL_DEPTH = " + String.valueOf(CommHandler.getSentPayloadValue(11)));
            bw.newLine();
            bw.write("MANUAL_PITCH = " + String.valueOf(CommHandler.getSentPayloadValue(12)));
            bw.newLine();
            bw.write("MANUAL_ROLL = " + String.valueOf(CommHandler.getSentPayloadValue(13)));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        try {
            Scanner scanner = new Scanner(new File("config.txt"));
            int i = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] tokens = line.split(" = ");

                try {
                    CommHandler.setSentPayloadValue(i++, Integer.parseInt(tokens[1]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            updateConstantsDisplay();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateConstantsDisplay() {
        depthPGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(1)));
        depthIGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(2)));
        depthDGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(3)));
        rollPGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(4)));
        rollIGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(5)));
        rollDGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(6)));
        pitchPGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(7)));
        pitchIGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(8)));
        pitchDGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(9)));
    }

    public void displayVesselCondition() {
        vesselRollText.setText(String.valueOf(CommHandler.getReceivedPayloadValue(0)));
        vesselPitchText.setText(String.valueOf(CommHandler.getReceivedPayloadValue(1)));
        vesselYawText.setText(String.valueOf(CommHandler.getReceivedPayloadValue(2)));
        vesselDepthText.setText(String.valueOf(CommHandler.getReceivedPayloadValue(3)));
        servoPitchText.setText(String.valueOf(CommHandler.getReceivedPayloadValue(4)));
        servoRollLeftText.setText(String.valueOf(CommHandler.getReceivedPayloadValue(5)));
        servoRollRightText.setText(String.valueOf(CommHandler.getReceivedPayloadValue(6)));
        servoRudderText.setText(String.valueOf(CommHandler.getReceivedPayloadValue(7)));
        controlCycleText.setText(String.valueOf(CommHandler.getReceivedPayloadValue(8)));
    }

    private class SaveButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveConfig();
        }
    }

    private class LoadButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadConfig();
        }
    }

    private class DepthPGainDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CommHandler.getSentPayloadValue(1) > 0)
                CommHandler.setSentPayloadValue(1, CommHandler.getSentPayloadValue(1) - 1);
            depthPGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(1)));
        }
    }

    private class DepthPGainIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(1, CommHandler.getSentPayloadValue(1) + 1);
            depthPGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(1)));
        }
    }

    private class DepthIGainDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CommHandler.getSentPayloadValue(2) > 0)
                CommHandler.setSentPayloadValue(2, CommHandler.getSentPayloadValue(2) - 1);
            depthIGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(2)));
        }
    }

    private class DepthIGainIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(2, CommHandler.getSentPayloadValue(2) + 1);
            depthIGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(2)));
        }
    }

    private class DepthDGainDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CommHandler.getSentPayloadValue(3) > 0)
                CommHandler.setSentPayloadValue(3, CommHandler.getSentPayloadValue(3) - 1);
            depthDGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(3)));
        }
    }

    private class DepthDGainIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(3, CommHandler.getSentPayloadValue(3) + 1);
            depthDGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(3)));
        }
    }

    private class RollPGainDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CommHandler.getSentPayloadValue(4) > 0)
                CommHandler.setSentPayloadValue(4, CommHandler.getSentPayloadValue(4) - 1);
            rollPGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(4)));
        }
    }

    private class RollPGainIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(4, CommHandler.getSentPayloadValue(4) + 1);
            rollPGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(4)));
        }
    }

    private class RollIGainDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CommHandler.getSentPayloadValue(5) > 0)
                CommHandler.setSentPayloadValue(5, CommHandler.getSentPayloadValue(5) - 1);
            rollIGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(5)));
        }
    }

    private class RollIGainIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(5, CommHandler.getSentPayloadValue(5) + 1);
            rollIGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(5)));
        }
    }

    private class RollDGainDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CommHandler.getSentPayloadValue(6) > 0)
                CommHandler.setSentPayloadValue(6, CommHandler.getSentPayloadValue(6) - 1);
            rollDGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(6)));
        }
    }

    private class RollDGainIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(6, CommHandler.getSentPayloadValue(6) + 1);
            rollDGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(6)));
        }
    }

    private class PitchPGainDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CommHandler.getSentPayloadValue(7) > 0)
                CommHandler.setSentPayloadValue(7, CommHandler.getSentPayloadValue(7) - 1);
            pitchPGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(7)));
        }
    }

    private class PitchPGainIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(7, CommHandler.getSentPayloadValue(7) + 1);
            pitchPGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(7)));
        }
    }

    private class PitchIGainDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CommHandler.getSentPayloadValue(8) > 0)
                CommHandler.setSentPayloadValue(8, CommHandler.getSentPayloadValue(8) - 1);
            pitchIGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(8)));
        }
    }

    private class PitchIGainIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(8, CommHandler.getSentPayloadValue(8) + 1);
            pitchIGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(8)));
        }
    }

    private class PitchDGainDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CommHandler.getSentPayloadValue(9) > 0)
                CommHandler.setSentPayloadValue(9, CommHandler.getSentPayloadValue(9) - 1);
            pitchDGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(9)));
        }
    }

    private class PitchDGainIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(9, CommHandler.getSentPayloadValue(9) + 1);
            pitchDGainText.setText(String.valueOf(CommHandler.getSentPayloadValue(9)));
        }
    }

    private class ManualDepthDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(11, CommHandler.getSentPayloadValue(11) - 1);
            depthControlText.setText(String.valueOf(CommHandler.getSentPayloadValue(11) - 50));
        }
    }

    private class ManualDepthIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(11, CommHandler.getSentPayloadValue(11) + 1);
            depthControlText.setText(String.valueOf(CommHandler.getSentPayloadValue(11) - 50));
        }
    }

    private class ManualRollDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(12, CommHandler.getSentPayloadValue(12) - 1);
            rollControlText.setText(String.valueOf(CommHandler.getSentPayloadValue(12) - 50));
        }
    }

    private class ManualRollIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(12, CommHandler.getSentPayloadValue(12) + 1);
            rollControlText.setText(String.valueOf(CommHandler.getSentPayloadValue(12) - 50));
        }
    }

    private class ManualPitchDecreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(13, CommHandler.getSentPayloadValue(13) - 1);
            pitchControlText.setText(String.valueOf(CommHandler.getSentPayloadValue(13) - 50));
        }
    }

    private class ManualPitchIncreaseButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(13, CommHandler.getSentPayloadValue(13) + 1);
            pitchControlText.setText(String.valueOf(CommHandler.getSentPayloadValue(13) - 50));
        }
    }

    private class ThrottleListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                CommHandler.setSentPayloadValue(10, source.getValue());
            }
        }
    }

    private class SwitchToAutomaticButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(0, 1);
            switchToAutomaticControl();
        }
    }

    private class SwitchToManualButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CommHandler.setSentPayloadValue(0, 0);
            switchToManualControl();
        }
    }
}
