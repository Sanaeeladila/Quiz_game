package quiz_game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton;
    private ButtonGroup buttonGroup;
    private Object[][] questions = {{"What is the capital of France?", "Paris", "London", "Rome", 0},
                                    {"What is the largest planet in our solar system?", "Earth", "Jupiter", "Mars", 1},
                                    {"What is the tallest mountain in the world?","Mount McKinley","Mount Fuji","Mount Everest", 2},
                                    {"Who wrote \"Harry Potter\"?","J.K. Rowling","George R.R. Martin","Stephen King", 0}};
    private int currentQuestionIndex = 0;
    private int score = 0;

    public Main() {
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        questionLabel = new JLabel((String) questions[currentQuestionIndex][0]);
        mainPanel.add(questionLabel);

        options = new JRadioButton[3];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 3; i++) {
            options[i] = new JRadioButton((String) questions[currentQuestionIndex][i + 1]);
            buttonGroup.add(options[i]);
            mainPanel.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        mainPanel.add(nextButton);

        add(mainPanel);

        setVisible(true);
    }

    private void checkAnswer() {
        if (options[(int) questions[currentQuestionIndex][4]].isSelected()) {
            score++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText((String) questions[currentQuestionIndex][0]);
            for (int i = 0; i < 3; i++) {
                options[i].setText((String) questions[currentQuestionIndex][i + 1]);
                options[i].setSelected(false);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Quiz completed!\nYour score: " + score + "/" + questions.length);
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
