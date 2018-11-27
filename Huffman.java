import java.util.ArrayList;
import java.util.Scanner;

public class Huffman {

	//Algorithme de création de l'arbre de huffman
	public static Arbre createHuffmanTree(Liste l)
	{
		while (!l.reste().estVide())
		{
			Arbre z = new Arbre();
			Arbre x = l.tete();
			z.setFg(x);
			l = l.supprimerOrd(x);
			Arbre y = l.tete();
			z.setFd(y);
			l = l.supprimerOrd(y);
			z.setFreq(x.getFreq() + y.getFreq());
			l = l.insererOrd(z);
		}
		return l.tete();
	}
	
	public static void main(String[] args)
	{	
		System.out.println("Entrer une chaine a encoder : ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine(); //Chaine entrée par l'utilisateur
		String strr = "";
		ArrayList<Character> characters = new ArrayList<Character>();//Tableau des lettres présentes dans la String
		/*Code et letter son générés après l'encodage, ils correspondent à la table de codage */
		/*code.get(i) et le code binaire de la lettre letter.get(i)							*/
		ArrayList<String> code = new ArrayList<String>();				
		ArrayList<String> letter = new ArrayList<String>();
		ArrayList<String> bitStr = new ArrayList<String>(); //Stockage de la chaine de bit renvoyée par la fonction récursive encodage
		char[] strChar = str.toCharArray();
		Liste l = new Liste();
		System.out.println("Chaine saisie : " + str);
		
		
		//Lettres contenues dans la string
		for (int i = 0; i < str.length(); i++)
		{
			if (!(characters.contains(strChar[i])))
				characters.add(strChar[i]);
		}
		
		int[] countOfChar = new int[characters.size()];
		for (int x = 0; x < countOfChar.length; x++) {
			countOfChar[x] = 0;
		}

		//Compte la fréquence des lettres
		for (int i = 0; i < characters.size(); i++) {
			char checker = characters.get(i);
			for (int x = 0; x < strChar.length; x++) {
				if (checker == strChar[x]) {
					countOfChar[i]++;
				}
			}
		}

		//tri des tableau de lettre et de fréquence pour une utilisation parrallèle
		for (int i = 0; i < countOfChar.length - 1; i++) {
			for (int j = 0; j < countOfChar.length - 1; j++) {
				if (countOfChar[j] < countOfChar[j + 1]) {
					int temp = countOfChar[j];
					countOfChar[j] = countOfChar[j + 1];
					countOfChar[j + 1] = temp;

					char tempChar = characters.get(j);
					characters.set(j, characters.get(j + 1));
					characters.set(j + 1, tempChar);
				}
			}
		}
		
		//Insertion des lettre est des fréquence dans une liste d'arbre
		for (int i = 0; i < characters.size(); i++)
			l = l.insererOrd(new Arbre(characters.get(i).toString(), countOfChar[i]));
		System.out.println("\nLettres et fréquence :");
		l.afficher();
	
		//Création et stockage de l'arbre de Huffman
		Arbre huffman = createHuffmanTree(l);
		//Parcours de l'arbre avec la méthode récursive encodage et génération de la table de codage
		huffman.encodage(strr, code, letter);
		
		//Affichage de la table de codage
		System.out.println("\nTable de codage :");
		System.out.println(letter);
		System.out.println(code +"\n");
	
		for (int i = 0; i < str.length(); i++)
		{
			for (int j = 0; j < letter.size(); j++)
				if (str.charAt(i) == letter.get(j).charAt(0))
					bitStr.add(code.get(j));
		}
		
		System.out.print("Encodage binaire : ");
		for (int i = 0; i < bitStr.size(); i++)
			System.out.print(bitStr.get(i));
		
		System.out.print("\nDécodage :");
		for (int i = 0; i < bitStr.size(); i++)
		{
			for (int j = 0; j < letter.size(); j++)
				if (str.charAt(i) == letter.get(j).charAt(0))
					System.out.print(letter.get(j));
		}
		
		System.out.println("\nTaille originale : " + str.length()*1 + " octets");
		System.out.println("Taille avec compression de Huffman :" + (bitStr.toString().length())/8 + " octets");
	}

}
