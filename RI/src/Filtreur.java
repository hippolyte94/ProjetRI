import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JSpinner.ListEditor;

import opennlp.tools.stemmer.PorterStemmer;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.WhitespaceTokenizer;

//import org.jdom2.Document;

public class Filtreur {
	
	private ArrayList<ArrayList<String>> listeTextesDesFichiersParses;
	private Crawler crawl;

	public Filtreur(Crawler crawl) {
		this.crawl=crawl;
	}
	
	public Map<String, Integer> tokenisation(ArrayList<String> listeDeMot) throws IOException{
		
		Map<String, Integer> retour=new HashMap<String, Integer>();
		SimpleTokenizer tokennizzer = SimpleTokenizer.INSTANCE;
		
		ArrayList<String> listtokenisee = new ArrayList<String>();
		String[] token;
		for (String s : listeDeMot) {
			 token = tokennizzer.tokenize(s);
				for(String s1:token) {
					if (!isStopWord(s1)) {
						if(retour.containsKey(s1)) retour.put(s1, retour.get(s1)+1);
						else retour.put(s1,1);
					}
					else {
					}
				}
		}
		return retour;
		
	}
	
	public boolean isStopWord(String mot) throws IOException{
		
		File stopwordfile = new File("../stopword");
		try {
			InputStream in = new FileInputStream(stopwordfile);
			InputStreamReader ipsr=new InputStreamReader(in);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				if (ligne.contains(mot)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {

		}
		
		return false;
		
	}
	
	public Map<String, Integer> stemming(Map<String, Integer> map,String clef){
		PorterStemmer stemmer =new PorterStemmer();
		String motstemmer;
	
		Map<String, Integer> mapStem=new HashMap<String,Integer>();
		Iterator<String> it = map.keySet().iterator();
		
		while (it.hasNext()) {
			String mot=it.next();
			motstemmer = stemmer.stem(mot);
			if(map.containsKey(motstemmer)) {
				mapStem.put(motstemmer, map.get(motstemmer)+map.get(mot));
			}
			else {
				mapStem.put(motstemmer, map.get(mot));
			}
			this.crawl.ajoutMotIndexeInverse(motstemmer,clef);
		}
		
	
		
//		for(String s:map.keySet()) {
//			motstemmer=stemmer.stem(s);
//			if(map.containsKey(motstemmer)) {
//				map.put(motstemmer, map.get(motstemmer)+map.get(s));
//				map.remove(s);
//			}
//			else {
//				map.put(motstemmer, map.get(s));
//				map.remove(s);
//			}
//			System.out.println(motstemmer);
//			
//		}
		return mapStem;
		
		
		
	}
}
