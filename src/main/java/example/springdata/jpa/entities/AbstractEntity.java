package example.springdata.jpa.entities;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import java.time.ZonedDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractEntity {

	@Column(name = "creation_date", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
	@CreatedDate
	private ZonedDateTime createdDate;

	@Column(name = "updated_date")
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
	@LastModifiedDate
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

	@Override
	public String toString() {
		return "creation_date=" + createdDate + ", updated_date=" + updatedDate;
	}
}
