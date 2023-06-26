import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import javax.swing.ImageIcon;

public class at06 extends JFrame {
    JPanel pTela = new JPanel(new GridLayout(3, 3, 10, 10));
    Bloco[][] blocos = new Bloco[3][3];
    final int player1 = 1;
    final int player2 = 2;
    JLabel lInfo = new JLabel("Jogador " + player1);
    int jogadorVez = player1;

    private Clip audioClip1;
    private Clip audioClip2;
    private ImageIcon vitoriaDio;
    private ImageIcon vitoriaJotaro;
    private ImageIcon Empate;

    public at06() {
        try {
            audioClip1 = AudioSystem.getClip();
            audioClip1.open(AudioSystem.getAudioInputStream(getClass().getResource("Dio.wav")));

            audioClip2 = AudioSystem.getClip();
            audioClip2.open(AudioSystem.getAudioInputStream(getClass().getResource("Jotaro.wav")));

            vitoriaDio = new ImageIcon(getClass().getResource("Dio.jpg"));
            vitoriaJotaro = new ImageIcon(getClass().getResource("Jotaro.jpg"));
            Empate = new ImageIcon(getClass().getResource("empate.jpg"));

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        configuraJanela();
        configurarTela();
    }

    public void configurarTela() {
        add(BorderLayout.CENTER, pTela);
        add(BorderLayout.NORTH, lInfo);
        pTela.setBackground(getForeground());
        lInfo.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Bloco bloco = new Bloco();
                blocos[i][j] = bloco;
                pTela.add(bloco);
            }
        }
    }

    public void mudarPlayer() {
        if (jogadorVez == 1) {
            jogadorVez = 2;
            lInfo.setText("Jogador 2");
        } else {
            jogadorVez = 1;
            lInfo.setText("Jogador 1");
        }
    }

    public boolean textVitoria(int player) {
        // vitoria por diagonal principal
        if (blocos[0][0].q == player && blocos[1][1].q == player && blocos[2][2].q == player) {
            return true;
            // vitoria por diagonal secundaria
        } else if (blocos[0][2].q == player && blocos[1][1].q == player && blocos[2][0].q == player) {
            return true;

            // vitorias por vertical
        } else if (blocos[0][0].q == player && blocos[0][1].q == player && blocos[0][2].q == player) {
            return true;
        } else if (blocos[1][0].q == player && blocos[1][1].q == player && blocos[1][2].q == player) {
            return true;
        } else if (blocos[2][0].q == player && blocos[2][1].q == player && blocos[2][2].q == player) {
            return true;

            // vitoria por horisontal
        } else if (blocos[0][0].q == player && blocos[1][0].q == player && blocos[2][0].q == player) {
            return true;
        } else if (blocos[0][1].q == player && blocos[1][1].q == player && blocos[2][1].q == player) {
            return true;
        } else if (blocos[0][2].q == player && blocos[1][2].q == player && blocos[2][2].q == player) {
            return true;
        }
        return false;
    }

    public boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (blocos[i][j].q == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void configuraJanela() {
        setTitle("Jogo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new at06();
    }

    public class Bloco extends JButton {
        int q = 0;

        public Bloco() {
            super();
            setBackground(Color.WHITE);
            addActionListener(e -> {
                if (q == 0) {
                    if (jogadorVez == player1) {
                        setText("X");
                        playAudio(audioClip1);
                        q = player1;
                    } else {
                        setText("O");
                        playAudio(audioClip2);
                        q = player2;
                    }
                    if (textVitoria(q)) {
                        if (q == player1) {
                            JOptionPane.showMessageDialog(null, "Jogador " + q + " Venceu!", "Vitória",
                                    JOptionPane.INFORMATION_MESSAGE, vitoriaJotaro);
                            System.exit(0);
                        } else if (q == player2) {
                            JOptionPane.showMessageDialog(null, "Jogador " + q + " Venceu!", "Vitória",
                                    JOptionPane.INFORMATION_MESSAGE, vitoriaDio);
                            System.exit(0);
                        }

                    }

                    if (verificarEmpate()) {
                        JOptionPane.showMessageDialog(null, "Empate!", "Empate",
                                JOptionPane.INFORMATION_MESSAGE, Empate);
                        System.exit(0);
                    }
                    mudarPlayer();
                }
            });
        }
    }

    private void playAudio(Clip clip) {
        if (clip.isRunning())
            clip.stop();

        clip.setFramePosition(0);
        clip.start();
    }
}