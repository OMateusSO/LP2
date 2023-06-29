import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class At09 extends JFrame {

    private List<Shape> shapes; // Lista para armazenar as formas desenhadas
    private Point startPoint; // Ponto de início do desenho
    private Point endPoint; // Ponto de fim do desenho
    private Shape currentShape; // Forma atual

    private enum ShapeType {
        LINE, RECTANGLE, OVAL
    }

    private ShapeType currentShapeType; // tipo forma selecionada

    public At09() {
        setTitle("Software de Desenho");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shapes = new ArrayList<>(); // Inicializa a lista de formas

        JPanel drawingPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(2.0f)); // Define a espessura do traço para 2 pixels
                g2d.setColor(Color.BLACK); // Define a cor de desenho como preto
                for (Shape shape : shapes) { // Desenha todas as formas armazenadas na lista
                    g2d.draw(shape);
                }
                if (currentShape != null) { // Desenha a forma atual sendo desenhada
                    g2d.draw(currentShape);
                }
            }
        };

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                endPoint = startPoint;
                updateCurrentShape();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                updateCurrentShape();
                shapes.add(currentShape); // Adiciona a forma atual à lista de formas
                currentShape = null; // Limpa a forma atual
                repaint();
            }
        });

        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                updateCurrentShape();
                repaint();
            }
        });

        JButton lineButton = new JButton("Linha");
        lineButton.addActionListener(e -> currentShapeType = ShapeType.LINE);

        JButton rectangleButton = new JButton("Retângulo");
        rectangleButton.addActionListener(e -> currentShapeType = ShapeType.RECTANGLE);

        JButton ovalButton = new JButton("Oval");
        ovalButton.addActionListener(e -> currentShapeType = ShapeType.OVAL);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(lineButton);
        buttonsPanel.add(rectangleButton);
        buttonsPanel.add(ovalButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonsPanel, BorderLayout.NORTH);
        getContentPane().add(drawingPanel, BorderLayout.CENTER);
    }

    private void updateCurrentShape() {
        switch (currentShapeType) {
            case LINE:
                currentShape = new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                break;
            case RECTANGLE:
                int x = Math.min(startPoint.x, endPoint.x);
                int y = Math.min(startPoint.y, endPoint.y);
                int width = Math.abs(startPoint.x - endPoint.x);
                int height = Math.abs(startPoint.y - endPoint.y);
                currentShape = new Rectangle2D.Double(x, y, width, height);
                break;
            case OVAL:
                x = Math.min(startPoint.x, endPoint.x);
                y = Math.min(startPoint.y, endPoint.y);
                width = Math.abs(startPoint.x - endPoint.x);
                height = Math.abs(startPoint.y - endPoint.y);
                currentShape = new Ellipse2D.Double(x, y, width, height);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            At09 software = new At09();
            software.setVisible(true);
        });
    }
}
