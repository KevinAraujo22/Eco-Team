package Start;

import javax.swing.JFrame;

import Construção.Fase;


@SuppressWarnings("serial")
public class Inicializacao extends JFrame {
	//Modificacao da janela do game
	public Inicializacao() {
		
		add (new Fase());
		
		setTitle("Eco-Team");
		setSize(1024,710);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		new Inicializacao();
		
		
	}

}
