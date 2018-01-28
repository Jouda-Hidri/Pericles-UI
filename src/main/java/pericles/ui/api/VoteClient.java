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


@FeignClient(name="gateway-server")
//@FeignClient(url = "http://localhost:8098")
//@FeignClient(name="core-service")

//@FeignClient(value = "candidates", path = "/candidates")
@RibbonClient(name="core-service")
public interface VoteClient {
	
    @RequestMapping(value = "/core-service/listall", method = RequestMethod.GET)
    public List<String> listAll();

    
    @RequestMapping(method = RequestMethod.GET, path = "/core-service/candidates")
    Resources<Candidate> getCandidates();
    
    @RequestMapping(method = RequestMethod.GET, path = "/core-service/candidates/{id}")
    Resource<Candidate> getCandidate(@PathVariable("id") long id);

}
