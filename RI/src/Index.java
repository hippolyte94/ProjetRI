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
	
}
