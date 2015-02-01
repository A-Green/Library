package by.epam.green.library.model;

import java.util.ArrayList;

/**
 * The Class Book represents book in catalog
 */
public class Book extends Entity {

	private static final long serialVersionUID = 1L;
	
	/** The title. */
	private String title;
	
	/** The publisher of the book. */
	private Publisher publisher;
	
	/** The publishing year. */
	private int year; 
	
	/** The book authors authors. */
	private ArrayList<Author> authors = new ArrayList<Author>();
	
	/** The book genre. */
	private BookGenre genre;
	
	/** The availability to order book. */
	private boolean available = true;
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the book title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the publisher.
	 *
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}
	
	/**
	 * Sets the publisher.
	 *
	 * @param publisher the book publisher
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * Gets the authors.
	 *
	 * @return the authors
	 */
	public ArrayList<Author> getAuthors() {
		return authors;
	}
	
	/**
	 * Sets the authors.
	 *
	 * @param authors the book authors
	 */
	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}
	
	/**
	 * Gets the genre.
	 *
	 * @return the genre
	 */
	public BookGenre getGenre() {
		return genre;
	}
	
	/**
	 * Sets the genre.
	 *
	 * @param genre the book genre
	 */
	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}

	/**
	 * Gets the publishing year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the publishing year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Checks if is available to order.
	 *
	 * @return true, if is available to order
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Sets the available.
	 *
	 * @param available the new available
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public String toString() {
		StringBuilder srtBuilder = new StringBuilder();
		srtBuilder.append("Id = ").append(getId())
		.append(" title: ").append(title)
		.append(" publusher: ").append(publisher.getName())
		.append(" genre: ").append(genre)
		.append(" year: ").append(year);
		
		return srtBuilder.toString();
	}
	

	/**
	* {@inheritDoc}
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result
				+ ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (available != other.available)
			return false;
		if (genre != other.genre)
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	/**
	* {@inheritDoc}
	*/
	@SuppressWarnings("unchecked")
	@Override
	public Book clone() throws CloneNotSupportedException  {
		Book clone = (Book) super.clone();
		clone.authors = (ArrayList<Author>) authors.clone();
		clone.publisher = (Publisher) publisher.clone();
		return clone;
	}
	
}
