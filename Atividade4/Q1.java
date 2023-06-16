package com.mycompany.at04_1;

/**
 *
 * @author MCDL
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Q1 {
    
    public static void main(String[] args) {
        
        JFrame janela = new JFrame("Clique na tela");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Desenho areaDesenho = new Desenho();
        janela.add(areaDesenho);
        JLabel label = new JLabel("Clique na tela para desenhar");
        janela.setSize(400, 300);
        janela.setVisible(true);
    }
}

class Desenho extends JPanel {
    private List<Point> pontos = new ArrayList<>();
    private Point ultimoCirculo;

    public Desenho() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pontos.add(e.getPoint());
                ultimoCirculo = e.getPoint();
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        for (Point p : pontos) {
            g.fillOval(p.x - 5, p.y - 5, 10, 10);
        }
        desenhaLinhas(g);
    }

    private void desenhaLinhas(Graphics g) {
        if (pontos.size() > 1) {
            Point pontoAnterior = pontos.get(0);
            for (int i = 1; i < pontos.size(); i++) {
                Point pontoAtual = pontos.get(i);
                desenhaLinha(g, pontoAnterior, pontoAtual);
                pontoAnterior = pontoAtual;
            }
        }
    }

    private void desenhaLinha(Graphics g, Point p1, Point p2) {
        g.setColor(Color.BLACK);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}

