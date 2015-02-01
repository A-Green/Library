package by.epam.green.library.commands;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.Registration;
import by.epam.green.library.logic.exception.LogicException;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.logic.validators.EmailValidator;
import by.epam.green.library.logic.validators.PhoneValidator;
import by.epam.green.library.managers.ResourceManager;
import by.epam.green.library.model.Client;
import by.epam.green.library.model.ClientType;

/**
 * The Class RegistrationCommand.
 */
public class RegistrationCommand implements Command {
	
	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

	/**
	* {@inheritDoc}
	*/
	@Override
	public String execute(HttpServletRequest request) {

		Registration registration = new Registration();
			
		String locale = (String) request.getParameter("localeChooser");
		request.getSession().setAttribute("locale", locale);

		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String login = request.getParameter("login");
		String name = request.getParameter("name");

		if ("".equals(login) || "".equals(name) || "".equals(city)
				|| "".equals(country) || "".equals(street)) {

			request.setAttribute("empryFieldsWarn", true);
			return ResourceManager.getInstance().getParametr("registration_page");
		}

		String pass = request.getParameter("password");
		String repass = request.getParameter("repass");

		if (!pass.equals(repass) || pass.isEmpty()) {
			request.setAttribute("passWarn", true);
			return ResourceManager.getInstance().getParametr("registration_page");
		}

		String phone = request.getParameter("phone");

		if (!PhoneValidator.validatePhoneNumber(phone)) {
			request.setAttribute("phoneWarn", true);
			return ResourceManager.getInstance().getParametr("registration_page");
		}
		
		String email = request.getParameter("email");
		
		if (!EmailValidator.validateEmail(email)) {
			request.setAttribute("emailWarn", true);
			return ResourceManager.getInstance().getParametr("registration_page");
		}

		//All fields are valid, create a new client
		Client newClient = new Client();
		newClient.setName(name);
		newClient.setPass(pass.hashCode());
		newClient.setLogin(login);
		newClient.setRole(ClientType.READER);
		newClient.getContactInfo().setCity(city);
		newClient.getContactInfo().setCountry(country);
		newClient.getContactInfo().setStreet(street);
		newClient.getContactInfo().setPhone(phone);
		newClient.getContactInfo().setEmail(email);
		
		try {
			registration.registration(newClient);
		} catch (LogicException e) {
			request.setAttribute("loginWarn", true);
			return ResourceManager.getInstance().getParametr("registration_page");
		} catch (TechnicalException e) {
			LOGGER.error("Can not register client", e);
			return ResourceManager.getInstance().getParametr("error_page");
		}

		request.setAttribute("successRegistr", true);
		return ResourceManager.getInstance().getParametr("autorization_page");
	}

}
