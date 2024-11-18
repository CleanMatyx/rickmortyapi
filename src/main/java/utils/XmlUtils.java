package utils;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import main.Constants;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import data.Character;

/**
 * Clase que contiene los métodos para convertir un objeto JSON a XML
 */
public class XmlUtils {

	/**
	 * Función que convierte un episodio en formato JSON a XML
	 * 
	 * @param episodio Número de episodio
	 */
    @SuppressWarnings("unchecked")
	public static void convertToXml(int episodio) {
        try {
            JSONObject episodioJson = JsonUtils.getApiJsonEpisode(Constants.EPISODES_URL, episodio);

            StringBuilder xml = new StringBuilder("<episode>\n");
            xml.append("\t<id>").append(episodioJson.get("id")).append("</id>\n");
            xml.append("\t<name>").append(episodioJson.get("name")).append("</name>\n");
            xml.append("\t<air_date>").append(episodioJson.get("air_date")).append("</air_date>\n");
            xml.append("\t<episode>").append(episodioJson.get("episode")).append("</episode>\n");
            xml.append("\t<characters>\n");

            List<String> charactersList = (List<String>) episodioJson.get("characters");
            for (String character : charactersList) {
                xml.append("\t\t<character>").append(character).append("</character>\n");
            }

            xml.append("\t</characters>\n");
            xml.append("\t<url>").append(episodioJson.get("url")).append("</url>\n");
            xml.append("\t<created>").append(episodioJson.get("created")).append("</created>\n");
            xml.append("</episode>");

            File directory = new File(Constants.DATA_FOLDER);
            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("Folder created: " + Constants.DATA_FOLDER);
            }

            try (FileWriter fileWriter = new FileWriter(new File(directory, Constants.RESULTS_FILE))) {
                fileWriter.write(xml.toString());
                System.out.println("File saved successfully: " + Constants.RESULTS_FILE);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Función que lee un archivo XML
     */
    public static void readXml() {
        File file = new File(Constants.DATA_FOLDER, Constants.RESULTS_FILE);
        if (!file.exists()) {
            System.out.println("The file does not exist: " + Constants.RESULTS_FILE);
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getTagName().equals("characters")) {
                        processCharacters(element);
                    } else {
                        System.out.println(element.getTagName() + ": " + element.getTextContent());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Función que procesa los personajes de un episodio
     * @param charactersElement Elemento XML de los personajes
     */
    private static void processCharacters(Element charactersElement) throws Exception {
        System.out.println("Characters: ");
        NodeList characters = charactersElement.getChildNodes();
        for (int j = 0; j < characters.getLength(); j++) {
            Node characterNode = characters.item(j);
            if (characterNode.getNodeType() == Node.ELEMENT_NODE) {
                Element character = (Element) characterNode;
                String characterUrl = character.getTextContent();
                Character characterObj = JsonUtils.getApiJson(characterUrl, Character.class);
                System.out.println("\t" + characterObj.getName());
            }
        }
    }
}