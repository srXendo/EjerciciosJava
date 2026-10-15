import java.io.File;
import java.io.IOException;
import javax.swing.text.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xsdEjercicio1 {
    public static void main(String[] args) {

        try {
            SchemaFactory fabrica =
                    SchemaFactory.newInstance(
                            XMLConstants.W3C_XML_SCHEMA_NS_URI
                    );

            fabrica.setProperty(
                    XMLConstants.ACCESS_EXTERNAL_DTD, ""
            );

            fabrica.setProperty(
                    XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""
            );

            Schema esquema =
                    fabrica.newSchema(new File("docs/alumnos.xsd"));

            Validator validador =
                    esquema.newValidator();

            validador.validate(
                    new StreamSource(new File("docs/alumnos.xml"))
            );
            // 1. Cargar el archivo XML en memoria
            File archivo = new File("docs/alumnos.xml");

            // 2. Crear la factoria y el constructor de documentos
            DocumentBuilderFactory fabricaXML = DocumentBuilderFactory.newInstance();

            DocumentBuilder constructor = fabricaXML.newDocumentBuilder();

            Document documento = (Document) constructor.parse(archivo);

            
            XPath xpath =
                    XPathFactory.newInstance().newXPath();

            NodeList nombres = (NodeList) xpath.evaluate(
                    "//nombre",
                    documento,
                    XPathConstants.NODESET
            );

            for (int i = 0; i < nombres.getLength(); i++) {

                System.out.println(
                        nombres.item(i).getTextContent()
                );
            }

            System.out.println(
                    "El XML es válido respecto al XSD"
            );

        } catch (SAXException e) {

            System.out.println(
                    "El XML no cumple el esquema: "
                    + e.getMessage()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error de lectura: " + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error de configuración: " + e.getMessage()
            );
        }
    }
}
