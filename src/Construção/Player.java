package Construção;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {

	//movimento personagem
	private int x, y;
	private int dx, dy;
	//personagem
	private Image imagem;
	//colis�o
	private int altura , largura;
	//Tiro da rede de captura
	private List <Tiro> tiros;
	//ativa��o da colis�o
	private boolean isVisivel;
	
	
	//coordenada spawn player
	public Player() {
		this.x=100;
		this.y=100;
		isVisivel = true;
		
		
		tiros = new ArrayList<Tiro>();
		
	}
	/*Imagem do Personagem!
	!!Easteregg!!: Voc� pode utilizar todos os personagens do incr�vel Time ECO!
	copie um dos seguintes arquivos: 
	"res\\Leo.png"
	"res\\Liza.png"
	"res\\Mari.png"
	"res\\Pedro.png"
	  L--------------------------------->	e substitua abaixo  												*/
	public void load() {
		ImageIcon referencia = new ImageIcon("res\\Liza.png");
		imagem = referencia.getImage();
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		
		
	}
	
	//movimentos do personagem
	public void update () {
		x += dx;
		y += dy;
		
	}
	
	public void tiroRede(){
		this.tiros.add(new Tiro(x+largura, y + (altura/2)));
	}
	
	public Rectangle getBounds() {
		return new Rectangle (x, y, largura, altura);
	}
	
	//movimento do personagem no teclado

	public void KeyPressed(KeyEvent tecla) {
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_SPACE) {
			tiroRede();
		}
		
		if(codigo == KeyEvent.VK_W) {
			dy=-3;
			
		}
		if(codigo == KeyEvent.VK_S) {
			dy=3;
			
		}
		if(codigo == KeyEvent.VK_A) {
			dx=-3;
			
		}
		if(codigo == KeyEvent.VK_D) {
			dx=3;
			
		}
		
	}
	
	public void KeyReleased(KeyEvent tecla) {
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_W) {
			dy=0;
			
		}
		if(codigo == KeyEvent.VK_S) {
			dy=-0;
			
		}
		if(codigo == KeyEvent.VK_A) {
			dx=-0;
			
		}
		if(codigo == KeyEvent.VK_D) {
			dx=0;
			
		}
		
	}
	
	//apenas getters e setters para utiliza��o durante a constru��o do game
	
	public boolean isVisivel() {
		return isVisivel;
	}
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImagem() {
		return imagem;
	}
	public List<Tiro> getTiros() {
		return tiros;
	}



	
	
	
}