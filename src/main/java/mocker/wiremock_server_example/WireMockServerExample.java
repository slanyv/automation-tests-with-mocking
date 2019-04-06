package mocker.wiremock_server_example;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpStatus;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.github.tomakehurst.wiremock.stubbing.Scenario;

import tools.Log;

public class WireMockServerExample {


    private static final int SERVER_PORT = 8081;
    private static WireMockServer wireMockServer = new WireMockServer(options().port(SERVER_PORT).usingFilesUnderDirectory("./src/main/java/mocker/wiremock_server_example/responses/"));
    private static HttpHeader ACCESS_ORIGIN_HEADER = new HttpHeader("Access-Control-Allow-Origin", "*");
    private static HttpHeader ACCESS_HEADERS_HEADER = new HttpHeader("Access-Control-Allow-Headers", "accept, content-type");
    private static HttpHeader ACCESS_REQUEST_HEADER = new HttpHeader("Access-Control-Request-Headers", "authorization,x-requested-with");
    private static HttpHeader ACCESS_METHODS_HEADER = new HttpHeader("Access-Control-Allow-Methods", "GET");
    private static HttpHeader CONTENT_HEADER = new HttpHeader("Content-Type", "application/json; charset=UTF-8");
    private static HttpHeader AUTHORIZATION_HEADER = new HttpHeader("Authorization", "Basic YWRtaW5AYWRtaW4uY29tOnBhc3N3b3Jk");
    private static HttpHeader TRANSFER_HEADER = new HttpHeader("Transfer-Encoding", "chunked");
    private static HttpHeader CACHE_HEADER = new HttpHeader("Cache-Control", "no-cache, no-store");
    private static HttpHeader PRAGMA_HEADER = new HttpHeader("Pragma", "no-cache");
    private static HttpHeader CONNECTION_HEADER = new HttpHeader("Connection", "keep-alive");
    private static Set<HttpHeader> DEFAULT_HEADERS = new HashSet<HttpHeader>() {
        {
            add(ACCESS_ORIGIN_HEADER);
            add(ACCESS_HEADERS_HEADER);
            add(ACCESS_REQUEST_HEADER);
            add(ACCESS_METHODS_HEADER);
            add(CONTENT_HEADER);
            add(AUTHORIZATION_HEADER);
            add(TRANSFER_HEADER);
            add(CACHE_HEADER);
            add(PRAGMA_HEADER);
            add(CONNECTION_HEADER);
        }
    };
    private static final HttpHeaders HEADERS = new HttpHeaders(DEFAULT_HEADERS);

    private static final String scenarioName = "CreateSpacecraft";
    private static final String[] scenario_states = new String[]{Scenario.STARTED, "CreateSpacecraft", "DeleteSpacecraft"};

    public static void main(String[] args) {
        startMockServer();
    }

    public static void startMockServer() {

        wireMockServer.start();
        Log.info("\n---STARTING MOCK SERVER---");
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

    public static boolean isServerRunning() {
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
                        .withHeader("Content-Type", "application/json; charset=UTF-8")
                        .withHeader("Authorization", "Basic YWRtaW5AYWRtaW4uY29tOnBhc3N3b3Jk")
                        .withHeader("Access-Control-Allow-Origin", "*")
                        .withHeader("Access-Control-Allow-Methods", "GET")
                        .withHeader("Access-Control-Allow-Headers", "authorization,x-requested-with") //this is needed
                        .withHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate")
                        .withHeader("Pragma", "no-cache")
                        .withHeader("Transfer-Encoding", "chunked")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching("/pa165/rest/spacecrafts"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[0])
                .willReturn((aResponse()
                        .withHeaders(HEADERS)
                        .withBodyFile("ListOfSpacecrafts.json")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching("/pa165/rest/spacecrafts"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[1])
                .willReturn((aResponse()
                        .withHeaders(HEADERS)
                        .withBodyFile("ListOfSpacecraftsAfterAdding.json")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(WireMock.options(urlMatching("/pa165/rest/spacecrafts/4"))
                .willReturn((aResponse()
                        .withHeader("Content-Type", "application/json; charset=UTF-8")
                        .withHeader("Authorization", "Basic YWRtaW5AYWRtaW4uY29tOnBhc3N3b3Jk")
                        .withHeader("Access-Control-Allow-Origin", "*")
                        .withHeader("Access-Control-Allow-Methods", "GET")
                        .withHeader("Access-Control-Allow-Headers", "authorization,x-requested-with") //this is needed
                        .withHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate")
                        .withHeader("Pragma", "no-cache")
                        .withHeader("Transfer-Encoding", "chunked")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching("/pa165/rest/spacecrafts/4"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[2])
                .willReturn((aResponse()
                        .withHeaders(HEADERS)
                        .withBodyFile("SpacecraftDetails.json")
                        .withStatus(HttpStatus.SC_OK))));
    }

    private static void setCreateSpacecraftResponses() {

        wireMockServer.stubFor(WireMock.options(urlMatching("/pa165/rest/spacecrafts"))
                .willReturn((aResponse()
                        .withHeader("Content-Type", "application/json; charset=UTF-8")
                        .withHeader("Authorization", "Basic YWRtaW5AYWRtaW4uY29tOnBhc3N3b3Jk")
                        .withHeader("Access-Control-Allow-Origin", "*")
                        .withHeader("Access-Control-Allow-Methods", "POST")
                        .withHeader("Access-Control-Allow-Headers", "authorization,content-type,x-requested-with") //this is needed
                        .withHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate")
                        .withHeader("Pragma", "no-cache")
                        .withHeader("Transfer-Encoding", "chunked")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(post(urlMatching("/pa165/rest/spacecrafts"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[0])
                .willReturn((aResponse()
                        .withHeaders(HEADERS)
                        .withBodyFile("SpacecraftDetails.json")
                        .withStatus(HttpStatus.SC_OK)))
                .willSetStateTo(scenario_states[1]));
    }

    private static void setAvailableComponentResponses() {

        wireMockServer.stubFor(WireMock.options(urlMatching("/pa165/rest/craftComponents/available"))
                .willReturn((aResponse()
                        .withHeader("Content-Type", "application/json; charset=UTF-8")
                        .withHeader("Authorization", "Basic YWRtaW5AYWRtaW4uY29tOnBhc3N3b3Jk")
                        .withHeader("Access-Control-Allow-Origin", "*")
                        .withHeader("Access-Control-Allow-Methods", "GET")
                        .withHeader("Access-Control-Allow-Headers", "authorization,x-requested-with") //this is needed
                        .withHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate")
                        .withHeader("Pragma", "no-cache")
                        .withHeader("Transfer-Encoding", "chunked")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(get(urlMatching("/pa165/rest/craftComponents/available"))
                .willReturn((aResponse()
                        .withHeaders(HEADERS)
                        .withBodyFile("ListOfAvailableCraftComponents.json")
                        .withStatus(HttpStatus.SC_OK))));
    }

    private static void setDeleteSpacecraftResponses() {

        wireMockServer.stubFor(WireMock.options(urlMatching("/pa165/rest/spacecrafts/4"))
                .willReturn((aResponse()
                        .withHeader("Content-Type", "application/json; charset=UTF-8")
                        .withHeader("Authorization", "Basic YWRtaW5AYWRtaW4uY29tOnBhc3N3b3Jk")
                        .withHeader("Access-Control-Allow-Origin", "*")
                        .withHeader("Access-Control-Allow-Methods", "DELETE")
                        .withHeader("Access-Control-Allow-Headers", "authorization,x-requested-with") //this is needed
                        .withHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate")
                        .withHeader("Pragma", "no-cache")
                        .withHeader("Transfer-Encoding", "chunked")
                        .withStatus(HttpStatus.SC_OK))));

        wireMockServer.stubFor(delete(urlMatching("/pa165/rest/spacecrafts/4"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[1])
                .willReturn((aResponse()
                        .withHeaders(HEADERS)
                        .withBodyFile("ListOfSpacecrafts.json")
                        .withStatus(HttpStatus.SC_OK)))
                .willSetStateTo(scenario_states[2]));
    }
}
