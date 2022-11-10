package com.duetsche.nomenclature.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.duetsche.nomenclature.model.Nomenclature;

public class CSVHelper {
	public static String TYPE = "text/csv";
	
	  static String[] NAC_HEADERs = { "Order", "Level", "Code", "Parent", "Description", "This item includes",
			  "This item also includes","Rulings","This item excludes","Reference to ISIC Rev. 4"};

	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }

	  public static List<Nomenclature> csvToNacDetails(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	    	
	    	//initialize csv parser
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<Nomenclature> nacDetails = new ArrayList<Nomenclature>();
	      
	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	      
	      //fetch csv record and add to a list
	      csvRecords.forEach(csvRecord->{nacDetails.add(getNomeclatureRecord(csvRecord));});
	      return nacDetails;
	    } 
	    catch (IOException e) {
	      throw new RuntimeException("failed to parse CSV file: " + e.getMessage());
	    }
	  }

	//this function returns a nacRecord
	private static Nomenclature getNomeclatureRecord(CSVRecord csvRecord) {
		return  new Nomenclature(
	              Long.parseLong(csvRecord.get("Order")),
	              Integer.parseInt(csvRecord.get("Level")),
	              csvRecord.get("Code"),
	              csvRecord.get("Parent"),
	              csvRecord.get("Description"),
	              csvRecord.get("This item includes"),
	              csvRecord.get("This item also includes"),
	              csvRecord.get("Rulings"),
	              csvRecord.get("This item excludes"),
	              csvRecord.get("Reference to ISIC Rev. 4")
	              );
	}
}
