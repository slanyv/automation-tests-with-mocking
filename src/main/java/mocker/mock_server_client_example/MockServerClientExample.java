package mocker.mock_server_client_example;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.mockserver.model.Header;

import tools.FileReader;

public class MockServerClientExample {

    private static final int SERVER_PORT = 8081;
    private static final String RELATIVE_PATH = "\\src\\main\\resources\\mock_server_responses\\";
    private static MockServerClient mockServerClient;
    private ClientAndServer mockServer;

    public static void main(String[] args) {
        MockServerClientExample.startMockServer();
    }

    public static void startMockServer() {

        mockServerClient = new MockServerClient("localhost", SERVER_PORT).reset();


        MockServerClientExample asd = new MockServerClientExample();
        asd.setup();
        //spacecraftTestScenario();
        System.out.println("asd");
        mockServerClient.stop();
    }

    @Test
    public void setup() {

        List<Header> defaultHeader = new ArrayList<>();
        defaultHeader.add(Header.header("Content-Type", "application/json; charset=UTF-8"));
        defaultHeader.add(Header.header("Authorization", "Basic YWRtaW5AYWRtaW4uY29tOnBhc3N3b3Jk"));
        defaultHeader.add(Header.header("Access-Control-Allow-Origin", "*"));
        defaultHeader.add(Header.header("Pragma", "no-cache"));
        defaultHeader.add(Header.header("Transfer-Encoding", "chunked"));

        List<Header> optionsGetHeaders = new ArrayList<>(defaultHeader);
        optionsGetHeaders.add(Header.header("Access-Control-Allow-Methods", "GET"));
        optionsGetHeaders.add(Header.header("Access-Control-Allow-Headers", "authorization,x-requested-with"));
        optionsGetHeaders.add(Header.header("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate"));


        List<Header> headers = new ArrayList<>(defaultHeader);
        headers.add(Header.header("Access-Control-Allow-Headers", "accept, content-type"));
        headers.add(Header.header("Access-Control-Allow-Headers", "authorization,x-requested-with"));
        headers.add(Header.header("Access-Control-Allow-Methods", "GET"));
        headers.add(Header.header("Cache-Control", "no-cache, no-store"));
        headers.add(Header.header("Connection", "keep-alive"));

        String baseUrl = "/pa165/rest/spacecrafts";
        mockServer = startClientAndServer(8081);
        mockServer
                .when(
                        request()
                                .withMethod("OPTIONS")
                                .withHeaders(optionsGetHeaders)
                                .withPath(baseUrl)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                );

        mockServer
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(baseUrl)
                )
                .respond(
                        response()
                                .withHeaders(headers)
                                .withStatusCode(200)
                                .withBody("ahoj")
                );

        System.out.println("asd");
    }

    public static boolean isServerRunning() {
        return true; //TODO
    }

    public static void stopMockServer() {

        System.out.println("---STOPPING MOCK SERVER---");
        mockServerClient.stop();
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
                        .withBody(""));
        //.withBody(FileReader.readFileAsString(RELATIVE_PATH + "ListOfSpacecrafts.json")));

        mockServerClient.when(
                request()
                        .withMethod("GET")
                        .withPath(baseUrl),
                Times.exactly(1))
                .respond(response()
                        .withStatusCode(200)
                        .withBody(FileReader.readFileAsString(RELATIVE_PATH + "ListOfSpacecraftsAfterAdding.json")));

        mockServerClient.when(
                request()
                        .withMethod("GET")
                        .withPath(baseUrl),
                Times.exactly(1))
                .respond(response()
                        .withStatusCode(200)
                        .withBody(FileReader.readFileAsString(RELATIVE_PATH + "ListOfSpacecrafts.json")));

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
