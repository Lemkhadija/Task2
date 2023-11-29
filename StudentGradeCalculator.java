import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame {
    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JTextArea resultArea;

    public StudentGradeCalculator() {
        // Configuration de la fenêtre
        setTitle("Student Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des composants
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        JLabel[] subjectLabels = new JLabel[5];
        subjectFields = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + ":");
            subjectFields[i] = new JTextField(10);
            inputPanel.add(subjectLabels[i]);
            inputPanel.add(subjectFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Configuration du gestionnaire de disposition
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(calculateButton, BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Affichage de la fenêtre
        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Calculer la somme des notes
                int totalMarks = 0;
                for (JTextField field : subjectFields) {
                    totalMarks += Integer.parseInt(field.getText());
                }

                // Calculer la moyenne
                double averagePercentage = (double) totalMarks / subjectFields.length;

                // Assigner la note en fonction de la moyenne
                String grade;
                if (averagePercentage >= 90) {
                    grade = "A";
                } else if (averagePercentage >= 80) {
                    grade = "B";
                } else if (averagePercentage >= 70) {
                    grade = "C";
                } else if (averagePercentage >= 60) {
                    grade = "D";
                } else {
                    grade = "F";
                }

                // Afficher les résultats
                resultArea.setText("Total Marks: " + totalMarks + "\n");
                resultArea.append("Average Percentage: " + String.format("%.2f", averagePercentage) + "%\n");
                resultArea.append("Grade: " + grade + "\n");

            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input. Please enter valid marks for all subjects.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentGradeCalculator());
    }
}
