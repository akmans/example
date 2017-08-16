package example.springdata.jpa.audit;

import java.time.ZonedDateTime;

public class CurrentTimeDateTimeService implements DateTimeService {
	public ZonedDateTime getCurrentDateAndTime() {
		return ZonedDateTime.now();
	}
}
