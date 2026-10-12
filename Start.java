import javax.swing.*;
import java.awt.*;

public class Start {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Smart Health Tracker");
        frame.setSize(600, 500);
        frame.setLayout(new GridLayout(7, 2, 10, 10));
        frame.getContentPane().setBackground(new Color(240, 248, 255));

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font inputFont = new Font("Arial", Font.PLAIN, 16);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        JTextField nameField = new JTextField();
        nameField.setFont(inputFont);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);
        JTextField ageField = new JTextField();
        ageField.setFont(inputFont);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        String[] genderOptions = {"male", "female"};
        JComboBox<String> genderDropdown = new JComboBox<>(genderOptions);
        genderDropdown.setFont(inputFont);

        JLabel heightLabel = new JLabel("Height (in meters):");
        heightLabel.setFont(labelFont);
        JTextField heightField = new JTextField();
        heightField.setFont(inputFont);

        JLabel weightLabel = new JLabel("Weight (in kg):");
        weightLabel.setFont(labelFont);
        JTextField weightField = new JTextField();
        weightField.setFont(inputFont);

        JButton calcBtn = new JButton("Calculate BMI & BMR");
        calcBtn.setFont(new Font("Arial", Font.BOLD, 16));
        calcBtn.setBackground(new Color(100, 149, 237));
        calcBtn.setForeground(Color.WHITE);

        JTextArea output = new JTextArea();
        output.setFont(new Font("Arial", Font.PLAIN, 16));
        output.setBackground(new Color(255, 255, 240));
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        frame.add(nameLabel); frame.add(nameField);
        frame.add(ageLabel); frame.add(ageField);
        frame.add(genderLabel); frame.add(genderDropdown);
        frame.add(heightLabel); frame.add(heightField);
        frame.add(weightLabel); frame.add(weightField);
        frame.add(calcBtn); frame.add(new JLabel());
        frame.add(new JLabel("Result:")); frame.add(output);

        calcBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = (String) genderDropdown.getSelectedItem();
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());

                User user = new User(name, age, gender, height, weight);
                double bmi = new BMICalculator(height, weight).calculate();
                double bmr = new BMRCalculator(user).calculate();

                String status;
                if (bmi < 18.5) status = "Underweight";
                else if (bmi < 24.9) status = "Normal";
                else if (bmi < 29.9) status = "Overweight";
                else status = "Obese";

                FileManager.saveUser(user, bmi, bmr);

                output.setText("BMI: " + String.format("%.2f", bmi) + " (" + status + ")\n"
                        + "BMR: " + String.format("%.2f", bmr) + "\n");

            } catch (Exception ex) {
                output.setText("Invalid input. Please enter correct data.");
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
