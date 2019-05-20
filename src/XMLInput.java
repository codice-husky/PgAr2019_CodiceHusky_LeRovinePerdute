import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

public class XMLInput {
	private InputStream xmlInputStream;
	private XMLInputFactory2 xmlInputFactory;
    private XMLStreamReader2 xmlStreamReader;
    int lastEventType;
    
    /**
     * Crea un oggetto di tipo XMLInput utilizzabile per leggere dati da file XML.
     * Usa StAX
     * @param xmlFileName Indirizzo del file da aprire
     */
    public XMLInput(String xmlFileName) {
    	try {
    		xmlInputStream = new FileInputStream(xmlFileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        xmlInputFactory = (XMLInputFactory2)XMLInputFactory.newInstance();
		try {
			xmlStreamReader = (XMLStreamReader2) xmlInputFactory.createXMLStreamReader(xmlInputStream);
			lastEventType = xmlStreamReader.getEventType();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
    }
    
    
    /*Il concetto è uguale per tutte le varianti. Non è possibile creare un metodo richiamabile causa mini modifiche specifiche per ogni caso.
     * Il parser legge elemento per elemento (\n inclusi) e, in base all'elemento letto (switch), esegue una deerminata operazione.
     * Sembra brutto e lo è, ma non ho trovato un metodo più snello per gestirlo. Funziona.
     */
    /**
     * Legge la persona successiva all'interno del file
     * @return Oggetto Persona
     */
    public Città readNextCittà() {
    	int idCittà = 0;
    	String nome = null, coordX = null, coordY = null, coordH = null;
    	LinkedList<Integer> link = new LinkedList<Integer>();
    	try {
    		if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
    		
    		String read;
    		if(lastEventType == XMLEvent.START_ELEMENT || lastEventType == XMLEvent.END_ELEMENT) {
    			read = xmlStreamReader.getName().toString();
    		} else  {
    			read = xmlStreamReader.getText().toString();
    		}
    		do {
    			if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
    			if(lastEventType == XMLEvent.END_DOCUMENT) return null;
        		else {
    			if(lastEventType == XMLEvent.START_ELEMENT || lastEventType == XMLEvent.END_ELEMENT) {
    				read = xmlStreamReader.getName().toString();
    			} else  {
    				read = xmlStreamReader.getText().toString();
    			}
    			
    			if(lastEventType!=XMLEvent.END_ELEMENT) {
    				switch(read) {
    				case "city":
    					idCittà = xmlStreamReader.getAttributeAsInt(0);
    					nome = xmlStreamReader.getAttributeValue(1);
    					coordX = xmlStreamReader.getAttributeValue(2);
    					coordY = xmlStreamReader.getAttributeValue(3);
    					coordH = xmlStreamReader.getAttributeValue(4);
    					if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
    					break;
    				case "link":
    					link.add(xmlStreamReader.getAttributeAsInt(0));
    					if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
    					break;
    				}
    			}
        		}
    		} while(!(lastEventType == XMLEvent.END_ELEMENT && read.equals("city")));
    		
    		return new Città(idCittà, nome, coordX, coordY, coordH, link);
    	} catch (XMLStreamException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    
    /**
     * Legge il comune successivo dal file
     * @return Oggetto comune
     */
    /*public Comune readNextComune() {
    	String nome = null, codice = null;
    	try {
    		if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
    		String read;
    		if(lastEventType == XMLEvent.START_ELEMENT || lastEventType == XMLEvent.END_ELEMENT) {
    			read = xmlStreamReader.getName().toString();
    		} else  {
    			read = xmlStreamReader.getText().toString();
    		}
    		do {
    			if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
    			if(lastEventType == XMLEvent.END_DOCUMENT) return null;
    			else {
    				if(lastEventType == XMLEvent.START_ELEMENT || lastEventType == XMLEvent.END_ELEMENT) {
    					read = xmlStreamReader.getName().toString();
    				} else  {
    					read = xmlStreamReader.getText().toString();
    				}
    				
    				if(lastEventType!=XMLEvent.END_ELEMENT) {
    					switch(read) {
    					case "nome":
    						if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
    						nome = xmlStreamReader.getText().toString();
    						break;
    						
    					case "codice":
    						if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
    						codice = xmlStreamReader.getText().toString();
    						break;
    					}
    				}
    			}
    		} while(!((lastEventType == XMLEvent.END_ELEMENT || lastEventType == XMLEvent.END_DOCUMENT) && read.equals("comune")));
    		
    		return new Comune(nome, codice);
    	} catch (XMLStreamException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /**
     * Legge il Codice Fiscale successivo
     * @return Stringa contenente il Codice Fiscale
     */
    /*public String readNextCF() {
    	String codice = null;
    	try {
    		if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
    		String read;
    		if(lastEventType == XMLEvent.START_ELEMENT || lastEventType == XMLEvent.END_ELEMENT) {
    			read = xmlStreamReader.getName().toString();
    		} else  {
    			read = xmlStreamReader.getText().toString();
    		}
			do {
				if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
				if(lastEventType == XMLEvent.END_DOCUMENT) return null;
        		else {
				if(lastEventType == XMLEvent.START_ELEMENT || lastEventType == XMLEvent.END_ELEMENT) {
        			read = xmlStreamReader.getName().toString();
        		} else  {
        			read = xmlStreamReader.getText().toString();
        		}
    			
    			if(lastEventType!=XMLEvent.END_ELEMENT) {
    				switch(read) {
    				case "codice":
    					if(xmlStreamReader.hasNext()) lastEventType = xmlStreamReader.next();
    					codice = xmlStreamReader.getText().toString();
    					break;
    				}
    			}
        		}
			} while(!((lastEventType == XMLEvent.END_ELEMENT || lastEventType == XMLEvent.END_DOCUMENT) && read.equals("codice")));
			
			return codice;
    	} catch (Exception e) {
    		
    	}
		return codice;
    }
    
    /**
     * Chiude lo stream. Non sono sicuro che serva a qualcosa
     */
    public void close() {
    	try {
			xmlInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

}