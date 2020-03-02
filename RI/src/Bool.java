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
			if(!mot.equals("AND") && !mot.equals("OR")&& !mot.equals("NOT"))
				contiens.put(tokennizzer.tokenize(mot)[0], indexInverse.contientMot(tokennizzer.tokenize(mot)[0]));
			if(mot.equals("AND"))
				System.out.println("hello");
				contienA=true;
			if(mot.equals("NOT"))
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
			if(split.length==3) {
				for(String url:contiens.get(split[0])) {
					System.out.println("la");
					if(contiens.get(split[2]).contains(url)) {
						temp.put(url, score.get(split[0]).get(url)+score.get(split[2]).get(url));
					}
				}
			}
			Map <String, Map<String, Integer>>temp2 =new HashMap<String, Map<String, Integer>>();
			temp2.put("1",temp);
			System.out.println(temp);
			return temp2;
		}
		if(contienN) {
			Map<String,ArrayList<String>> contiens2= new HashMap<String,ArrayList<String>>();
			Map<String, ArrayList<String>> temp = indexInverse.getListeIndex();
			for(String mot:contiens.keySet()) {
				if(!temp.containsKey(mot)) {
					contiens2.put(tokennizzer.tokenize(mot)[0], indexInverse.contientMot(tokennizzer.tokenize(mot)[0]));
				}
			}
			Map<String,Map<String,Integer>> score2=new HashMap<String,Map<String,Integer>>();
			for(String mot:contiens2.keySet()) {
				score2.put(mot,new HashMap<String,Integer>());
				Map<String,Integer> temp2=index.getScore(contiens2.get(mot), mot);
				for(String fichier:temp2.keySet()) {
					score2.get(mot).put(fichier, temp2.get(fichier));
				}

			}
			System.out.println("lol je suis passe la trop lourd");
			return score2;
		}

		return score;
		
	}
}
