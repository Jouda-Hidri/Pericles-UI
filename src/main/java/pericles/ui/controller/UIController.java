package pericles.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pericles.coreservice.model.Candidate;
import pericles.ui.api.VoteClient;


@Controller
public class UIController {
	@Autowired
	VoteClient voteClient;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getCandidates(Model model) {
		Resources<Candidate> list = voteClient.getCandidates();
		model.addAttribute("list", list.getContent());
		return "list";
	}
	
	@RequestMapping(value = "/vote/{candidate}", method = RequestMethod.GET)
	public String vote(Model model, @PathVariable("candidate") long candidateId) {
		model.addAttribute("id", candidateId);
		voteClient.vote(1, candidateId);
		return "vote";
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String getResults(Model model) {
		List<String> listResults = voteClient.getResult();
		model.addAttribute("list", listResults);
		return "result";
	}

}
