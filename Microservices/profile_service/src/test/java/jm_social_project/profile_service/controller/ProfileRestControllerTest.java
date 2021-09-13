package jm_social_project.profile_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfileRestController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void getProfileByAccountId() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/profiles/2").header("user_id", "1");
        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }
}