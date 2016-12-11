package introsde.assignment.soap.client;

import java.io.PrintWriter;
import java.util.List;

import introsde.assignment.soap.ws.Measure;
import introsde.assignment.soap.ws.Measurement;
import introsde.assignment.soap.ws.MeasurementHistory;
import introsde.assignment.soap.ws.People;
import introsde.assignment.soap.ws.People_Service;
import introsde.assignment.soap.ws.Person;


/***
 * A simple client that calls each service and prints the result both on the console and on a log file.
 * 
 * @author alan
 *
 */

public class PeopleClient {
	// Useful constraints used throughout the client implementation
	static final String serverUri = "http://introsde-assignment3-ar.herokuapp.com/ws/people?wsdl";
	// static final String serverUri = "http://127.0.1.1:6902/ws/people?wsdl";
	static final String logFileName = "client-server.log";
	
	static PrintWriter writer;			// the writer to log results
	static People_Service service;
	static People people;
	
	public static void main(String[] args) throws Exception {
		
		writer = new PrintWriter(logFileName, "UTF-8");
		service = new People_Service();
		people = service.getPeopleImplementationPort();
		
		// Initialize the database with 5 people and some measurements
		people.initializeDatabase();
		
		
		/********************************************************************************
		 * REQUEST #0: Print the server WSDL URL that we are calling					*
		 ********************************************************************************/
		
		printHeader(serverUri, writer);		// print the header on the log file
		
		
		/********************************************************************************
		 * REQUEST #1: readPersonList()													*
		 ********************************************************************************/
		
		printRequestHeader("Request #1: readPersonList()\n"
				+ "Print the whole list of people:", writer);
		
		// Store all the people into a list
		List<Person> peopleList = people.readPersonList();
		
		printResultHeader();
		
		// Print each person in the list to both the console and the log file
		for (Person person : peopleList) {
			printResultBody(convertPersonToString(person), writer);
		}
		
		printSeparator();
		
		
		/********************************************************************************
		 * REQUEST #2: readPerson(2)													*
		 ********************************************************************************/
		
		printRequestHeader("Request #2: readPerson(2)\n"
				+ "Print the person with ID 2:", writer);
		
		// Store the person of interest
		Person personToRead = people.readPerson(new Long(2));
		
		printResultHeader();
		
		// Print the person to both the console and the log file
		printResultBody(convertPersonToString(personToRead), writer);
		
		printSeparator();
		
		
		/********************************************************************************
		 * REQUEST #3: updatePerson(person)												*
		 ********************************************************************************/
		
		printRequestHeader("Request #3: updatePerson(person)\n"
				+ "Update person (ID: 1) with new firstname \"FIRSTNAME UPDATED\":", writer);
		
		// Store the person of interest
		Person personToUpdate = people.readPerson(new Long(1));
		
		// Change the person's firstname and get its ID
		personToUpdate.setFirstname("FIRSTNAME UPDATED");
		people.updatePerson(personToUpdate);
		
		// Read that person again using its ID
		personToUpdate = people.readPerson(new Long(1));
		
		printResultHeader();
		
		// Print the person to both the console and the log file
		printResultBody(convertPersonToString(personToUpdate), writer);
		
		printSeparator();
		
		
		/********************************************************************************
		 * REQUEST #4: createPerson(person)												*
		 ********************************************************************************/
		
		printRequestHeader("Request #4: createPerson(person)\n"
				+ "Create myself (Alan Ramponi - Aug 15th, 1991):", writer);
		
		// Create the needed objects in order to add a new person
		Person personToCreate = new Person();
		Measurement measurement = new Measurement();
		Person.HealthProfile healthProfile = new Person.HealthProfile();
		
		// Set the person's attributes
		personToCreate.setFirstname("Alan");
		personToCreate.setLastname("Ramponi");
		personToCreate.setBirthdate("15-08-1991");
		
		// Set the measurement's attributes
		measurement.setMeasure("weight");
		measurement.setValue("78");
		measurement.setValueType("Int");
		
		// Add them to a list of measurements and set the person's health profile
		List<Measurement> measurements = healthProfile.getMeasureType();
		measurements.add(measurement);
		
		// Then, create the person
		personToCreate = people.createPerson(personToCreate);
		
		// Store the ID of the created person
		int idPersonCreated = personToCreate.getId();
		
		printResultHeader();
		
		// Print the person to both the console and the log file
		printResultBody(convertPersonToString(personToCreate), writer);
		
		printSeparator();
		
		
		/********************************************************************************
		 * REQUEST #5: deletePerson(id)													*
		 ********************************************************************************/
		
		printRequestHeader("Request #5: deletePerson(" + idPersonCreated + ")\n"
				+ "If successful, print the deleted person (ID: " + idPersonCreated + "):", writer);
		
		// Store the person of interest
		Person personToDelete = people.readPerson(new Long(idPersonCreated));
		
		// Delete the person of interest
		people.deletePerson(new Long(idPersonCreated));
		
		printResultHeader();
		
		// Check if the person was correctly deleted
		if (people.readPerson(new Long(idPersonCreated)) == null) {
			// Print the person to both the console and the log file
			printResultBody(convertPersonToString(personToDelete), writer);
		} else {
			// Print an error if the procedure wasn't successful
			printResultBody("ERROR! The person with ID: " + personToDelete.getId() + "doesn't exists!", writer);
		}

		printSeparator();
		
		
		/********************************************************************************
		 * REQUEST #6: readPersonHistory(1, "weight")									*
		 ********************************************************************************/
		
		printRequestHeader("Request #6: readPersonHistory(1, \"weight\")\n"
				+ "Print the \"weight\" history for the person with ID 1:", writer);
		
		// Store the person of interest
		Person personToCheck = people.readPerson(new Long(1));
		int personToCheckId = personToCheck.getId();
		
		// Store the person's measurement history into a list
		List<MeasurementHistory> measurementHistory = people.readPersonHistory(new Long(personToCheckId), "weight");
		
		printResultHeader();
		
		// Print each measurement in the list to both the console and the log file
		for (MeasurementHistory m : measurementHistory) {
			printResultBody(convertMeasurementHistoryToString(m), writer);
		}
		
		printSeparator();
		
		
		/********************************************************************************
		 * REQUEST #7: readMeasureTypes()												*
		 ********************************************************************************/
		
		printRequestHeader("Request #7: readMeasureTypes()\n"
				+ "Print the whole list of measures:", writer);
		
		// Store all the measures into a list
		List<Measure> measuresList = people.readMeasureTypes();
		
		printResultHeader();
		
		// Print each measure in the list to both the console and the log file
		for (Measure measure : measuresList) {
			printResultBody(convertMeasureToString(measure), writer);
		}
		
		printSeparator();
		
		
		/********************************************************************************
		 * REQUEST #8: readPersonMeasure(1, "bloodpressure", 13)						*
		 ********************************************************************************/
		
		printRequestHeader("Request #8: readPersonMeasure(1, \"bloodpressure\", 13)\n"
				+ "Print the bloodpressure measurement (mID: 13) for the person (ID: 1):", writer);
		
		// Store the measurement
		MeasurementHistory mHistory = people.readPersonMeasure(new Long(1), "bloodpressure", new Long(13));
		
		printResultHeader();
		
		// Print the measurement to both the console and the log file
		printResultBody(convertMeasurementHistoryToString(mHistory), writer);
		
		printSeparator();
		
		
		/********************************************************************************
		 * REQUEST #9: savePersonMeasure(1, measure)									*
		 ********************************************************************************/
		
		printRequestHeader("Request #9: savePersonMeasure(1, measurement)\n"
				+ "Print the new bloodpressure measurement (72) for the person (ID: 1):", writer);
		
		// Set the new measurement to save
		Measurement measurementToSave = new Measurement();
		measurementToSave.setMeasure("bloodpressure");
		measurementToSave.setValue("72");
		measurementToSave.setValueType("Int");
		
		// Store the new measurement
		Measurement measurementSaved = people.savePersonMeasure(new Long(1), measurementToSave);
		
		printResultHeader();
		
		// Print the measurement to both the console and the log file
		printResultBody(convertMeasurementToString(measurementSaved), writer);
		
		printSeparator();
		
		
		/********************************************************************************
		 * REQUEST #10: updatePersonMeasure(1, measure)									*
		 ********************************************************************************/
		
		printRequestHeader("Request #10: updatePersonMeasure(1, measurement)\n"
				+ "Update (so, without modifying the creation date) the newly created\n"
				+ "bloodpressure measurement from 72 to 70 for the person (ID: 1):", writer);
		
		// Get the measurement to update
		List<MeasurementHistory> mHistoryList = people.readPersonHistory(new Long(1), "bloodpressure");
		MeasurementHistory mHistoryToUpdate = mHistoryList.get(mHistoryList.size()-1);
		
		// Set the new measurement value
		mHistoryToUpdate.setValue("70");
		
		// Update the measurement and store its ID
		Long idMeasure = people.updatePersonMeasure(new Long(1), mHistoryToUpdate);
		
		// Get the updated measurement in order to print it later
		MeasurementHistory measurementUpdated = people.readPersonMeasure(new Long(1), "bloodpressure", idMeasure);
		
		printResultHeader();
		
		// Print the measurement to both the console and the log file
		printResultBody(convertMeasurementHistoryToString(measurementUpdated), writer);
		
		printSeparator();
		
		
		
		// Close the writer
		writer.close();
		
		// Initialize the database with 5 people and some measurements
		people.initializeDatabase();
	}
	
	
	/********************************************************************************
	 * Helper methods used to accomplish the output goals							*
	 ********************************************************************************/
	
	/***
	 * A method used to print the header with the server URL.
	 * @param url: the URL used to make requests
	 * @param writer: the print writer
	 */
	private static void printHeader(String url, PrintWriter writer) {
		String header = "==============================================================================\n" +
						"Heroku server URL: " + serverUri + "\n" +
						"==============================================================================\n";
		
		System.out.println(header);
		writer.println(header);
	}
	
	/***
	 * A method used to print the request header.
	 * @param request: the string representing the request
	 * @param writer: the print writer
	 */
	private static void printRequestHeader(String request, PrintWriter writer) {
		request = request + "\n";
		System.out.println(request);
		writer.println(request);
	}
	
	/***
	 * A method used to print the result header.
	 */
	private static void printResultHeader() {
		String result = "=> Result:\n";
		System.out.println(result);
		writer.println(result);
	}
	
	/***
	 * A method used to print the result body.
	 * @param result: the string representing the result
	 * @param writer: the print writer
	 */
	private static void printResultBody(String result, PrintWriter writer) {
		result = "\t" + result + "\n";
		System.out.println(result);
		writer.println(result);
	}
	
	/***
	 * A method used to print a visual separator between the requests.
	 */
	private static void printSeparator() {
		System.out.println("==============================================================================\n");
		writer.println("==============================================================================\n");
	}
	
	/***
	 * A method used to pretty output a person.
	 * @param person: the person of interest
	 * @return a well formatted string
	 */
	private static String convertPersonToString(Person person) {
		if (person != null) {
			String p = "[id]: " + person.getId() + "\n" +
					   "\t[firstname]: " + person.getFirstname() + "\n" +
					   "\t[lastname]: " + person.getLastname() + "\n" +
					   "\t[birthdate]: " + person.getBirthdate();
			return p;
		} else {
			return "ERROR! This person doesn't exist!";
		}
	}
	
	/***
	 * A method used to pretty output a measure.
	 * @param measure: the measure of interest
	 * @return a well formatted measure
	 */
	private static String convertMeasureToString(Measure measure) {
		if (measure != null) {
			String m = "[id]: " + measure.getId() + "\n" +
					   "\t[name]: " + measure.getName();
			return m;
		} else {
			return "ERROR! This measure doesn't exist!";
		}
	}
	
	/***
	 * A method used to pretty output a measurement.
	 * @param measurement: the measurement of interest
	 * @return a well formatted measurement
	 */
	private static String convertMeasurementToString(Measurement measurement) {
		if (measurement != null) {
			String m = "[measure]: " + measurement.getMeasure() + "\n" +
					   "\t[value]: " + measurement.getValue() + "\n" +
					   "\t[valueType]: " + measurement.getValueType() + "\n" +
					   "\t[created]: " + measurement.getCreated();
			return m;
		} else {
			return "ERROR! This measurement doesn't exist!";
		}
	}
	
	/***
	 * A method used to pretty output a measurement history.
	 * @param mHistory: the measurement history of interest
	 * @return a well formatted measurement history
	 */
	private static String convertMeasurementHistoryToString(MeasurementHistory mHistory) {
		if (mHistory != null) {
			String mValue = "Int";
			String m = "[mid]: " + mHistory.getMid() + "\n" +
					   "\t[value]: " + mHistory.getValue() + "\n" +
					   "\t[valueType]: " + mValue + "\n" +
					   "\t[created]: " + mHistory.getCreated();
			return m;
		} else {
			return "ERROR! This measurement history doesn't exist!";
		}
	}
}