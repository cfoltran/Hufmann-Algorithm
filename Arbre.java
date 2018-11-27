package clement.foltran;
import java.util.*;

public class Arbre {
private int freq;
private String letter;
private Arbre fg;
private Arbre fd;

	public Arbre(String letter, int freq, Arbre fg, Arbre fd) {
		this.letter = letter;
		this.freq = freq;
		this.fg = fg;
		this.fd = fd;
	}
	
	public Arbre(String letter, int freq) {
		this.letter = letter;
		this.freq = freq;	
		this.fg = new Arbre();
		this.fd = new Arbre();
	}
	
	public Arbre() {
		this.letter = "_";
		this.freq = 70000000;
	}
	
	public boolean estVide(){
		return this.letter == "_" && this.freq == 70000000;
	}
	
	public boolean estFeuille() {
		if (this.estVide()) return false;
		else return this.fd.estVide() && this.fg.estVide();
	}
	
	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public Arbre getFg() {
		return fg;
	}

	public void setFg(Arbre fg) {
		this.fg = fg;
	}

	public Arbre getFd() {
		return fd;
	}

	public void setFd(Arbre fd) {
		this.fd = fd;
	}
 
	public String toString() {
		return "- " +letter +" "+ freq;
	}
	
	public void afficher_infixe() {
		if (!this.estVide()){
			this.getFg().afficher_infixe();
			System.out.println(this.getFreq() + " " + this.getLetter());
			this.getFd().afficher_infixe();			
		}
	}
	
	
	//méthode récursive permettant le parcours de l'arbre et l'obtention de la table de codage
	//La méthode retourner dans deux arrayList distinctes le code à l'indice i correspondant a la lettre à l'indice i
	public void encodage(String str, ArrayList<String> code, ArrayList<String> letter) {
		if (!this.estVide())
		{	
			if (this.estFeuille())
			{
				str += this.getLetter();
				letter.add(this.getLetter());
				code.add(str.substring(0, str.length()-1));
			}
			else
			{
				this.getFg().encodage(str+="0", code, letter);
				str = str.substring(0, str.length()-1);
				this.getFd().encodage(str+="1", code, letter);
				str = str.substring(0, str.length()-1);
			}
		}
	}
}
