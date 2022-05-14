import javax.swing.*;
import java.util.Observer;

public class ClockGui extends JFrame implements Observer {
    private JLabel label = new JLabel("Clock Counter");

    public ClockGui() {
        add(label);
        pack();
        setVisible(true);
        setAlwaysOnTop(true);
    }

    public void updateTick(int tick) {
        label.setText("Tick " + tick);
    }
}
