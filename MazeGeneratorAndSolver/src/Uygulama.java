import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Uygulama extends JFrame{
    public Panel panel;

    public Uygulama() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        panel = new Panel();
        this.add(panel);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
