package jm_social_project.profile_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.model.VisitedProfiles;
import jm_social_project.profile_service.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Date;
import java.util.LinkedHashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
@AutoConfigureRestDocs

public class ProfileRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfileRestController controller;

    @Autowired
    private ProfileRepository repository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }


    @Test
    void getAllProfiles() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/profiles")
                .header("user_id", "1");

        this.mockMvc.perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}", preprocessResponse(prettyPrint())));
    }


    @Test
    void getProfileByAccountId() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/profiles/{accountId}", 2)
                .header("user_id", "1");

        this.mockMvc
                .perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}", preprocessResponse(prettyPrint())));
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
                .andDo(document("{methodName}", preprocessResponse(prettyPrint())));
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
                .andDo(document("{methodName}", preprocessResponse(prettyPrint())));
    }


    @Test
    void deleteProfile() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/profiles/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .header("user_id", "1");

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}", preprocessResponse(prettyPrint())));
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
                .andDo(document("{methodName}", preprocessResponse(prettyPrint())));
    }


    @Test
    void addToLikeOrDodgeList() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/profiles/{accountId}/{isLiked}", 2, true)
                .header("user_id", "1");

        this.mockMvc.perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}", preprocessResponse(prettyPrint())));
    }
}