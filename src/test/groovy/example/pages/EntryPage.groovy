package example.pages;

import geb.Page

class EntryPage extends Page {

	static url = "/new"
	static at = { title == "New Greeting Message" }
	static content = {
		heading { $("h1").text() }
		
		errorMessages {$(".errors")*.text()}

		nameField {$("input[name=name]")}
		emailField {$("input[name=email]")}
		messageField {$("input[name=message]")}

		submitLink {$("#submit")}
	}
}