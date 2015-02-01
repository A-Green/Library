package by.epam.green.library.commands;

/**
 * The Enum Commands.
 */
public enum Commands {
	
	/** The login. */
	LOGIN {
		public Command getCommand() {
			return new LoginCommand();
		}	
	},
	
	/** The logout. */
	LOGOUT {
		public Command getCommand() {
			return new LogoutCommand();
		}
	},
	
	/** The catalog. */
	CATALOG {
		public Command getCommand() {
			return new CatalogCommand();
		}
	},
	
	/** The orders. */
	ORDERS {
		public Command getCommand() {
			return new ShowOrdersCommand();
		}
	},
	
	/** The add book. */
	ADD_BOOK {
		public Command getCommand() {
			return new AddBookCommand();
		}
	},
	
	/** The order book. */
	ORDER_BOOK {
		public Command getCommand() {
			return new OrderBookCommand();
		}
	},
	
	/** The search books. */
	SEARCH_BOOKS {
		public Command getCommand() {
			return new SearchBooksCommand();
		}
	},
	
	/** The clients. */
	CLIENTS {
		public Command getCommand() {
			return new ShowClientsCommand();
		}
	},

	/** The registration. */
	REGISTRATION {
		public Command getCommand() {
			return new RegistrationCommand();
		}
	},

	/** The delete books. */
	DELETE_BOOKS {
		public Command getCommand() {
			return new DeleteBookCommand();
		}
	},

	/** The grant books. */
	GRANT_BOOKS {
		public Command getCommand() {
			return new GrantBooksCommand();
		}
	},

	/** The return books. */
	RETURN_BOOKS {
		public Command getCommand() {
			return new ReturnBooksCommand();
		}
	},

	/** The reject order. */
	REJECT_ORDER {
		public Command getCommand() {
			return new RejectOrderCommand();
		}
	};
	
	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	public abstract Command getCommand();
}
