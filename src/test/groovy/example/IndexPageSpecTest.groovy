package example;

import org.springframework.boot.test.context.SpringBootTest

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import spock.lang.Unroll

import example.pages.EntryPage
import example.pages.IndexPage

@Unroll
class IndexPageSpecTest extends BaseGebSpec {

	def "At top page with no greeting message"() {
		when: "At greeting board"
		to IndexPage

		then: "No greeting message"
		nomessage == "Ooh, no message!"
	}

	def "click on new greeting message link"() {
		when: "At greeting board"
		to IndexPage

		and: "Click new greeting message link"
		newGreetingLink.click()
		
		then: "New greeting message screen displayed"
		at EntryPage
	}
}