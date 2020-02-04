import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/*
* Le Crawler un repertoire de manière automatique.
* Dans ce cas ici present il s'agit d'un repertoire avec une architecture bien défini.--> la fonction initParsing marche uniquement pour le cas du TP
*/

public class Crawler {

	private Filtreur filtreur;
	private String source;
	private File repertoire;
	private String cheminDossier;
	private Map<String, Map<String, Map<String, Integer>>> listeTextesDesFichiersParses;
	private Moteur mot;

	public Crawler(String source,Moteur mot) {
		this.mot=mot;
		
		this.source = source;
		this.cheminDossier = source;
		this.repertoire = new File(this.source);
		this.listeTextesDesFichiersParses = new HashMap<String, Map<String, Map<String,Integer>>>();
		this.filtreur = new Filtreur(this);
	}

	public void initParsing() throws IOException, ParserConfigurationException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		String text = "";
		String beautifullText = "";
		System.out.println("indexation en cours..");

		// On parcourt tout les dossiers de this.repertoire (this.repertoire = liste des
		// noms des dossiers du dossier courant)
		for (String nomDossier : this.repertoire.list()) {
			File dossier = new File(this.cheminDossier + "/" + nomDossier);
			this.listeTextesDesFichiersParses.put(nomDossier, new HashMap<String,Map<String,Integer>>());
			// On parcourt tout les fichiers du dossier
			for (String nomFichier : dossier.list()) { // dossier.list = liste des noms des fichiers du dossier courant
				try {

					
					Document doc = (Document) docBuilder.parse(this.cheminDossier + "/" + nomDossier + "/" + nomFichier);
					Element elements = doc.getDocumentElement();
					NodeList nodeList = elements.getChildNodes();
					

					// Parsing et séparation des mots 
					
					this.listeTextesDesFichiersParses.get(nomDossier).put(nomFichier, recupereText(nodeList,text,nomDossier+" "+nomFichier));
					

					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	public Map<String, Integer> recupereText(NodeList nodeList,String text,String clef) throws IOException{
		ArrayList<String> contenuFichier = new ArrayList<String>();
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeName() == "DATE_LINE" 
					|| nodeList.item(i).getNodeName() == "DATE_TIME"
					|| nodeList.item(i).getNodeName() == "HEADER" 
					|| nodeList.item(i).getNodeName() == "BODY" 
					|| nodeList.item(i).getNodeName() == "HEADLINE"
																) {
				
				text = nodeList.item(i).getTextContent();
				String beautifullText = deleteRegExfromText(text);
				contenuFichier.add(beautifullText);
			}
			
		}
		return this.filtreur.stemming(this.filtreur.tokenisation(contenuFichier),clef);
	}

	public String deleteRegExfromText(String text) {
		String beautifullString = "";

		for (String s : text.split("[-:.;,'()`]")) {
			beautifullString += s;
		}
		return beautifullString;
	}

	public Map<String, Map<String, Map<String, Integer>>> getListeTextesDesFichiersParses() {
		return listeTextesDesFichiersParses;
	}
	
	public void ajoutMotIndexeInverse(String mot,String clef) {
		this.mot.ajoutIndexInverse(clef, mot);
	}

	
}
