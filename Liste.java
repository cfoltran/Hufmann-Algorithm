package clement.foltran;

public class Liste {
private Arbre info;
private Liste reste;
private boolean vide;

	public Liste(Arbre info, Liste reste) {
		this.info = info;
		this.reste = reste;
		this.vide = false;
	}
	
	public Liste(Arbre info) {
		this.info = info;
		this.reste = new Liste();
		this.vide = false;
	}
	
	public Liste() {
		this.vide = true;
	}
	
	public boolean estVide() {
		return vide;
	}
	
	public Liste reste() {
		return reste;
	}
	
	public Arbre tete() {
		return info;
	}
	
	public Liste prefixer(Arbre a){
		return new Liste(a, this);
	}
	
	public void afficher()
	{
		if (this.estVide()) System.out.println(" Fin Liste");
		else 
		{
			System.out.print(" "+this.tete());
			this.reste.afficher();
		}
	}
	
	public Liste insererOrd(Arbre i) {
		if (this.estVide())
			return new Liste(i);
		if (this.tete().getFreq() >= i.getFreq())
			return this.prefixer(i);
		else
			return this.reste.insererOrd(i).prefixer(this.tete());	
	}
	public Liste supprimerOrd(Arbre i)
	{
		if(this.estVide())
			return this;
		else if(i == this.tete())
			return this.reste;
		else
		return  this.reste.supprimerOrd(i).prefixer(this.tete());
	}
}
