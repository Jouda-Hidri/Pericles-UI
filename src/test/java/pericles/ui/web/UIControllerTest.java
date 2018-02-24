package pericles.ui.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UIControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private UIController controller;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * Test that the GET request to "/list-candidates"
	 * {@link pericles.ui.web.UIController#getListCandidates()} returns the correct
	 * status and the correct view
	 */
	@Test
	public void testGetListCandidates() throws Exception {
		this.mockMvc.perform(get("/list-candidates")).andExpect(status().isOk()).andExpect(view().name("candidates"))
				.andDo(print());
	}

	/**
	 * Test that the GET request to "/vote/{candidate}"
	 * {@link pericles.ui.web.UIController#vote(long)} returns the correct status
	 * and the correct view
	 */
	@Test
	public void testVote() throws Exception {
		int candidate = 1;
		// TODO test when candidate doesn't exist in database
		this.mockMvc.perform(get("/vote/{candidate}", candidate)).andExpect(status().isOk())
				.andExpect(view().name("vote")).andDo(print());
	}

	/**
	 * Test that the GET request to "/list-results"
	 * {@link pericles.ui.web.UIController#getListResults()} returns the correct
	 * status and the correct view
	 */
	@Test
	public void testGetListResults() throws Exception {
		this.mockMvc.perform(get("/list-results")).andExpect(status().isOk()).andExpect(view().name("results"))
				.andDo(print());
	}

}
