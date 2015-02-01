package com.epam.lab.news.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.epam.lab.news.constants.AppMessages;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@Entity
@Table(name = "AUTHORS")
@JacksonXmlRootElement(localName = "author")
public class NewsAuthor extends ModelObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "AUTHORS_SEQ", sequenceName = "NEWS_AUTHORS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHORS_SEQ")
	@Column(name="AUTHOR_ID")
	private int id;
	
	@Column(name = "NAME")
	@Size(min = 3, max = 50, message = AppMessages.VALIDATION_INVALID_AUTHOR_NAME)
	@JacksonXmlText(value = true)
	private String name;
	
	public NewsAuthor() {	
	}
	
	public NewsAuthor(String authorName) {
		this.name = authorName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override 
	public String toString() {
		return "id: " + id + "\nname: " + name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		NewsAuthor other = (NewsAuthor) obj;
		return Objects.equals(name, other.name);
	}
	
	
}
