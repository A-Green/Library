package com.epam.lab.news.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.epam.lab.news.constants.AppMessages;

@Entity
@Table(name="COMMENTS")
public class NewsComment extends ModelObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "COMMENTS_SEQ", sequenceName = "COMMENT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTS_SEQ")
	@Column(name="COMMENT_ID")
	private int id;
	
	@Column(name="COMMENT_TEXT")
	@Size(min = 2, max = 500, message = AppMessages.VALIDATION_INVALID_COMMENT)
	private String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE", updatable = false)
	private Date creationDate;
	
	@Column(name="NEWS")
	private int newsMessageId;
		
	public NewsComment() {		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getNewsMessageId() {
		return newsMessageId;
	}

	public void setNewsMessageId(int newsMessageId) {
		this.newsMessageId = newsMessageId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + newsMessageId;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		NewsComment other = (NewsComment) obj;
		
		return Objects.equals(text, other.text)
				&& Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(newsMessageId, other.newsMessageId);
	}
}
