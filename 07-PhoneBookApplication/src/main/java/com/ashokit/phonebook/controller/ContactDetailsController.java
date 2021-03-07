package com.ashokit.phonebook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.phonebook.constants.AppConstants;
import com.ashokit.phonebook.entity.Contact;
import com.ashokit.phonebook.exception.NoContactFoundException;
import com.ashokit.phonebook.props.AppProperties;
import com.ashokit.phonebook.service.ContactDetailsServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("This is the API Documentation related to PhoneBook Application")

public class ContactDetailsController {

	
	private static final Logger logger = LoggerFactory.getLogger(ContactDetailsController.class);
	
	private ContactDetailsServiceImpl contactDetailsServiceImpl;
	
	private AppProperties appProps;


	public ContactDetailsController(ContactDetailsServiceImpl contactDetailsServiceImpl,AppProperties appProps) 
	{

		this.contactDetailsServiceImpl = contactDetailsServiceImpl;
		this.appProps = appProps;

	}
	
	
	//-------- Create Contact Details ------------------------------------------------

	 @ApiOperation("This end point is used to save contact in Database")
	 @RequestMapping(value="/saveContact", method = RequestMethod.POST)
	 public ResponseEntity<String> saveContact(@RequestBody Contact contact) 
	 {
		 logger.debug("**** saveContact() - Execution Started **** ");
		 
		 try {
			     boolean isSaved = contactDetailsServiceImpl.saveContact(contact);
			  if (isSaved) 
			  {
				 
			  logger.info("**** saveContact() -Contact Saved **** ");
			  String succMsg =appProps.getMessages().get(AppConstants.SAVE_CONTACT_SUCCESS);
			      return new ResponseEntity<>(succMsg, HttpStatus.CREATED);
			   }
		 } catch (Exception e)
		 {
		 logger.error(" ** Exception Occured : ** " +e);
		 }
		 logger.info("**** saveContact() - Contact Not Saved**** ");
		 logger.debug("**** saveContact() - Execution Ended**** ");
		 
		 String failMsg =appProps.getMessages().get(AppConstants.SAVE_CONTACT_FAILED);
		 
		 return new ResponseEntity<>(failMsg,HttpStatus.INTERNAL_SERVER_ERROR);
	 
	 }

	
	 //---------------------- Get All Contact Details -----------------------------

	 @RequestMapping(value = "/getAllContacts", method = RequestMethod.GET)
	@ApiOperation("This end point is used to get all Contacts from Database")
	 public ResponseEntity<List<Contact>> getAllContacts() {
		 
	 logger.debug("** getAllContacts() - Execution Started**");
	 
	 List<Contact> allContacts = null;
	 
	 try {
		 allContacts = contactDetailsServiceImpl.getAllContactDetails();
	 
		 if (allContacts.isEmpty()) 
		 {
		  logger.info("** getAllContacts( ) -Records Not Available **");
		 }
	 } catch (Exception e) 
	 {
		 logger.error("Exception Occured : ** " +e);
	 }
	 
	 logger.debug("** getAllContacts() - Execution Ended**");
	 
	 return new ResponseEntity<>(allContacts,HttpStatus.OK);
	 }


	// ---------------- Get Conatct BY ID using Path Variable ------------------
	@ApiOperation("This end point is used to get Contacts from Database using contactId")
	@RequestMapping(value = "/getContactById/{id}", method = RequestMethod.GET)
	 public ResponseEntity<Contact> getContactById(@PathVariable("id") Integer contactId) {
		 
	 logger.debug("** getContactById() - Execution Started** ");
	 
	 Contact contact = null;
	 try {
		 contact = contactDetailsServiceImpl.findByContactId(contactId);
		 if (contact == null) {
		 logger.info("** getContactById() - No Contact Found ** ");
			throw new NoContactFoundException("Contact Not Found for requested ContactID.");
	 }
	 } catch (Exception e) {
		 logger.error("** Exception Occured ** :: " +e);
	 }
	 
	 logger.debug("** getContactById() - Execution Ended **");
	 return new ResponseEntity<>(contact, HttpStatus.OK);
	 
	 }


	// -----------Update Contact ------------------------------------

	@ApiOperation("This end point is used update Contacts of Database")
	@RequestMapping(value = "/updateContact", method = RequestMethod.PUT)
	public ResponseEntity<String>  updateContact(@RequestBody Contact contactDetails) {

		int contactCreationStatus = contactDetailsServiceImpl.updateContactDetails(contactDetails);

		if (contactCreationStatus==1) {

			return new ResponseEntity<>("Contact update Successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error Occured while updating Contact", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ---------------- Get Conatct BY ID Using Request Param ------------------
	
	@ApiOperation("This end point is used Delete Contacts of Database")
	@RequestMapping(value = "/deletContactById", method = RequestMethod.PUT)
	public ResponseEntity<String> deleteContactById(@RequestParam("contactId") Integer contactId) {
		
	logger.debug("** deleteContactById - Execution Started** ");
	
	ResponseEntity<String> responseEntity = null;
	
	try {
		boolean isDeleted =contactDetailsServiceImpl.deleteContactDetails(contactId);
		
		if (isDeleted) 
		{
			
			logger.info("** deleteContactById( ) -Record Deleted ** ");
			
			String succMsg =appProps.getMessages().get(AppConstants.DELETE_CONTACT_SUCCESS);
		
		    responseEntity = new ResponseEntity<>(succMsg, HttpStatus.OK);
		 }
	} catch (Exception e) 
	{
	logger.error(" ** Exception Occured ** " +e);

	String failMsg =appProps.getMessages().get(AppConstants.DELETE_CONTACT_FAILED);
	
	responseEntity = new ResponseEntity<>(failMsg,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	logger.debug("** deleteContactById - Execution Ended** ");
	
	
	return responseEntity;
	}
	
}


//https://www.codejava.net/frameworks/spring-boot/logback-rolling-files-example