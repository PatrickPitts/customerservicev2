<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New Flight</title>
</head>
<body>
<h2>Create a new Flight for the Database</h2>
<form action="/site/new-flight" method="post">
    <table>
        <tr>
            <td><label for="departureCity">Departure City: </label></td><td><input type="text" name="departureCity" id="departureCity"></td>
            <td><label for="arrivalCity">Arrival City: </label></td><td><input type="text" name="arrivalCity" id="arrivalCity"></td>
        </tr>
        <tr>
            <td><label for="departureState">Departure State: </label></td><td><input type="text" name="departureState" id="departureState"></td>
            <td><label for="arrivalState">Arrival State: </label></td><td><input type="text" name="arrivalState" id="arrivalState"></td>
        </tr>
        <tr>
            <td><label for="departureAirportCode">Departure Airport Code: </label></td><td><input type="text" name="departureAirportCode" id="departureAirportCode"></td>
            <td><label for="arrivalAirportCode">Arrival Airport Code: </label></td><td><input type="text" name="arrivalAirportCode" id="arrivalAirportCode"></td>
        </tr>
        <tr>
            <td><label for="departureDate">Departure Date and Time: </label></td><td><input type="datetime-local" name="departureDate" id="departureDate"></td>
            <td><label for="arrivalDate">Arrival Date and Time: </label></td><td><input type="datetime-local" name="arrivalDate" id="arrivalDate"></td>
        </tr>
        <tr>
            <td><label for="numFirstClass">Number of 1st class seats:</label></td><td><input type="number" name="numFirstClass" id="numFirstClass" value="0"></td>
        </tr>
        <tr>
            <td><label for="numBusinessClass">Number of Business class seats:</label></td><td><input type="number" name="numBusinessClass" id="numBusinessClass" value="0"></td>
        </tr>

    </table>



    <input type="submit" value="Submit">
</form>

</body>
</html>