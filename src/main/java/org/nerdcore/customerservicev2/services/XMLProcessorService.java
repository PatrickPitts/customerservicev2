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
import java.lang.reflect.Array;
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

    public static void main(String[] args) {

    }

    //Gets all FlightModel data from the flights.xml data store
    public static ArrayList<FlightModel> getFlights(){

        ArrayList<FlightModel> flights = new ArrayList<>();

        try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(new File(flightsFileName));
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("flight");

            for(int i = 0; i < nodeList.getLength(); i++){

                Node flightNode = nodeList.item(i);
                flightNode.normalize();

                //TODO: Optimize index access, clean up text tags in flightNode
                //Even numbered item indices we simply 'text' tags that carried no information. Normalizing
                //Did not clean these tags up, so for right now we're just using odd indices.
                Node arrivalNode = flightNode.getChildNodes().item(1);
                Node departureNode = flightNode.getChildNodes().item(3);
                Node seatsNode = flightNode.getChildNodes().item(5);

                flights.add(new FlightModel(
                        ((Element)departureNode).getElementsByTagName("city").item(0).getTextContent(),
                        ((Element)departureNode).getElementsByTagName("state").item(0).getTextContent(),
                        ((Element)departureNode).getElementsByTagName("airportCode").item(0).getTextContent(),
                        ((Element)departureNode).getElementsByTagName("datetime").item(0).getTextContent(),
                        ((Element)arrivalNode).getElementsByTagName("city").item(0).getTextContent(),
                        ((Element)arrivalNode).getElementsByTagName("state").item(0).getTextContent(),
                        ((Element)arrivalNode).getElementsByTagName("airportCode").item(0).getTextContent(),
                        ((Element)arrivalNode).getElementsByTagName("datetime").item(0).getTextContent(),
                        Integer.parseInt(((Element)seatsNode).getElementsByTagName("firstClass").item(0).getTextContent()),
                        Integer.parseInt(((Element)seatsNode).getElementsByTagName("businessClass").item(0).getTextContent())
                        ));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return flights;
    }

    //Writes new FlightModel data to the flights.xml data store
    public static void writeNewFlight(FlightModel flightModel) {

        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(flightsFileName);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            Element newFlightElement = doc.createElement("flight");
            Element newArrivalElement = doc.createElement("arrival");
            Element newDepartureElement = doc.createElement("departure");
            Element newSeatsElement = doc.createElement("seats");

            Element e;

            e = doc.createElement("airportCode");
            e.appendChild(doc.createTextNode(flightModel.getArrivalAirportCode()));
            newArrivalElement.appendChild(e);

            e = doc.createElement("city");
            e.appendChild(doc.createTextNode(flightModel.getArrivalCity()));
            newArrivalElement.appendChild(e);

            e = doc.createElement("state");
            e.appendChild(doc.createTextNode(flightModel.getArrivalState()));
            newArrivalElement.appendChild(e);

            e = doc.createElement("datetime");
            e.appendChild(doc.createTextNode(flightModel.getArrivalDate()));
            newArrivalElement.appendChild(e);

            e = doc.createElement("airportCode");
            e.appendChild(doc.createTextNode(flightModel.getDepartureAirportCode()));
            newDepartureElement.appendChild(e);

            e = doc.createElement("city");
            e.appendChild(doc.createTextNode(flightModel.getDepartureCity()));
            newDepartureElement.appendChild(e);

            e = doc.createElement("state");
            e.appendChild(doc.createTextNode(flightModel.getDepartureState()));
            newDepartureElement.appendChild(e);

            e = doc.createElement("datetime");
            e.appendChild(doc.createTextNode(flightModel.getDepartureDate()));
            newDepartureElement.appendChild(e);

            e = doc.createElement("firstClass");
            e.appendChild(doc.createTextNode(Integer.toString(flightModel.getNumFirstClass())));
            newSeatsElement.appendChild(e);

            e = doc.createElement("businessClass");
            e.appendChild(doc.createTextNode(Integer.toString(flightModel.getNumBusinessClass())));
            newSeatsElement.appendChild(e);

            newFlightElement.appendChild(newArrivalElement);
            newFlightElement.appendChild(newDepartureElement);
            newFlightElement.appendChild(newSeatsElement);

            root.appendChild(newFlightElement);

            DOMSource source = new DOMSource(doc);

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.transform(source, new StreamResult(flightsFileName));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Reads all user credential information from the users.xml data store, and returns it as an ArrayList<UserModel> object
    public static ArrayList<UserModel> getUsers() {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        ArrayList<UserModel> users = new ArrayList<>();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(usersFileName));
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("user");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element e = (Element) node;
                    users.add(new UserModel(e.getElementsByTagName("username").item(0).getTextContent(),
                            e.getElementsByTagName("password").item(0).getTextContent()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

    //Appends new user login credentials to the users.xml data store.
    public static void writeNewLoginCredentials(UserModel newUserModel) {
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
