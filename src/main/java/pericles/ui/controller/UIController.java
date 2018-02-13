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
	
	@RequestMapping(value = "/list-candidates", method = RequestMethod.GET)
	public String getListCandidates(Model model) {
		Resources<Candidate> listCandidates = voteClient.getCandidates();
		model.addAttribute("listCandidates", listCandidates.getContent());
		return "list-candidates";
	}
	
	@RequestMapping(value = "/vote/{candidate}", method = RequestMethod.GET)
	public String vote(Model model, @PathVariable("candidate") long candidateId) {
		long userId = 1; //TODO get online user
		voteClient.vote(userId, candidateId);
		return "vote";
	}
	
	@RequestMapping(value = "/list-results", method = RequestMethod.GET)
	public String getListResults(Model model) {
		List<String> listResults = voteClient.getResult();
		model.addAttribute("listResults", listResults);
		return "list-results";
	}
}
