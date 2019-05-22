package mocker.wiremock_server_example;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.apache.http.HttpStatus;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.Scenario;

import core.constants.GeneralContants;
import core.constants.HttpHeadersConstants;

public class WireMockServerExample {

    private static final int SERVER_PORT = 8081;
    private static WireMockServer wireMockServer = new WireMockServer(options().port(SERVER_PORT).usingFilesUnderDirectory("./src/main/resources/mock_server_responses/"));

    private static final String scenarioName = "CreateSpacecraft";
    private static final String[] scenario_states = new String[]{Scenario.STARTED, "CreateSpacecraft", "DeleteSpacecraft"};

    public static void main(String[] args) {
        WireMockServerExample.startMockServer();
    }

    public static void startMockServer() {

        wireMockServer.resetAll();
        wireMockServer.stubFor(any(urlMatching("/.*"))
                .willReturn(aResponse().proxiedFrom(GeneralContants.HOME_PAGE_URL)));
        wireMockServer.start();

        spacecraftTestScenario();
        System.out.println("\n--- MOCK SERVER STARTED ON PORT: " + WireMockServerExample.wireMockServer.port() + " ---");
    }

    public static boolean isServerRunning() {
        return wireMockServer.isRunning();
    }

    public static void stopMockServer() {

        System.out.println("--- STOPPING MOCK SERVER ---");
        wireMockServer.stop();
    }

    private static void spacecraftTestScenario() {

        setListOfSpacecraftsResponses();
        setAvailableComponentResponses();
        setCreateSpacecraftResponses();
        setDeleteSpacecraftResponses();
    }

    private static void setListOfSpacecraftsResponses() {

        String baseUrl = "/pa165/rest/spacecrafts";

        wireMockServer.stubFor(WireMock.options(urlMatching(baseUrl))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.GET_OPTION_HEADERS)
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching(baseUrl))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[0])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("ListOfSpacecrafts.json")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching(baseUrl))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[1])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("ListOfSpacecraftsAfterAdding.json")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(WireMock.options(urlMatching(baseUrl + "/4"))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.GET_OPTION_HEADERS)
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching(baseUrl + "/4"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[1])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("SpacecraftDetails.json")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching(baseUrl + "/4"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[2])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("SpacecraftDetails.json")
                        .withStatus(HttpStatus.SC_OK))));
    }

    private static void setCreateSpacecraftResponses() {

        String baseUrl = "/pa165/rest/spacecrafts";

        wireMockServer.stubFor(WireMock.options(urlMatching(baseUrl))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.POST_OPTION_HEADERS)
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(post(urlMatching(baseUrl))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[0])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("SpacecraftDetails.json")
                        .withStatus(HttpStatus.SC_OK)))
                .willSetStateTo(scenario_states[1]));
    }

    private static void setAvailableComponentResponses() {

        String baseUrl = "/pa165/rest/craftComponents/available";

        wireMockServer.stubFor(WireMock.options(urlMatching(baseUrl))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.GET_OPTION_HEADERS)
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching(baseUrl))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("ListOfAvailableCraftComponents.json")
                        .withStatus(HttpStatus.SC_OK))));
    }

    private static void setDeleteSpacecraftResponses() {

        String baseUrl = "/pa165/rest/spacecrafts/4";

        wireMockServer.stubFor(WireMock.options(urlMatching(baseUrl))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.DELETE_OPTION_HEADERS)
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(delete(urlMatching(baseUrl))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[1])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("ListOfSpacecrafts.json")
                        .withStatus(HttpStatus.SC_OK)))
                .willSetStateTo(scenario_states[2]));
    }
}
