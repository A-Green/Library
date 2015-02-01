package by.epam.green.library.model;

/**
 * The Class Publisher represents book publisher.
 */
public class Publisher extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The publisher name. */
	private String name;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets publisher name.
	 *
	 * @param name publisher name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public String toString() {
		return "Id = " + getId() + " name: " + name;
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
		Publisher other = (Publisher) obj;
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
	public Publisher clone() throws CloneNotSupportedException {
		return (Publisher) super.clone();
	}

}
