<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.nerdcore.customerservicev2.models.FlightModel"       %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search For Flights</title>
</head>
<body>
<%--
<% ArrayList<FlightModel> flights = (ArrayList<FlightModel>) request.getAttribute("flights"); %>
--%>
<table>
    <tr><th>Arrival: </th><th>Departure: </th></tr>
    <c:forEach var="flight" items="${flights}">
        <tr><td>City: ${flight.arrivalCity}<br>State: ${flight.arrivalState}</td><td>City: ${flight.departureCity}<br>State: ${flight.departureState}</td></tr>
    </c:forEach>
</table>


</body>
</html>