package by.epam.green.library.model;

/**
 * The Class Author represents book author.
 */
public class Author extends Entity {

	private static final long serialVersionUID = 1L;

	/** The author name. */
	private String name;

	/**
	 * Gets the author name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name author name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public String toString() {
		return "Id = " + getId() + " name = " + name;
	}
	

	/**
	* {@inheritDoc}
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Author other = (Author) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public Author clone() throws CloneNotSupportedException {
		return (Author) super.clone();
	}

}
