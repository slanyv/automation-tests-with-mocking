package mocker.wiremock_server_example;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.http.HttpStatus;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.github.tomakehurst.wiremock.stubbing.Scenario;

import core.constants.GeneralContants;
import tools.Log;

public class WireMockServerExample {


    private static final int SERVER_PORT = 8081;
    private static WireMockServer wireMockServer = new WireMockServer(options().port(SERVER_PORT).usingFilesUnderDirectory("./src/main/java/tools/mocker/wiremock_server_example"));
    private static HttpHeader CORS_HEADER = new HttpHeader("Access-Control-Allow-Origin", "*"); // cross origin resource sharing set to all
    private static final HttpHeaders DEFAULT_HEADERS = new HttpHeaders(CORS_HEADER);

    public static void main(String[] args) {
        startMockServer();
    }

    public static void startMockServer() {

        wireMockServer.start();
        Log.info("\n---STARTING MOCK SERVER---");
        wireMockServer.stubFor(any(urlMatching("/.*"))
                .willReturn(aResponse().proxiedFrom(GeneralContants.HOME_PAGE_URL)));
        courseScenario(); // TODO maybe delete this
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


    private static void courseScenario() {
        String scenarioName = "CreateCourse";
        String[] scenario_states = new String[]{Scenario.STARTED, "CourseCreated", "CourseDeleted"};

        wireMockServer.stubFor(get(urlMatching(".*\\course\\/list")).inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[0])
                .willReturn((aResponse()
                        .withHeaders(DEFAULT_HEADERS)
                        .withBodyFile("responses/ListOfDefaultCoursesResponse.html")
                        .withStatus(HttpStatus.SC_OK))));

        System.out.println("ahoj");
/*
        wireMockServer.stubFor(post(urlMatching(".*\\/audiences\\/custom"))
                .withRequestBody(containing("Selenium audience test - emails"))
                .inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[0])
                .willReturn((aResponse()
                        .withHeaders(DEFAULT_HEADERS)
                        .withBody("{'status':'created','id':90000}")
                        .withStatus(HttpStatus.SC_CREATED)))
                .willSetStateTo(scenario_states[1]));

        wireMockServer.stubFor(put(urlMatching(".*\\/audiences/custom/.*")).inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[1])
                .willReturn(aResponse()
                        .withHeaders(DEFAULT_HEADERS)
                        .withStatus(HttpStatus.SC_NO_CONTENT))
                .willSetStateTo(scenario_states[2]));

        wireMockServer.stubFor(get(urlMatching(".*\\/targeting-audiences\\?.*")).inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[2])
                .willReturn(aResponse()
                        .withHeaders(DEFAULT_HEADERS)
                        .withBodyFile("json_responses/emailAudienceEdite")
                        .withStatus(HttpStatus.SC_OK)));

        wireMockServer.stubFor(delete(urlMatching(".*\\/targeting\\/audiences\\/.*")).inScenario(scenarioName)
                .whenScenarioStateIs(scenario_states[2])
                .willReturn(aResponse()
                        .withHeaders(DEFAULT_HEADERS)
                        .withBodyFile("json_responses/emailAudienceDelete")
                        .withStatus(HttpStatus.SC_OK))
                .willSetStateTo(scenario_states[0]));
        */
    }


}
