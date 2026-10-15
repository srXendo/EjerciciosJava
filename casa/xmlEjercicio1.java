
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xmlEjercicio1 {

        public static void main(String[] args) {
                try {
                        // 1. Cargar el archivo XML en memoria
                        File archivo = new File("docs/alumnos.xml");

                        // 2. Crear la factoria y el constructor de documentos
                        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

                        DocumentBuilder constructor = fabrica.newDocumentBuilder();

                        Document documento = constructor.parse(archivo);

                        // 3. Opcional: Normalizar la entructura del XML
                        documento.getDocumentElement().normalize();

                        System.out.println("XML leido correctamente");

                        // 4. Lectura del XML
                        System.out.println("Elemento raiz: " + documento.getDocumentElement().getTagName());

                        NodeList alumnos = documento.getElementsByTagName("alumno");

                        String nombre = "";

                        double sumaNota = 0;

                        double notaMedia = 0;

                        for (int i = 0; i < alumnos.getLength(); i++) {

                                Element alumno = (Element) alumnos.item(i);

                                NodeList nombres = alumno.getElementsByTagName("nombre");

                                if (nombres.getLength() > 0) {
                                        nombre = nombres.item(0).getTextContent();
                                        System.out.println("Nombre 1: " + nombre);
                                } else {
                                        System.out.println("El alumno no tiene nombre");
                                }

                                String edadTexto = alumno.getElementsByTagName("edad").item(0).getTextContent();

                                int edad = Integer.parseInt(edadTexto);

                                String curso = alumno.getElementsByTagName("curso").item(0).getTextContent();

                                String notaTexto = alumno.getElementsByTagName("nota").item(0).getTextContent();

                                double nota = Double.parseDouble(notaTexto);

                                sumaNota = sumaNota + nota;

                                String telefono = alumno.getElementsByTagName("telefono").item(0).getTextContent();

                                String id = alumno.getAttribute("id");
                                String estado = alumno.getAttribute("estado");
                                String fecha = alumno.getAttribute("fecha");

                                System.out.println("Nombre: " + nombre);
                                System.out.println("Edad: " + edad);
                                System.out.println("Curso: " + curso);
                                System.out.println("Nota: " + nota);
                                System.out.println("Telefono: " + telefono);
                                System.out.println("Id: " + id);
                                System.out.println("Estado: " + estado);
                                System.out.println("Fecha: " + fecha);
                                System.out.println("------------------------");

                        }
                        grabarDatos(alumnos);
                        notaMedia = sumaNota / alumnos.getLength();

                        System.out.println("Nota media: " + notaMedia);

                } catch (SAXException | IOException | javax.xml.parsers.ParserConfigurationException e) {

                        System.out.println("Error al leer el XML: " + e.getMessage());

                }

        }

        static public void grabarDatos(NodeList alumnos) {
                try {

                        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

                        DocumentBuilder constructor = fabrica.newDocumentBuilder();
                        Document documento = constructor.newDocument();

                        //Elemento raíz para crear nuestro xml
                        Element raiz = documento.createElement("Alumnos");

                        for(int i = 0; i < alumnos.getLength(); i++){

                                //Elemento alumno y nombre
                                Element alumno = documento.createElement("alumno");
                                raiz.appendChild(alumno);
                                Element alumnoAntiguo = (Element) alumnos.item(i);
                                Element nombre = documento.createElement("nombre");
                                nombre.setTextContent(alumnoAntiguo.getElementsByTagName("nombre").item(0).getTextContent());
                                alumno.appendChild(nombre);

                                Element edad = documento.createElement("edad");
                                edad.setTextContent(alumnoAntiguo.getElementsByTagName("edad").item(0).getTextContent());
                                alumno.appendChild(edad);

                                Element telefono = documento.createElement("telefono");
                                telefono.setTextContent(alumnoAntiguo.getElementsByTagName("telefono").item(0).getTextContent());
                                alumno.appendChild(telefono);

                                Element curso = documento.createElement("curso");
                                curso.setTextContent(alumnoAntiguo.getElementsByTagName("curso").item(0).getTextContent());
                                alumno.appendChild(curso);

                                String notaAntigua = alumnoAntiguo.getElementsByTagName("nota").item(0).getTextContent();
                                if(notaAntigua.equals("8.5")){
                                        notaAntigua = "9.0";
                                }
                                System.out.println(notaAntigua + notaAntigua.equals("8.5"));
                                Element nota = documento.createElement("nota");
                                nota.setTextContent(notaAntigua);
                                alumno.appendChild(nota);
                        }
                        documento.appendChild(raiz);

                        TransformerFactory fabricaTransformer = TransformerFactory.newInstance();

                        Transformer transformer = fabricaTransformer.newTransformer();

                        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
                        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                        transformer.transform(new DOMSource(documento), new StreamResult("docs/alumnos.xml"));

                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }
}
