import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import opennlp.tools.tokenize.SimpleTokenizer;

public class Bool {
	private Index index;
	private IndexInverse indexInverse;

	Bool(Index index,IndexInverse indexI) {
		this.index=index;
		this.indexInverse=indexI;
		
	}
	//return une map ayant le mot comme cle principale et ensuite le fichier et le score du mot dans le fichier (c'est a dire combien de fois il apparait dedans.
	public Map<String, Map<String, Integer>> recherche(String recherche) {
		boolean contienA=false;
		boolean contienO=false;
		boolean contienN=false;
		Map<String,ArrayList<String>> contiens= new HashMap<String,ArrayList<String>>();
		String[] split=recherche.split(" ");
		SimpleTokenizer tokennizzer = SimpleTokenizer.INSTANCE;
		for(String mot:split) {
			System.out.println(mot);
			if(mot!="AND" && mot !="OR"&& mot!="NOt")
				contiens.put(tokennizzer.tokenize(mot)[0], indexInverse.contientMot(tokennizzer.tokenize(mot)[0]));
			if(mot=="AND")
				contienA=true;
			if(mot=="NOT")
				contienN=true;
		}
		System.out.println(contiens);
		Map<String,Map<String,Integer>> score=new HashMap<String,Map<String,Integer>>();
		for(String mot:contiens.keySet()) {
			score.put(mot,new HashMap<String,Integer>());
			Map<String,Integer> temp=index.getScore(contiens.get(mot), mot);
			for(String fichier:temp.keySet()) {
				score.get(mot).put(fichier, temp.get(fichier));
			}

		}
		if(contienA) {
			Map<String,Integer> temp=new HashMap<String,Integer>();
		
		}
		
		return score;
		
	}
}
