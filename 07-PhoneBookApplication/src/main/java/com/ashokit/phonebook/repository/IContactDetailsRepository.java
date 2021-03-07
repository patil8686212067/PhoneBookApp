package com.ashokit.phonebook.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.phonebook.entity.Contact;

public interface IContactDetailsRepository extends JpaRepository<Contact,Serializable> {

}
