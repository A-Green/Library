package by.epam.green.library.model;

/**
 * The Class Client represents library user
 */
public class Client extends Entity {

	private static final long serialVersionUID = 1L;

	/** The name. */
	private String name;
	
	/** The login. */
	private String login;
	
	/** The pass. */
	private long pass;
	
	/** The role. */
	private ClientType role;
	
	/** Client contact info. */
	private ContactInfo contactInfo = new ContactInfo();
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name client name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Sets the login.
	 *
	 * @param login client login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Gets the pass.
	 *
	 * @return the pass
	 */
	public long getPass() {
		return pass;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param pass  password.
	 */
	public void setPass(long pass) {
		this.pass = pass;
	}
	
	/**
	 * Gets the role.
	 *
	 * @return the client role
	 */
	public ClientType getRole() {
		return role;
	}
	
	/**
	 * Sets the role.
	 *
	 * @param role client role
	 */
	public void setRole(ClientType role) {
		this.role = role;
	}
	
	/**
	 * Gets client contact info.
	 *
	 * @return the contact info
	 */
	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	/**
	 * Sets the contact info.
	 *
	 * @param contactInfo the new contact info
	 */
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	* {@inheritDoc}
	*/
	@Override 
	public String toString() {
		
		StringBuilder  srtBuilder = new StringBuilder();
		srtBuilder.append("Id = ").append(getId())
		.append(" name: ").append(name)
		.append(" login: ").append(login)
		.append(" role: ").append(role);
		
		return srtBuilder.toString();
	}
	

	/**
	* {@inheritDoc}
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((contactInfo == null) ? 0 : contactInfo.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (pass ^ (pass >>> 32));
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Client other = (Client) obj;
		if (contactInfo == null) {
			if (other.contactInfo != null)
				return false;
		} else if (!contactInfo.equals(other.contactInfo))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pass != other.pass)
			return false;
		if (role != other.role)
			return false;
		return true;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public Client clone() throws CloneNotSupportedException {
		Client clone = (Client) super.clone();
		clone.contactInfo = (ContactInfo) contactInfo.clone();
		return clone;
	}

}
