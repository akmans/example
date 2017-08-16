package example.web.form;

import java.time.ZonedDateTime;

public abstract class AbstractSimpleForm {
	private ZonedDateTime createdDate;

	private ZonedDateTime updatedDate;

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public ZonedDateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setUpdatedDate(ZonedDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public ZonedDateTime getUpdatedDate() {
		return this.updatedDate;
	}

	public void reset() {
		this.createdDate = null;
		this.updatedDate = null;
	}

	@Override
	public String toString() {
		return "created_date=" + createdDate + ", updated_date=" + updatedDate;
	}
}
