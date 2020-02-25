import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Index{

	private String clef;
	private Map<String, Map<String, Map<String, Integer>>> listeIndex;
	

	public Index(Map<String, Map<String, Map<String, Integer>>> listeIndex, String clef) {
		this.listeIndex = listeIndex;
		this.clef = clef;
	}
	
	
	public Index() {
		// TODO Auto-generated constructor stub
	}


	public String getClef() {
		return clef;
	}

	public void init(Map<String, Map<String, Map<String, Integer>>> map) {
		this.listeIndex=map;
	}


	public Map<String, Map<String, Map<String, Integer>>> getListeIndex() {
		return listeIndex;
	}
	public Integer getScore(String doc,String fichier,String mot) {
		return listeIndex.get(doc).get(fichier).get(mot);
	}


	public Integer getScore(Integer integer, String mot) {
		// TODO Auto-generated method stub
		return null;
	}


	public Map<String, Integer> getScore(ArrayList<String> liste, String mot) {
		String doc;
		String fichier;
		
		Map<String,Integer> ret=new HashMap<String,Integer>();
		for(String s:liste) {
			doc=s.split(" ")[0];
			fichier=s.split(" ")[1];
			ret.put(s, listeIndex.get(doc).get(fichier).get(mot));
		}
		return ret;
	}
	
}
