package pericles.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pericles.coreservice.model.Candidate;
import pericles.ui.api.VoteClient;


@Controller
public class UIController {
	@Autowired
	VoteClient voteClient;
	
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	public String getCandidates(Model model) {
		Resources<Candidate> list = voteClient.getCandidates();
		model.addAttribute("list", list);
		return "lists";
	}

}
