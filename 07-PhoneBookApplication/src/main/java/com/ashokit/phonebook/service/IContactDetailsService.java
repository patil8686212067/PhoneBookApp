package com.ashokit.phonebook.service;

import java.util.List;

import com.ashokit.phonebook.entity.Contact;

public interface IContactDetailsService {

	
	//-- use to save contact Details ----------------------------------------
	public boolean saveContact(Contact contactDetails);
	
	
	//---find paritcular contact Details by Id
	public Contact findByContactId(Integer contactId);
	
	
	public int updateContactDetails(Contact contactDetails);
	
	
	public boolean deleteContactDetails(Integer contactId);
	
	public List<Contact> getAllContactDetails();
}
