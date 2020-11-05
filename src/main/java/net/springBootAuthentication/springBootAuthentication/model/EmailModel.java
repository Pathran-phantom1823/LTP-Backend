package net.springBootAuthentication.springBootAuthentication.model;

import java.util.List;
import java.util.Map;

public class EmailModel {

   
    private String from;
    private String mailTo;
    private String subject;
    private Map<String, Object> props;

    public EmailModel() {}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Map<String, Object> getProps() {
		return props;
	}

	public void setProps(Map<String, Object> props) {
		this.props = props;
	}


}
