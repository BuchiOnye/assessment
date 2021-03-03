package com.renmoney.assessment;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {AssessmentApplication.class})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Slf4j
class AssessmentApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	
	@Before
    public void setup() {
		
    }

    private String obtainAccessToken(String username, String password) throws Exception {
        String token = null;
        ResultActions result = null;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", username);
        params.add("password", password);
        params.add("scope", "read write");
        

        try {
            result
                    = mockMvc.perform(post("/oauth/token")
                            .params(params)
                            .with(httpBasic("clientId", "secret"))
                            .accept("application/json;charset=UTF-8"))
                            .andExpect(content().contentType("application/json;charset=UTF-8"));
        } catch (Exception exception) {
          log.error(exception.getMessage());
        }

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();

        Object obj = jsonParser.parseMap(resultString).get("access_token");
        if (obj != null) {
            token = obj.toString();
        }
        return token;
    }

    @Test
    public void getTokenWithValidCredential() throws Exception {
        final String accessToken = obtainAccessToken("user", "pass");
        Assert.assertNotNull(accessToken);

    }

    @Test
    public void getTokenWithInValidCredential() throws Exception {
        final String accessToken = obtainAccessToken("admiwn", "password");
        Assert.assertNull(accessToken);

    }


}
