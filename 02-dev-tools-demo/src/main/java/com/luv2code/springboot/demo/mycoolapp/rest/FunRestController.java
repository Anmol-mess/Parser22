package com.luv2code.springboot.demo.mycoolapp.rest;



import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v22.datatype.AD;
import ca.uhn.hl7v2.model.v22.datatype.ID;
import ca.uhn.hl7v2.model.v22.datatype.PN;
import ca.uhn.hl7v2.model.v22.datatype.ST;
import ca.uhn.hl7v2.model.v22.datatype.TS;
import ca.uhn.hl7v2.model.v22.group.ORU_R01_ORDER_OBSERVATION;
import ca.uhn.hl7v2.model.v22.message.ADT_A01;
import ca.uhn.hl7v2.model.v22.message.ORU_R01;
import ca.uhn.hl7v2.model.v22.segment.MSH;
import ca.uhn.hl7v2.model.v22.segment.OBX;
import ca.uhn.hl7v2.model.v22.segment.ORC;
import ca.uhn.hl7v2.model.v22.segment.PID;
import ca.uhn.hl7v2.parser.EncodingNotSupportedException;
import ca.uhn.hl7v2.parser.Parser;




public class FunRestController {
	
	


	
	
	public static void  main(String[] args) throws HL7Exception, IOException {
	String msg = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
			                  + "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414||||SA|||SA||||NONE|V1|0001|I|D.ER^50A^M110^01|ER|P00055|11B^M011^02|070615^BATMAN^GEORGE^L|555888^NOTREAL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^NOTREAL^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|199904101200||||5555112333|||666097^NOTREAL^MANNY^P\r"
			                  + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|||||||ORGANIZATION\r"
			                  + "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|||||5555112333|||666097^DNOTREAL^MANNY^P\r"
			                  + "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r"
			                  + "AL1||SEV|001^POLLEN\r"
			                  + "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r"
			                  + "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554";
			  
	
					HapiContext context = new DefaultHapiContext();
			          
			          
			          Parser p = context.getGenericParser();
			  
			          Message hapiMsg;
			          try {
			              // The parse method performs the actual parsing
			              hapiMsg = p.parse(msg);
			         } catch (EncodingNotSupportedException e1) {
			              e1.printStackTrace();
			              return ;
			          } catch (HL7Exception e1) {
			              e1.printStackTrace();
			              return ;
			          }
			          
			         
			          ADT_A01 adtMsg = (ADT_A01) hapiMsg; 
			          System.out.println(123456);
			          MSH msh = (MSH)adtMsg.getMSH();
			          
			          String msgType = msh.getMessageType().getMessageType().getValue();
					  String msgTrigger = msh.getMessageType().getTriggerEvent().getValue();
					  	
			        // Prints type of message
					  System.out.println(msgType + " " + msgTrigger);
			          
			         
			         
			          
			          
			          
			          
			          
			          
			          
						
						  
						   PN patientName = ((PID) adtMsg.getPID()).getPatientName();  
						   AD[] address= ((ADT_A01) adtMsg).getPID().getPatientAddress(); 
						   ID countryCode =adtMsg.getPID().getCountyCode(); 
						   TS birthDate =adtMsg.getPID().getDateOfBirth();  
						   ID gender = adtMsg.getPID().getSex(); 
						   ID martialStatus = adtMsg.getPID().getMaritalStatus(); 
						  //Prints patient details 
						  String familyName = patientName.getFamilyName().getValue();
						  String givenName = patientName.getGivenName().getValue();
						  
						  System.out.println(givenName + " "+ familyName);
						  System.out.print("Address is: ");  for(int i =0; i<address.length;i++) {
						   System.out.println(address[i]); }  String cc = countryCode.getValue();
						  System.out.println("Date of Birth : "+ birthDate);
						  System.out.println("Country Code is: " + cc);
						  System.out.println("Gender is " + gender);
						  System.out.println("Marraige Status is: "+ martialStatus);
						 			         
	}
}

