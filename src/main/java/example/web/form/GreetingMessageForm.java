package example.web.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import example.util.ValidEmailAddress;

public class GreetingMessageForm extends AbstractSimpleForm implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id. */
	private Integer id;

	@Size(max = 10, message = "{name.too.long}")
	@NotEmpty(message = "{name.is.empty}")
	private String name;

	@Size(max = 60, message = "{email.is.invalid}")
	@ValidEmailAddress(message = "{email.is.invalid}")
	private String email;

	@Size(max = 100, message = "{message.too.long}")
	@NotEmpty(message = "{message.is.empty}")
	private String message;

	/**
	 * id getter.<BR>
	 * 
	 * @return id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * id setter.<BR>
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * name getter.<BR>
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * name setter.<BR>
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * email getter.<BR>
	 * 
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * email setter.<BR>
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public void reset() {
		super.reset();
		this.id = null;
		this.name = null;
		this.email = null;
		this.message = null;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", email=" + email + ", message=" + message + super.toString() + "]";
	}
}
