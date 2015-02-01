package by.epam.green.library.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * The Class EncodingFilter.
 */
public class EncodingFilter implements Filter {

	/**
	* {@inheritDoc}
	* 
	* sets UTF-8 character encoding to requests and response
	*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void destroy() {
		
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
