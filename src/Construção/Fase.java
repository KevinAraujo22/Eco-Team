package Construção;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Entulho1> entulho1;
	private boolean emJogo;
	
 // Inicializa��o de objetos
	// montagem do plano de fundo
	public Fase() {

		setFocusable(true); // melhora de desempenho
		setDoubleBuffered(true); // melhora de desempenho

		ImageIcon referencia = new ImageIcon("res\\FO9IaSrVUAAd1y-.png");
		fundo = referencia.getImage();

		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();
		
		inicializaEntulhos();
		emJogo = true;
	}
	
	private void emJogo(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void inicializaEntulhos() {
		
		int coordenadas [] = new int [40]; // qntde
		entulho1 = new ArrayList<Entulho1>();
		// gerador rand�mico de inimigos
		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 8000+1024); 
			int y = (int) (Math.random() * 650+30); 
			entulho1.add(new Entulho1(x, y));
			
		}
		
	}
	
	//apari��o dos objetos na tela
	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;
		if(emJogo == true) {
			graficos.drawImage(fundo, 0, 0, null);
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

			List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
			
			for (int a = 0; a < entulho1.size(); a++) {
				
				Entulho1 in = entulho1.get(a);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
				
			}
		}else { // tela de fim de jogo
			ImageIcon fimJogo = new ImageIcon ("res\\fim-de-jogo-2.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			
		}

		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		// Repeti��o para tiros infinitos
		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}
		//qntd de entulhos
		for (int a = 0; a < entulho1.size(); a++) {
			Entulho1 in = entulho1.get(a);
			if (in.isVisivel()) {
				in.update();
			}else {
				entulho1.remove(a);
			}
		}
		checarColisoes();
		repaint();

	}
	//�rea de checagem de colis�es!!!!
	public void checarColisoes() {
		
		Rectangle formaJogador = player.getBounds();
		Rectangle formaEntulho1;
		Rectangle formaTiro;
		
		//Colis�o de jogador com entulho
		for (int d = 0; d < entulho1.size(); d++) {
			Entulho1 tempEntulho1 = entulho1.get(d);
			formaEntulho1 = tempEntulho1.getBounds();
				if(formaJogador.intersects(formaEntulho1)) {
					player.setVisivel(false);
					tempEntulho1.setVisivel(false);
					emJogo = false;
					
				}
		}
		//Colis�o de tiro com entulho
		List<Tiro> tiros = player.getTiros();
		for (int b = 0; b < tiros.size(); b++) {
			Tiro tempTiro = tiros.get(b);
			formaTiro = tempTiro.getBounds();
			for (int c = 0; c < entulho1.size(); c++) {
				Entulho1 tempEntulho1 = entulho1.get(c);
				formaEntulho1 = tempEntulho1.getBounds();
				if(formaTiro.intersects(formaEntulho1)) {
					tempEntulho1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
				
			}
			
		}
		
	}
	
	

	// m�todo de entrada do teclado
	private class TecladoAdapter implements KeyListener {

		@Override
		public void keyTyped(KeyEvent ke) {
			// throw new UnsupportedOperationException("Not supported yet."); //To change
			// body of generated methods, choose Tools | Templates.
		}

		@Override
		public void keyPressed(KeyEvent ke) {
			player.KeyPressed(ke);
		}

		@Override
		public void keyReleased(KeyEvent ke) {
			player.KeyReleased(ke);
		}

	}
}