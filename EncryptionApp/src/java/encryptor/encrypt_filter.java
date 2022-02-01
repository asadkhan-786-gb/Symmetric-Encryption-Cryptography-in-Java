package encryptor;
import java.io.IOException;
import java.text.*;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class encrypt_filter implements javax.servlet.Filter {

 private static final boolean debug = true;
 private FilterConfig filterConfig = null;
 //Create a custom date and time
 private DateFormat dateFormat;
 private String newDate;

public encrypt_filter() {
 }

 /**
  * Init method for this filter use for initialize parameter
  */
 @Override
 public void init(FilterConfig filterConfig) {
  this.filterConfig = filterConfig;
  if (filterConfig != null) {
if (debug) {
 log("encrypt_filter:Initializing filter");
}
  }
  dateFormat = new SimpleDateFormat("dd/mm/yyyy");
  newDate = dateFormat.format(Calendar.getInstance().getTime());
 }

 @Override
 public void doFilter(ServletRequest request,
	  ServletResponse response, FilterChain chain)
throws IOException, ServletException {
  HttpServletRequest req = (HttpServletRequest) request;
  if (debug) {
log("encrypt_filter:doFilter()");
  }
  //write massage over mapping URL in xml file
  response.getWriter().write(req.getHeader("user-agent"));
  response.getWriter().write
	  ("<BR>Message send from filter through " + "<b><I>"
 + req.getRequestURL() + "</I></b>" +
	        " On " + "<b>" + newDate + "<b>");
  chain.doFilter(request, response);
 }

 /**
  * Return the filter configuration object for this filter.
  */
 public FilterConfig getFilterConfig() {
  return (this.filterConfig);
 }

 /**
  * Set the filter configuration object for this filter.
  */
 public void setFilterConfig(FilterConfig filterConfig) {
  this.filterConfig = filterConfig;
 }

 /**
  * Destroy method for this filter 
  */
 @Override
 public void destroy() {
 }
 /*
  * log method is used for print message over server console
  */

 public void log(String msg) {
  filterConfig.getServletContext().log(msg);
 }
}