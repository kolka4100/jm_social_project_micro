package jm_social_project.profile_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.model.VisitedProfiles;
import jm_social_project.profile_service.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.LinkedHashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class ProfileRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfileRestController controller;

    @Autowired
    private ProfileRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void getAllProfiles() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/profiles")
                .header("user_id", "1");
        this.mockMvc.perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getAllProfiles"));
    }

    @Test
    void getProfileByAccountId() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/profiles/{accountId}", 2)
                .header("user_id", "1");
        this.mockMvc
                .perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getProfileByAccountId"));
    }

    @Test
    void createProfile() throws Exception {
        Profile profile = new Profile("8", "8", "Adam8", "Smith8", "free", "https://", new Date(89, 2, 21), "cool boy", 45.032689,38.984449,new LinkedHashSet<VisitedProfiles>());
        String data = objectMapper.writeValueAsString(profile);

        MockHttpServletRequestBuilder requestBuilder = post("/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .header("user_id", "1")
                .content(data);

        this.mockMvc.perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("createProfile"));
    }

    @Test
    void updateProfile() throws Exception {
        Profile profile = repository.getProfileByAccountId("2");
        profile.setFirstName("Adam20");
        profile.setLastName("Smith20");
        String data = objectMapper.writeValueAsString(profile);

        MockHttpServletRequestBuilder requestBuilder = put("/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .header("user_id", "1")
                .content(data)
                .characterEncoding("utf-8");
        this.mockMvc.perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("updateProfile"));
    }

    @Test
    void deleteProfile() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/profiles/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .header("user_id", "1");
        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("deleteProfile"));
    }


    @Test
    void getNearbyProfiles() throws Exception {
        Profile profile = repository.getProfileByAccountId("2");
        String data = objectMapper.writeValueAsString(profile);

        MockHttpServletRequestBuilder requestBuilder = get("/profiles/nearby-profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .header("user_id", "1")
                .content(data);
        this.mockMvc.perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getNearbyProfiles"));
    }

    @Test
    void addToLikeOrDodgeList() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/profiles/{accountId}/{isLiked}", 2, true)
                .header("user_id", "1");
        this.mockMvc.perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("addToLikeOrDodgeList"));
    }
}