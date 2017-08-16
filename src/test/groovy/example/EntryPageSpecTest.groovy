package example;

import java.rmi.activation.ActivationGroupDesc.CommandEnvironment

import org.openqa.selenium.interactions.ClickAction
import org.springframework.boot.test.context.SpringBootTest

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import spock.lang.Unroll

import example.pages.EntryPage
import example.pages.IndexPage

@Unroll
class EntryPageSpecTest extends BaseGebSpec {

	def "Entry new greeting message normally with #name"() {
		given: "At new greeting message screen"
		to EntryPage

		when: "Fill the form with normal data and click the submit link"
		nameField = name
		emailField = email
		messageField = message
		report "new greeting message screen"
		submitLink.click()

		then: "Greeting board! screen displayed"
		at IndexPage

		where:
		name		|email					|message
		'L'			|''						|'Hello, this is L'
		'なおとテスト'	|'a@e.co'				|'This is a test.'
		'A' * 10	|'B' * 51 + '@abcde.com'|'M' * 100
	}

	def "Form validation check"() {
		given: "At new greeting message screen"
		to EntryPage

		when: "Fill the form with invalid data and click the submit link"
		nameField = name
		emailField = email
		messageField = message
		submitLink.click()

		then: "Error messages displayed"
		at EntryPage
		errorMessages.sort() == errMessage.sort()

		where:
		name			|email					|message		||errMessage
		''				|''						|''				||['Name is empty!', 'Message is empty!']
		'A' * 11		|'B' * 51 + '@abcde.com'|'M' * 101		||['Name is too long, over 10 characters!', 'Email is invalid!', 'Message is too long, over 100 characters!']
		'A' * 10		|'@defghijk.com'		|'M' * 100		||['Email is invalid!']
		'A' * 10		|'abc@'					|'M' * 100		||['Email is invalid!']
		'A' * 10		|'a@e.c'				|'M' * 100		||['Email is invalid!']
	}
}