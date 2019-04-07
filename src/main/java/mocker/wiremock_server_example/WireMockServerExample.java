package mocker.wiremock_server_example;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.http.HttpStatus;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.Scenario;

import tools.Log;

public class WireMockServerExample {


    private static final int SERVER_PORT = 8081;
    private static WireMockServer wireMockServer = new WireMockServer(options().port(SERVER_PORT).usingFilesUnderDirectory("./src/main/java/mocker/wiremock_server_example/responses/"));

    private static final String scenarioName = "CreateSpacecraft";
    private static final String[] scenario_states = new String[]{Scenario.STARTED, "CreateSpacecraft", "DeleteSpacecraft"};

    public static void main(String[] args) {
        WireMockServerExample wireMockServerExample = new WireMockServerExample();
        wireMockServerExample.startMockServer();
    }

    public void startMockServer() {

        wireMockServer.start();
        Log.info("\n---STARTING MOCK SERVER---");
        System.out.println("\n---STARTING MOCK SERVER---");
        /*
        wireMockServer.stubFor(any(urlMatching("/.*"))
                .willReturn(aResponse().proxiedFrom(GeneralContants.HOME_PAGE_URL)));
        */
        spacecraftTestScenario();
        // TODO maybe delete this
        try {
            InetAddress IP = InetAddress.getLocalHost();
            System.out.println(IP.getHostAddress());
            System.out.println(wireMockServer.port());
        } catch (UnknownHostException ex) {
            Log.error("Unknown IP of host");
        }
    }

    public boolean isServerRunning() {
        return wireMockServer.isRunning();
    }

    public static void stopMockServer() {

        Log.info("---STOPPING MOCK SERVER---");
        wireMockServer.stop();
    }

    private static void spacecraftTestScenario() {

        setListOfSpacecraftsResponses();
        setAvailableComponentResponses();
        setCreateSpacecraftResponses();
        setDeleteSpacecraftResponses();
    }

    private static void setListOfSpacecraftsResponses() {

        wireMockServer.stubFor(WireMock.options(urlMatching("/pa165/rest/spacecrafts"))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.GET_OPTION_HEADERS)
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching("/pa165/rest/spacecrafts"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[0])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("ListOfSpacecrafts.json")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching("/pa165/rest/spacecrafts"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[1])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("ListOfSpacecraftsAfterAdding.json")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(WireMock.options(urlMatching("/pa165/rest/spacecrafts/4"))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.GET_OPTION_HEADERS)
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching("/pa165/rest/spacecrafts/4"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[1])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("SpacecraftDetails.json")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching("/pa165/rest/spacecrafts/4"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[2])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("SpacecraftDetails.json")
                        .withStatus(HttpStatus.SC_OK))));
    }

    private static void setCreateSpacecraftResponses() {

        wireMockServer.stubFor(WireMock.options(urlMatching("/pa165/rest/spacecrafts"))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.POST_OPTION_HEADERS)
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(post(urlMatching("/pa165/rest/spacecrafts"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[0])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("SpacecraftDetails.json")
                        .withStatus(HttpStatus.SC_OK)))
                .willSetStateTo(scenario_states[1]));
    }

    private static void setAvailableComponentResponses() {

        wireMockServer.stubFor(WireMock.options(urlMatching("/pa165/rest/craftComponents/available"))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.GET_OPTION_HEADERS)
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching("/pa165/rest/craftComponents/available"))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("ListOfAvailableCraftComponents.json")
                        .withStatus(HttpStatus.SC_OK))));
    }

    private static void setDeleteSpacecraftResponses() {

        wireMockServer.stubFor(WireMock.options(urlMatching("/pa165/rest/spacecrafts/4"))
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.DELETE_OPTION_HEADERS)
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(delete(urlMatching("/pa165/rest/spacecrafts/4"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[1])
                .willReturn((aResponse()
                        .withHeaders(HttpHeadersConstants.HEADERS)
                        .withBodyFile("ListOfSpacecrafts.json")
                        .withStatus(HttpStatus.SC_OK)))
                .willSetStateTo(scenario_states[2]));
    }
}
