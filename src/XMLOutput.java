import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

public class XMLOutput {
	XMLOutputFactory outputFactory;
	XMLStreamWriter xmlWriter;
	XMLStreamWriter xmlIdentingWriter;
	String path;

	/**
	 * Genera un oggetto XMLOutput capace di scrivere un nuovo documento XML
	 * @param path Indirizzo di destinazione del file
	 * @param isBuffer True se si tratta di un file di buffer, false altrimenti
	 */
    public XMLOutput(String path, boolean isBuffer) {
    	this.path = path;
    	try {
        	outputFactory = XMLOutputFactory.newInstance();
        	xmlWriter = outputFactory.createXMLStreamWriter(new FileOutputStream(path), "utf-8");
        	xmlIdentingWriter = new IndentingXMLStreamWriter(xmlWriter);
			xmlIdentingWriter.writeStartDocument("utf-8", "1.0");
			
			if(!isBuffer) xmlIdentingWriter.writeStartElement("routes");
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Apre un nuovo nodo di tipo Persone
     * @param num Numero di persone
     */
    public void openPersone(int num) {
		try {
			xmlIdentingWriter.writeStartElement("persone");
			xmlIdentingWriter.writeAttribute("numero", Integer.toString(num));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
    }
    
    
    public void addRoute(String team, int cost, int cities, ArrayList<Città> hops) {
    	try {
    		xmlIdentingWriter.writeStartElement("route");
    		xmlIdentingWriter.writeAttribute("team", team);
    		xmlIdentingWriter.writeAttribute("cost", Integer.toString(cost));
    		xmlIdentingWriter.writeAttribute("cities", Integer.toString(cities));
    		for(Città hop : hops) {
    			xmlIdentingWriter.writeStartElement("city");
        		xmlIdentingWriter.writeAttribute("id", Integer.toString(hop.getId()));
        		xmlIdentingWriter.writeAttribute("name", hop.getNome());
        		xmlIdentingWriter.writeEndElement();
    		}
    		
    		xmlIdentingWriter.writeEndElement();
    	} catch (XMLStreamException e) {
    		e.printStackTrace();
    	}
    }
    
    
    /**
     * Chiude i nodi rimanenti e, successivamente, il documento
     */
    public void close() {
    	try {
    		xmlIdentingWriter.writeEndElement();
			xmlIdentingWriter.writeEndDocument();
			xmlIdentingWriter.close();
			xmlWriter.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
    }
}