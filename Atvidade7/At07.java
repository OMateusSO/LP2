import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class At07 implements ActionListener, MouseListener, MouseMotionListener {
    private JFrame frame;
    private JPanel panel;
    private JPanel puzzlePanel;
    private JPanel boardPanel;
    private JLabel[][] labels;
    private BufferedImage image;
    private int numRows;
    private int numCols;
    private int pieceSize;
    private int offsetX;
    private int offsetY;
    private JLabel draggingLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new At07().createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Quebra-Cabeça");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Carrega a imagem do arquivo (substitua "imagem.jpg" pelo caminho da imagem
        // que você deseja usar)
        try {
            image = ImageIO.read(new File("sonic.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Define o tamanho da imagem e a dimensão do tabuleiro
        int imageSize = Math.min(image.getWidth(), image.getHeight());
        int maxPieces = 20;
        int maxSize = 300;
        pieceSize = Math.min(imageSize / (int) Math.sqrt(maxPieces), maxSize);

        numRows = (int) Math.ceil((double) imageSize / pieceSize);
        numCols = (int) Math.ceil((double) imageSize / pieceSize);

        panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(numCols * pieceSize + 100, numRows * pieceSize + 100));

        puzzlePanel = new JPanel(new GridLayout(numRows, numCols));
        boardPanel = new JPanel(new GridLayout(5, 4));

        panel.add(puzzlePanel, BorderLayout.CENTER);
        panel.add(boardPanel, BorderLayout.SOUTH);

        labels = new JLabel[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                BufferedImage subImage = getSubImage(image, col, row);
                JLabel label = new JLabel(new ImageIcon(subImage));
                label.setBounds(col * pieceSize, row * pieceSize, pieceSize, pieceSize);
                label.addMouseListener(this);
                label.addMouseMotionListener(this);
                labels[row][col] = label;
                puzzlePanel.add(label);
            }
        }

        for (int i = 0; i < maxPieces; i++) {
            JLabel pieceLabel = new JLabel(new ImageIcon(getSubImage(image, i % numCols, i / numCols)));
            pieceLabel.setPreferredSize(new Dimension(pieceSize, pieceSize));
            pieceLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            pieceLabel.addMouseListener(this);
            pieceLabel.addMouseMotionListener(this);
            boardPanel.add(pieceLabel);
        }

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implementação do actionPerformed (se necessário)
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Implementação do mouseClicked (se necessário)
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        offsetX = e.getX();
        offsetY = e.getY();
        draggingLabel = label;
        label.getParent().setComponentZOrder(label, 0);
        label.getParent().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (draggingLabel != null) {
            int releasedRow = e.getY() / pieceSize;
            int releasedCol = e.getX() / pieceSize;

            if (puzzlePanel.contains(e.getX(), e.getY()) && isAdjacent(releasedRow, releasedCol, draggingLabel)) {
                int draggingRow = draggingLabel.getY() / pieceSize;
                int draggingCol = draggingLabel.getX() / pieceSize;

                BufferedImage tempImage = new BufferedImage(pieceSize, pieceSize, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = tempImage.createGraphics();
                draggingLabel.paint(g);
                g.dispose();

                puzzlePanel.add(draggingLabel, releasedRow * numCols + releasedCol);
                puzzlePanel.add(labels[draggingRow][draggingCol], draggingRow * numCols + draggingCol);

                puzzlePanel.revalidate();
                puzzlePanel.repaint();
            } else {
                draggingLabel.setBounds(draggingLabel.getX(), draggingLabel.getY(), pieceSize, pieceSize);
            }

            draggingLabel = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implementação do mouseEntered (se necessário)
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implementação do mouseExited (se necessário)
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggingLabel != null) {
            int x = e.getX() + draggingLabel.getX() - offsetX;
            int y = e.getY() + draggingLabel.getY() - offsetY;
            draggingLabel.setBounds(x, y, pieceSize, pieceSize);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Implementação do mouseMoved (se necessário)
    }

    private BufferedImage getSubImage(BufferedImage image, int col, int row) {
        int subImageWidth = Math.min(image.getWidth() - col * pieceSize, pieceSize);
        int subImageHeight = Math.min(image.getHeight() - row * pieceSize, pieceSize);
        return image.getSubimage(col * pieceSize, row * pieceSize, subImageWidth, subImageHeight);
    }

    private boolean isAdjacent(int row, int col, JLabel label) {
        int draggingRow = label.getY() / pieceSize;
        int draggingCol = label.getX() / pieceSize;

        return (Math.abs(row - draggingRow) == 1 && col == draggingCol) ||
                (Math.abs(col - draggingCol) == 1 && row == draggingRow);
    }
}
