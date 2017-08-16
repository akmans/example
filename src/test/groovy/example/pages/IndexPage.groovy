package example.pages;

import geb.Page

class IndexPage extends Page {

	static url = "/"
	static at = { title == "Greeting Board!" }
	static content = {
		heading { $("h1").text() }

		newGreetingLink {$("#add")}

		greetings (required: false) {$(".greeting")}
		messages (required: false) {$(".message")}

		nomessage(required: false) {$("#nomessage").text()}
	}
}