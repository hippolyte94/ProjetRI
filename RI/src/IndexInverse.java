import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IndexInverse{

	private Map<String, ArrayList<String>> listeIndex;

	public IndexInverse(Map<String, ArrayList<String>> listeIndex) {
		this.listeIndex = listeIndex;
	}
	
	public IndexInverse() {
		this.listeIndex = new HashMap<String, ArrayList<String>>();
		// TODO Auto-generated constructor stub
	}

	public void ajouter(String mot , String clef) {
		if (this.listeIndex.containsKey(mot)) {
			if (!this.listeIndex.get(mot).contains(clef)) {
				this.listeIndex.get(mot).add(clef);
			}
		}
		else {
			ArrayList <String>list= new ArrayList<String>();
			list.add(clef);
			this.listeIndex.put(mot,list);
		}
	}

	public Map<String, ArrayList<String>> getListeIndex() {
		return listeIndex;
	}
	
	
}
