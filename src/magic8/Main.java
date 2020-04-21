package magic8;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Main {
    private static final String[] responses = {
        "It is certain",
        "It is decidedly so",
        "Without a doubt",
        "Yes, definitely",
        "You may rely on it",
        "As I see it, yes",
        "Most likely",
        "Outlook good",
        "Yes",
        "Signs point to yes",
        "Reply hazy, try again",
        "Ask again later",
        "Better not tell you now",
        "Cannot predict now",
        "Concentrate and ask again",
        "Don't count on it",
        "My reply is no",
        "My sources say no",
        "Outlook not so good",
        "Very doubtful"
    };

    public static int width = 500;
    public static int height = 500;

    private static final String placeholder = "Question";
    private static boolean placeholderOn = true;

    private static final Random r = new Random();
    private static final JFrame frame = new JFrame();
    private static final JTextField question = new JTextField(placeholder);
    private static final JButton submit = new JButton("Submit");
    private static final JLabel answer = new JLabel();

    public static void main (String[] args) {
        question.setBounds((width - 200) / 2, (height - 20) / 2 - 30, 200, 20);
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.addFocusListener(new FocusListener() {
            @Override // I don't really know what this does
            public void focusGained(FocusEvent e) {
                questionDeactivatePlaceholder();
            }

            @Override
            public void focusLost(FocusEvent e) {
                questionActivatePlaceholder();
            }
        });
        question.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) answer();
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });

        submit.setBounds((width - 80) / 2, (height - 20) / 2, 80, 20);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer();
            }
        });

        answer.setBounds(0, (height - 20) / 2 + 30, width, 20);
        answer.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(question);
        frame.add(submit);
        frame.add(answer);

        frame.setSize(width, height);
        frame.getContentPane().setBackground(new Color(128, 0, 255));
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public static void answer () {
        question.setText("");
        question.setFocusable(false);
        question.setFocusable(true);
        if (!placeholderOn) questionActivatePlaceholder();
        answer.setText(responses[r.nextInt(20)]);
    }
    public static void questionActivatePlaceholder () {
        if (question.getText().isBlank()) {
            question.setText(placeholder);
            question.setForeground(new Color(128, 128, 128));
            placeholderOn = true;
        }
        else placeholderOn = false;
        System.out.println("activate " + placeholderOn);
    }
    public static void questionDeactivatePlaceholder () {
        if (placeholderOn) {
            question.setText("");
            question.setForeground(new Color(0, 0, 0));
        }
        System.out.println("deactivate " + placeholderOn);
    }
}
