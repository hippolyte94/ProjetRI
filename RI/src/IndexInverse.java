import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IndexInverse implements Serializable{

	private Map<String, ArrayList<String>> listeIndex;

	public IndexInverse(Map<String, ArrayList<String>> listeIndex) {
		this.listeIndex = listeIndex;
	}
	
	public IndexInverse() {
		this.listeIndex = new HashMap<String, ArrayList<String>>();
		// TODO Auto-generated constructor stub
	}
	
		
	
	public void serialize() throws FileNotFoundException, IOException {
		ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream("indexInverseSerialize.txt"));
		oos.writeObject(this.listeIndex);
		oos.close();
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
	
	public ArrayList<String> contientMot(String mot){
		listeIndex.get(mot);
		return listeIndex.get(mot);
		
	}
	
	
}
