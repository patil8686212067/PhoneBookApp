package com.ashokit.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashokit.phonebook.entity.Contact;
import com.ashokit.phonebook.exception.NoContactFoundException;
import com.ashokit.phonebook.repository.IContactDetailsRepository;


@Service
public class ContactDetailsServiceImpl implements IContactDetailsService {
	
	
	private IContactDetailsRepository iContactDetailsRepository;
	
	ContactDetailsServiceImpl(IContactDetailsRepository iContactDetailsRepository)
	{
		
		this.iContactDetailsRepository = iContactDetailsRepository;
	}

	@Override
	public boolean saveContact(Contact contactDetails) {
		Contact ContactDetails =iContactDetailsRepository.save(contactDetails);
		if(ContactDetails.getContactId()==null) {
			throw new NoContactFoundException("Failed to Save Contact.");

		}else
		{
	      return true;
		}
	
	}

	@Override
	public Contact findByContactId(Integer contactId) {
		Optional<Contact> findById = iContactDetailsRepository.findById(contactId);
		
		if(findById.isPresent()) {
			return findById.get();
		}else 
		{
			return null;
		}
		
	}

	@Override
	public int updateContactDetails(Contact contactDetails) {
		int updateCount;
		Contact ContactDetails = iContactDetailsRepository.save(contactDetails);
		if(ContactDetails!=null) {
			updateCount=1;
		}else {
			throw new NoContactFoundException("Contact Not Found for requested ContactID to Update.");
		}
		
		return updateCount;
	}

	@Override
	public boolean deleteContactDetails(Integer contactId) {
		
		try {
			    iContactDetailsRepository.deleteById(contactId);
			 	return true;
		}catch(Exception e) {
			throw new NoContactFoundException("Contact Not Found for requested ContactID to Delete"+e);

		}
		
		
	}

	@Override
	public List<Contact> getAllContactDetails() {
		return iContactDetailsRepository.findAll();
	}

}
