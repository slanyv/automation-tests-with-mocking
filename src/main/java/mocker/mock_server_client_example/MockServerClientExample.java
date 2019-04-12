package mocker.mock_server_client_example;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.matchers.Times;

public class MockServerClientExample {

    private static final int SERVER_PORT = 8081;
    private static final String RESPONSES_DIRECTORY = "/src/main/resources/mock_server_responses/";
    private static MockServerClient mockServerClient;

    public static void main(String[] args) {
        MockServerClientExample.startMockServer();
    }

    public static void startMockServer() {
        mockServerClient = new MockServerClient("localhost", SERVER_PORT);
        spacecraftTestScenario();
    }

    private static void spacecraftTestScenario() {

        setListOfSpacecraftsResponses();
        setAvailableComponentResponses();
        setCreateSpacecraftResponses();
        setDeleteSpacecraftResponses();
    }


    private static void setListOfSpacecraftsResponses() {

        String baseUrl = "/pa165/rest/spacecrafts";
        mockServerClient.when(
                request()
                        .withMethod("GET")
                        .withPath(baseUrl),
                Times.exactly(1))
                .respond(response()
                        .withStatusCode(200)
                        .withBody(readFileAsString(RESPONSES_DIRECTORY + "ListOfSpacecrafts.json")));

        mockServerClient.when(
                request()
                        .withMethod("GET")
                        .withPath(baseUrl),
                Times.exactly(1))
                .respond(response()
                        .withStatusCode(200)
                        .withBody(readFileAsString(RESPONSES_DIRECTORY + "ListOfSpacecraftsAfterAdding.json")));

        mockServerClient.when(
                request()
                        .withMethod("GET")
                        .withPath(baseUrl),
                Times.exactly(1))
                .respond(response()
                        .withStatusCode(200)
                        .withBody(readFileAsString(RESPONSES_DIRECTORY + "ListOfSpacecrafts.json")));

    }

    public static String readFileAsString(String fileName) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void setCreateSpacecraftResponses() {

        String baseUrl = "/pa165/rest/spacecrafts";

    }

    private static void setAvailableComponentResponses() {

        String baseUrl = "/pa165/rest/craftComponents/available";
    }

    private static void setDeleteSpacecraftResponses() {

        String baseUrl = "/pa165/rest/spacecrafts/4";

    }
}
