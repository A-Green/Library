package by.epam.green.library.model;

import java.io.Serializable;

/**
 * The super class for all model objects.
 */
public abstract class Entity implements Cloneable, Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private int id;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	

	/**
	* {@inheritDoc}
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	

	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	* {@inheritDoc}
	*/
	@Override 
	public Entity clone() throws CloneNotSupportedException {
		return (Entity) super.clone();
	}

}
