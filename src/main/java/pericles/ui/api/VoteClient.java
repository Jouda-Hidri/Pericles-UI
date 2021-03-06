package pericles.ui.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pericles.coreservice.domain.Candidate;
import pericles.coreservice.domain.dto.CandidateDto;

@FeignClient(name = "gateway-server")
@RibbonClient(name = "core-service")
public interface VoteClient {

	@RequestMapping(method = RequestMethod.GET, path = "/core-service/zone-1/candidates")
	Resources<Candidate> getCandidates();

	@RequestMapping(method = RequestMethod.GET, path = "/core-service/zone-1/candidates/{id}")
	Resource<Candidate> getCandidate(@PathVariable("id") long id);

	@RequestMapping(value = "/core-service/zone-1/vote/{voter}/for/{candidate}", method = RequestMethod.GET)
	public void vote(@PathVariable("voter") long voterId, @PathVariable("candidate") long candidateId);
	
	@RequestMapping(value = "/core-service/zone-1/result", method = RequestMethod.GET)
	public List<CandidateDto> getResult();

}
