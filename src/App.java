import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 600;
        int boardHeight = boardWidth;

        JFrame frame = new JFrame("Potion");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        Potion potion = new Potion(boardWidth, boardHeight);
        frame.add(potion);
        frame.pack();
        potion.requestFocus();
    }
}
