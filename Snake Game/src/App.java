import java.awt.Color;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        JFrame frame=new JFrame("Snake Game");
        frame.setBounds(20, 15, 920, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GPanel panel=new GPanel();
        frame.add(panel);
        panel.setBackground(Color.BLUE);
        frame.setVisible(true);
    }
}
