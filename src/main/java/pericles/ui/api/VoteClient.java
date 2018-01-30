package pericles.ui.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pericles.coreservice.model.Candidate;

@FeignClient(name = "gateway-server")
@RibbonClient(name = "core-service")
public interface VoteClient {

	@RequestMapping(method = RequestMethod.GET, path = "/core-service/candidates")
	Resources<Candidate> getCandidates();

	@RequestMapping(method = RequestMethod.GET, path = "/core-service/candidates/{id}")
	Resource<Candidate> getCandidate(@PathVariable("id") long id);

	@RequestMapping(value = "/core-service/vote/{voter}/for/{candidate}", method = RequestMethod.GET)
	public void vote(@PathVariable("voter") long voterId, @PathVariable("candidate") long candidateId);
	
	@RequestMapping(value = "/core-service/result", method = RequestMethod.GET)
	public List<String> getResult();

}
