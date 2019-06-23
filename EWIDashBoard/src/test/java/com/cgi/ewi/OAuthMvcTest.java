//package com.cgi.ewi;
//
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Map;
//import java.util.Set;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.json.JacksonJsonParser;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.OAuth2Request;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.RequestPostProcessor;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.context.WebApplicationContext;
//
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//@SpringBootTest(classes = EwiDashBoardApplication.class)
//@ActiveProfiles("mvc")
//public class OAuthMvcTest {
//
//	@Autowired
//	private WebApplicationContext wac;
//
//	@Autowired
//	private FilterChainProxy springSecurityFilterChain;
//
//	private MockMvc mockMvc;
//
//	private static final String CLIENT_ID = "fooClientIdPassword";
//	private static final String CLIENT_SECRET = "secret";
//
//	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
//
//	private static final String EMAIL = "jim@yahoo.com";
//	private static final String NAME = "Jim";
//
//	@Before
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
//	}
//
//	private String obtainAccessToken(String username, String password) throws Exception {
//		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "password");
//		params.add("client_id", CLIENT_ID);
//		params.add("username", username);
//		params.add("password", password);
//
//		// @formatter:off
//
//		ResultActions result = mockMvc
//				.perform(post("/oauth/token").params(params)
//						.with(new SecurityMockMvcRequestPostProcessors().httpBasic(CLIENT_ID, CLIENT_SECRET))
//						.accept(CONTENT_TYPE))
//				.andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE));
//
//		// @formatter:on
//
//		String resultString = result.andReturn().getResponse().getContentAsString();
//
//		JacksonJsonParser jsonParser = new JacksonJsonParser();
//		return jsonParser.parseMap(resultString).get("access_token").toString();
//	}
//
//	@Test
//	public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
//		mockMvc.perform(get("/employee").param("email", EMAIL)).andExpect(status().isUnauthorized());
//	}
//
//	@Test
//	public void givenInvalidRole_whenGetSecureRequest_thenForbidden() throws Exception {
//		final String accessToken = obtainAccessToken("user1", "pass");
//		System.out.println("token:" + accessToken);
//		mockMvc.perform(get("/employee").header("Authorization", "Bearer " + accessToken).param("email", EMAIL))
//				.andExpect(status().isForbidden());
//	}
//
//	@Test
//	public void givenToken_whenPostGetSecureRequest_thenOk() throws Exception {
//		final String accessToken = obtainAccessToken("admin", "nimda");
//
//		String employeeString = "{\"email\":\"" + EMAIL + "\",\"name\":\"" + NAME + "\",\"age\":30}";
//
//		// @formatter:off
//
//		mockMvc.perform(post("/employee").header("Authorization", "Bearer " + accessToken).contentType(CONTENT_TYPE)
//				.content(employeeString).accept(CONTENT_TYPE)).andExpect(status().isCreated());
//
//		mockMvc.perform(get("/employee").param("email", EMAIL).header("Authorization", "Bearer " + accessToken)
//				.accept(CONTENT_TYPE)).andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE))
//				.andExpect(jsonPath("$.name", is(NAME)));
//
//		// @formatter:on
//	}
//
//	public RequestPostProcessor bearerToken(final String clientid, final String username) {
//		return mockRequest -> {
//			OAuth2Authentication auth = oAuth2Authentication(clientid, username);
//			OAuth2AccessToken token = null;// tokenservice.createAccessToken(auth);
//			mockRequest.addHeader("Authorization", "Bearer " + token.getValue());
//			return mockRequest;
//		};
//	}
//
//	// For use with @WithOAuth2Authentication
//	public OAuth2Authentication oAuth2Authentication(final String clientId, final String username) {
//		// Look up authorities, resourceIds and scopes based on clientId
//		ClientDetails client = null;// clientDetailsService.loadClientByClientId(clientId);
//		Collection<GrantedAuthority> authorities = client.getAuthorities();
//		Set<String> resourceIds = client.getResourceIds();
//		Set<String> scopes = client.getScope();
//
//		// Default values for other parameters
//		Map<String, String> requestParameters = Collections.emptyMap();
//		boolean approved = true;
//		String redirectUrl = null;
//		Set<String> responseTypes = Collections.emptySet();
//		Map<String, Serializable> extensionProperties = Collections.emptyMap();
//
//		// Create request
//		OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId, authorities, approved, scopes,
//				resourceIds, redirectUrl, responseTypes, extensionProperties);
//
//		// Create OAuth2AccessToken
//		UserDetails user = null;// userDetailsService.loadUserByUsername(username);
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null,
//				authorities);
//		OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, authenticationToken);
//		return auth;
//	}
//}