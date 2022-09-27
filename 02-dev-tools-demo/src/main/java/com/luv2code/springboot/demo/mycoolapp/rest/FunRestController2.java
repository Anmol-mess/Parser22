package com.luv2code.springboot.demo.mycoolapp.rest;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import ca.uhn.hl7v2.model.v23.group.*;
import ca.uhn.hl7v2.model.v23.segment.OBX;
import ca.uhn.hl7v2.model.v23.segment.MSH;
import ca.uhn.hl7v2.model.v23.segment.OBR;
import ca.uhn.hl7v2.model.Varies;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import lombok.extern.log4j.Log4j2;

@RestController
public class FunRestController2 {
	
	@GetMapping("/")
	public static void main(String[] args) throws HL7Exception {
		// TODO Auto-generated method stub
		
		String msg = "MSH|^~\\&|RAD.WMH|3.07.70.200240.23.4.7890^Redtown Hospital|BROAG||240212281720||ORU^R01|84|P|2.3.1|||AL|NE\r\n"
				+ "PID||44388|1234efgh5678c3po9101mnop2345qrst6789abcf^^^^^3.07.70.200240.23.4.7890~44388|44388|JAMES^WILLIAM^T^||23530408|M||2106-3^White|8 DEN LN^^NEPENTHE^CA^54904^USA||(408)-355-9426^PRN^PH^^1^920^2523000^^|^^^^^^^^|eng^English|M|CAT|NCC80102A|001-02-0003||^^^^^|N|Alaska|||United Federation of Planets|Y|Earth||N|\r\n"
				+ "PV1||O|^^^MOBILE HEALTH TEAM - NEPENTHE*||||4^DIETITIAN SCHEDULE^^||||||||||1629097654^JEN^ANN^CLAIRE||NCC80102A|STARFLEET Retirement Plan||||||||||||||||||||||||240212281607||||||||\r\n"
				+ "ORC||B000396110||||||||||ROGERM^ROGER^Mark^A^^^MD\r\n"
				+ "OBR|1|B000396110|B000396110|XRAY^HANDL^Hand LEFT^Radiology Report||240212281720|240212281720|240212281720|||||MAIN|||ROGERM^ROGER^Mark^A^^^MD|920-746-0510^^PH^^^920^746-0510~920-743-2798^^FX^^^920^743-2798|XRAY24021228-0022||DI|CR|240212281720||RADIOLOGY|F||^^^240212281720|||||RENFREWD^Renfrew^Donald^L^^^MD|||RENFREWD^Renfrew^Donald^L^^^MD|\r\n"
				+ "OBX|1|TX|||||||||F\r\n"
				+ "OBX|2|TX|||                                                       ||||||F\r\n"
				+ "OBX|3|TX|||Patient: JAMES,  WILLIAM  T.                        DOB: 04/08/2353              MR #: 44388            ||||||F\r\n"
				+ "OBX|4|TX|||       ||||||F\r\n"
				+ "OBX|5|TX|||Order Number: NCC80102A   Type: XRAY Hand LEFT||||||F\r\n"
				+ "OBX|6|TX|||                ||||||F\r\n"
				+ "OBX|7|TX|||001-02-0003||||||F\r\n"
				+ "OBX|8|TX|||                                                ||||||F\r\n"
				+ "OBX|9|TX|||LEFT HAND RADIOGRAPHS  ||||||F\r\n"
				+ "OBX|10|TX|||   ||||||F\r\n"
				+ "OBX|11|TX||| CLINICAL INFORMATION: Trigger thumb.  ||||||F\r\n"
				+ "OBX|12|TX|||   ||||||F\r\n"
				+ "OBX|13|TX||| COMPARISON STUDIES: No comparison studies are available at this time.  ||||||F\r\n"
				+ "OBX|14|TX|||   ||||||F\r\n"
				+ "OBX|15|TX||| TECHNIQUE: AP, lateral and oblique radiographs of left hand.  ||||||F\r\n"
				+ "OBX|16|TX|||   ||||||F\r\n"
				+ "OBX|17|TX||| INTERPRETATION:  ||||||F\r\n"
				+ "OBX|18|TX||| Bones: Incidentally noted is a corticated ossification off of the ulnar styloid   ||||||F\r\n"
				+ "OBX|19|TX||| in keeping with remote avulsion injury or an accessory ossification center.  ||||||F\r\n"
				+ "OBX|20|TX||| Joints: Joint narrowing and osteophytic spurring along the thumb CMC.  ||||||F\r\n"
				+ "OBX|21|TX||| Other structures: Normal.  ||||||F\r\n"
				+ "OBX|22|TX|||   ||||||F\r\n"
				+ "OBX|23|TX||| IMPRESSION:  ||||||F\r\n"
				+ "OBX|24|TX||| Degenerative changes of the thumb CMC.  ||||||F\r\n"
				+ "OBX|25|TX|||   ||||||F\r\n"
				+ "OBX|26|TX||| Donald L. Renfrew, M.D.  ||||||F\r\n"
				+ "OBX|27|TX||| Radiology Associates of the Fox Valley  ||||||F\r\n"
				+ "OBX|28|TX||| RAFVDC  ||||||F\r\n"
				+ "OBX|29|TX||| I.2  ||||||F\r\n"
				+ "OBX|30|TX|||   ||||||F\r\n"
				+ "OBX|31|TX|||   ||||||F\r\n"
				+ "OBX|32|TX|||Dictated By: Renfrew,Donald L MD                Dictated Date/Time: 12/28/2402 1110                     ||||||F\r\n"
				+ "OBX|33|TX|||               ||||||F\r\n"
				+ "OBX|34|TX|||ESigned By: Renfrew,Donald L MD                ESigned Date Time: 12/28/2402 1210                       ||||||F\r\n"
				+ "OBX|35|TX|||             ||||||F\r\n"
				+ "";		          
				          
		
		
		PipeParser pipeParser = new PipeParser();
		pipeParser.setValidationContext(new ca.uhn.hl7v2.validation.impl.NoValidation());
		Message message = pipeParser.parse(msg);
		ORU_R01 oru = (ORU_R01) message;
		MSH msh = oru.getMSH();
		String sendingApp = msh.getSendingApplication().encode();
		String sendingFacility = msh.getSendingFacility().encode();
		
		for (ORU_R01_RESPONSE response : oru.getRESPONSEAll()) {
			
				for (ORU_R01_ORDER_OBSERVATION orderObservation : response.getORDER_OBSERVATIONAll()) {
				    OBR obr = orderObservation.getOBR();
				    String fillerOrderNumber = obr.getObr3_FillerOrderNumber().encode();
				    
					    for (ORU_R01_OBSERVATION observation : orderObservation.getOBSERVATIONAll()) {
					    	
					    	OBX obx = observation.getOBX();
					        String type = obx.getObx3_ObservationIdentifier().getCe2_Text().getValue();
					        String status = obx.getObservResultStatus().getValue();
					        for (Varies varies : obx.getObx5_ObservationValue()) {
					          String value = varies.encode();
					         // log.info("value {} type {} status {}", value, type, status);
					          System.out.println(value + " "+ type +" " + status);
					    }
				}
		}

	}

	}
}
