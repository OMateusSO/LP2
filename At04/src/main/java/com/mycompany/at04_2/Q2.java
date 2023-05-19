
package com.mycompany.at04_2;

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

public class Q2 {
    
    public static void main(String[] args) {
        
        JFrame janela = new JFrame("Click na tela");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Desenho areaDesenho = new Desenho();
        janela.add(areaDesenho);
        JLabel label = new JLabel("Clique na tela para desenhar");
        janela.add(label, "South");
        janela.setSize(400, 300);
        janela.setVisible(true);
    }
}

class Desenho extends JPanel {
    private List<PontoColorido> pontos = new ArrayList<>();
    private Point ultimoCirculo;

    public Desenho() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for (PontoColorido pc : pontos) {
                    if (pc.ponto.distance(e.getPoint()) <= 5) {
                        pc.cor = gerarCorAleatoria();
                        repaint();
                        return;
                    }
                }
                pontos.add(new PontoColorido(e.getPoint(), gerarCorAleatoria()));
                ultimoCirculo = e.getPoint();
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (PontoColorido pc : pontos) {
            g.setColor(pc.cor);
            g.fillOval(pc.ponto.x - 5, pc.ponto.y - 5, 10, 10);
        }
        desenhaLinhas(g);
    }

    private void desenhaLinhas(Graphics g) {
        if (pontos.size() > 1) {
            Point pontoAnterior = pontos.get(0).ponto;
            for (int i = 1; i < pontos.size(); i++) {
                Point pontoAtual = pontos.get(i).ponto;
                desenhaLinha(g, pontoAnterior, pontoAtual);
                pontoAnterior = pontoAtual;
            }
        }
    }

    private void desenhaLinha(Graphics g, Point p1, Point p2) {
        g.setColor(Color.BLUE);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    private Color gerarCorAleatoria() {
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
        return new Color(r, g, b);
    }

    private static class PontoColorido {
        Point ponto;
        Color cor;

        public PontoColorido(Point ponto, Color cor) {
            this.ponto = ponto;
            this.cor = cor;
        }
    }
}
