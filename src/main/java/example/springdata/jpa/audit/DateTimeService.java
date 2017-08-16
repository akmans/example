package example.springdata.jpa.audit;

import java.time.ZonedDateTime;

public interface DateTimeService {
	ZonedDateTime getCurrentDateAndTime();
}
