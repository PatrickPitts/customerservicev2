package org.nerdcore.customerservicev2.services;


import org.nerdcore.customerservicev2.models.UserModel;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

//A processor class to read and write information to the users.xml data store.
@Service
public class XMLProcessorService {

    //private static String filename = "src/main/java/org/nerdcore/customerservicev2/users.xml";
    private static String filename = "src/main/resources/static/users.xml";

    //Reads all user credential information from the users.xml data store, and returns it as an ArrayList<UserModel> object
    public static ArrayList<UserModel> getUsers(){

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        ArrayList<UserModel> users = new ArrayList<>();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filename));
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("user");

            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){

                    Element e = (Element) node;
                    users.add(new UserModel(e.getElementsByTagName("username").item(0).getTextContent(),
                            e.getElementsByTagName("password").item(0).getTextContent() ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

    //Appends new user login credentials to the users.xml data store.
    public static void writeNewLoginCredentials(UserModel newUserModel){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(filename);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            Element newUserElement = doc.createElement("user");
            Element usernameElement = doc.createElement("username");
            Element passwordElement = doc.createElement("password");

            usernameElement.appendChild(doc.createTextNode(newUserModel.getUsername()));
            passwordElement.appendChild(doc.createTextNode(newUserModel.getPassword()));

            newUserElement.appendChild(usernameElement);
            newUserElement.appendChild(passwordElement);

            root.appendChild(newUserElement);

            DOMSource source = new DOMSource(doc);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            StreamResult result = new StreamResult(filename);
            t.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
