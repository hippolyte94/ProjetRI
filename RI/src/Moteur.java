import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

public class Moteur {

	private Index index;
	private IndexInverse indexInverse;
	private Crawler crawler;
	private String source = "../corpusRInew";
	private Filtreur filtreur;
	
	public Moteur() {
		this.index=new Index();
		this.indexInverse=new IndexInverse();
		 
		this.crawler = new Crawler(source,this);
	}
	public void ajoutIndexInverse(String clef,String mot) {
		this.indexInverse.ajouter(mot, clef);
	}
	public void start() {
		try {
			this.crawl();
			this.index.init(this.crawler.getListeTextesDesFichiersParses());
	
			System.out.println(this.indexInverse.getListeIndex());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void crawl() throws IOException, ParserConfigurationException {
		this.crawler.initParsing();

	}
}
