import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class at05 extends JFrame{
    private JLabel Label;
    private JTextField Text;

    public at05(){
        
        super("Atividade 05");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        Label = new JLabel("Valor: ");
        Text = new JTextField(10);
        Text.setEditable(false);
        


        JButton incrementButton = new JButton("Incrementar");
        incrementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                incrementValue();
            }
        });

        JButton resetButton = new JButton("Zerar");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Zerar();
            }
        });

       
        add(Label);
        add(Text);
        Text.setText("0");
        add(incrementButton);
        add(resetButton);
        pack();
        setVisible(true);
        
    }

    private void incrementValue() {
        int value = Integer.parseInt(Text.getText());
            value++;
            Text.setText(Integer.toString(value));
    }

    private void Zerar() {
        Text.setText("0");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                at05 frame = new at05();
                frame.setSize(200, 100);
                frame.setLocationRelativeTo(null);
            }
        });
    }
}
