package com.epam.lab.news.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import com.epam.lab.news.constants.AppMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="NEWS")
@XmlRootElement(name = "news")
public class News extends ModelObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "NEWS_ID_SEQ", sequenceName = "NEWS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_ID_SEQ")
	@Column(name="NEWS_ID")
	private int id = 0;
	
	@Column(name="TITLE")
	@NotEmpty(message = AppMessages.VALIDATION_TITLE_EMPTY)
	@Size(max = 100, message = AppMessages.VALIDATION_TITLE_LARGE)
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE", updatable = false)
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFICATION_DATE")
	@JsonProperty
	private Date modificationDate;
	
	@Column(name="BRIEF")
	@NotEmpty(message = AppMessages.VALIDATION_BRIEF_EMPTY)
	@Size(max = 500, message = AppMessages.VALIDATION_BRIEF_LARGE)
	private String brief;
	
	@Column(name="NEWS_CONTENT")
	@NotEmpty(message = AppMessages.VALIDATION_CONTENT_EMPTY)
	@Size(max = 2048, message = AppMessages.VALIDATION_CONTENT_LARGE)
	private String content;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="NEWS", updatable = false)
	@JsonIgnore
	private Set<NewsComment> comments;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinTable(name = "NEWS_AUTHORS", 
	joinColumns = { @JoinColumn(name = "NEWS") }, 
	inverseJoinColumns = { @JoinColumn(name = "AUTHOR") })
	@JsonIgnore
	@JsonDeserialize
	private Set<NewsAuthor> authors;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinTable(name = "NEWS_TAG", 
	joinColumns = { @JoinColumn(name = "NEWS") }, 
	inverseJoinColumns = { @JoinColumn(name = "TAG") })
	@JsonIgnore
	@JsonDeserialize
	private Set<NewsTag> tags;

	public News() {		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBrief() {
		return brief;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date date) {
		this.creationDate = date;
	}
	
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	public Set<NewsComment> getComments() {
		return comments;
	}

	public void setComments(Set<NewsComment> comments) {
		this.comments = comments;
	}

	public Set<NewsAuthor> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<NewsAuthor> authors) {
		this.authors = authors;
		
	}

	public Set<NewsTag> getTags() {
		return tags;
	}

	public void setTags(Set<NewsTag> tags) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {

		return Objects.hash(brief, content, creationDate, title, modificationDate, authors, tags);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		News other = (News) obj;

		return 	Objects.equals(brief, other.brief)
				&& Objects.equals(title, other.title)
				&& Objects.equals(content, other.content)
				&& Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(modificationDate, other.modificationDate)
				&& Objects.equals(authors, other.getAuthors())
				&& Objects.equals(tags, other.getTags());
		 
	}

	@Override
	public String toString() {
		StringBuilder  strBuilder = new StringBuilder();
		
		strBuilder.append("News: id: ").append(id)
		.append("\n title: ").append(title)
		.append("\n creation date: ").append(creationDate)
		.append("\n modification date: ").append(modificationDate)
		.append("\n brief: ").append(brief)
		.append("\n content: ").append(content)
		.append("\n");
		
		return strBuilder.toString();
	}
}
