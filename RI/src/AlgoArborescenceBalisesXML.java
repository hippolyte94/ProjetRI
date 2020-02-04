import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AlgoArborescenceBalisesXML {
	// affiche arborescence balises fichier XMl -- par défault level = 1
	private void printNode(NodeList nodeList, int level) {

		level++;
		if (nodeList != null && nodeList.getLength() > 0) {

			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println(node.getNodeName());
				}
			}
		}
	}
}
