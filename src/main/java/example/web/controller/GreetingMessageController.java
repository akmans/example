package example.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import example.service.GreetingMessageService;
import example.springdata.jpa.entities.GreetingMessage;
import example.web.form.GreetingMessageForm;

@Controller
@SessionAttributes("greetingMessageForm")
public class GreetingMessageController {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GreetingMessageController.class);

	@Autowired
	private GreetingMessageService greetingMessageService;

	@RequestMapping(path = { "/" })
	String index(ModelMap model) {
		List<GreetingMessage> messages = greetingMessageService.findAll();
		model.addAttribute("allMessages", messages);
		return "index";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	String initNew(ModelMap model, GreetingMessageForm greetingMessageForm) {
		// reset form.
		greetingMessageForm.reset();

		// render path
		return "entry";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	String submit(ModelMap model,
			@Valid @ModelAttribute("greetingMessageForm") final GreetingMessageForm greetingMessageForm,
			BindingResult bindingResult) {
		logger.debug("The greetingMessageForm = {}", greetingMessageForm);
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				logger.debug("ERRORS" + error.getDefaultMessage());
			}
			// Errors
			model.addAttribute("cssStyle", "alert-danger");

			// render path
			return "entry";
		} else {
			GreetingMessage greetingMessage = new GreetingMessage();
			BeanUtils.copyProperties(greetingMessageForm, greetingMessage);
			greetingMessageService.add(greetingMessage);

			List<GreetingMessage> allMessages = greetingMessageService.findAll();
			model.addAttribute("allMessages", allMessages);

			// render path
			return "index";
		}
	}
}
