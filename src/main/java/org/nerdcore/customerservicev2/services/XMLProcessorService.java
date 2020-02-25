package org.nerdcore.customerservicev2.services;


import org.nerdcore.customerservicev2.models.FlightModel;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//A processor class to read and write information to the users.xml data store.
@Service
public class XMLProcessorService {

    private static String usersFileName = "src/main/resources/static/users.xml";
    private static String flightsFileName = "src/main/resources/static/flights.xml";

    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar c = new GregorianCalendar(2020, Calendar.FEBRUARY,29,9,30);
        System.out.println(sdf.format(c.getTime()));
//        writeNewFlight(new FlightModel("Seattle",
//                "WA", "SEA", new Date("3/3/2020"), ));
    }

    public static void writeNewFlight(FlightModel flightModel){

        try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(flightsFileName);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            Element newFlightElement = doc.createElement("flight");
            Element newArrivalElement = doc.createElement("arrival");
            Element newDepartureElement = doc.createElement("departure");
            Element newSeatsElement = doc.createElement("available-seats");

            newArrivalElement.appendChild(doc.createElement("arrival-airport-code").appendChild(doc.createTextNode(flightModel.getArrivalAirportCode())));
            newArrivalElement.appendChild(doc.createElement("arrival-city").appendChild(doc.createTextNode(flightModel.getArrivalCity())));
            newArrivalElement.appendChild(doc.createElement("arrival-state").appendChild(doc.createTextNode(flightModel.getArrivalCity())));
            newArrivalElement.appendChild(doc.createElement("arrival-date").appendChild(doc.createTextNode(flightModel.getArrivalDate().toString())));

            newDepartureElement.appendChild(doc.createElement("departure-airport-code").appendChild(doc.createTextNode(flightModel.getDepartureAirportCode())));
            newDepartureElement.appendChild(doc.createElement("departure-city").appendChild(doc.createTextNode(flightModel.getDepartureCity())));
            newDepartureElement.appendChild(doc.createElement("departure-state").appendChild(doc.createTextNode(flightModel.getDepartureState())));
            newDepartureElement.appendChild(doc.createElement("departure-date").appendChild(doc.createTextNode(flightModel.getDepartureDate().toString())));

            newSeatsElement.appendChild(doc.createElement("first-class").appendChild(doc.createTextNode(Integer.toString(flightModel.getNumFirstClass()))));
            newSeatsElement.appendChild(doc.createElement("business-class").appendChild(doc.createTextNode(Integer.toString(flightModel.getNumBusinessClass()))));

            newFlightElement.appendChild(newArrivalElement);
            newFlightElement.appendChild(newDepartureElement);
            newFlightElement.appendChild(newSeatsElement);

            root.appendChild(newFlightElement);

            DOMSource source = new DOMSource(doc);

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.transform(source, new StreamResult(flightsFileName));


        } catch (Exception e){
            e.printStackTrace();
        }
    }





    //Reads all user credential information from the users.xml data store, and returns it as an ArrayList<UserModel> object
    public static ArrayList<UserModel> getUsers(){

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        ArrayList<UserModel> users = new ArrayList<>();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(usersFileName));
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
            Document doc = db.parse(usersFileName);
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

            StreamResult result = new StreamResult(usersFileName);
            t.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
