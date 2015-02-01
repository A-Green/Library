package com.epam.lab.news.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.epam.lab.news.constants.AppMessages;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@Entity
@Table(name = "TAG")
@JacksonXmlRootElement(localName = "tag")
public class NewsTag extends ModelObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TAG")
	@Size(min = 2, max = 15, message = AppMessages.VALIDATION_INVALID_TAG)
	@JsonProperty
	@JacksonXmlText(value = true)
	private String tagName;
	
	public NewsTag() {	
	}
	
	public NewsTag(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public String toString() {
		return tagName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tagName);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsTag other = (NewsTag) obj;
		
		return Objects.equals(tagName, other.tagName);
	}	
}
