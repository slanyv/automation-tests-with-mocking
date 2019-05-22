package mocker.mock_server_client_example;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.apache.http.HttpStatus;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;

import core.constants.HeadersConstants;
import core.enums.EMethods;
import tools.FileReader;

public class MockServerClientExample {

    private static final int SERVER_PORT = 8081;
    private static final String RELATIVE_PATH = "\\src\\main\\resources\\mock_server_responses\\__files\\";
    private static ClientAndServer mockServer;

    public static void main(String[] args) {
        MockServerClientExample.startMockServer();
    }

    public static void initialize() {
        mockServer = new ClientAndServer();
        mockServer.stop();
    }

    public static void startMockServer() {

        mockServer = startClientAndServer(SERVER_PORT);
        mockServer.reset();
        spacecraftTestScenario();
        System.out.println("--- MOCK SERVER STARTED ON PORT: " + mockServer.getLocalPort() + " ---");
    }

    public static boolean isServerRunning() {
        return mockServer.isRunning();
    }

    public static void stopMockServer() {

        System.out.println("--- STOPPING MOCK SERVER ---");
        mockServer.stop();
    }

    private static void spacecraftTestScenario() {

        setListOfSpacecraftsResponses();
        setAvailableComponentResponses();
    }


    private static void setListOfSpacecraftsResponses() {

        String baseUrl = "/pa165/rest/spacecrafts";

        //GET spacecrafts
        mockServer
                .when(request()
                        .withPath(baseUrl)
                        .withMethod(EMethods.OPTIONS.name()),
                        Times.exactly(1)
                )
                .respond(response()
                        .withHeaders(HeadersConstants.optionsGetHeaders(EMethods.GET))
                        .withStatusCode(HttpStatus.SC_OK)
                );

        mockServer
                .when(request()
                                .withMethod(EMethods.GET.name())
                                .withPath(baseUrl),
                        Times.exactly(1)
                )
                .respond(response()
                        .withHeaders(HeadersConstants.defaultHeader())
                        .withStatusCode(HttpStatus.SC_OK)
                        .withBody(FileReader.readFileAsString(RELATIVE_PATH + "ListOfSpacecrafts.json"))
                );

        //POST new spacecraft
        mockServer
                .when(request()
                                .withPath(baseUrl)
                                .withMethod(EMethods.OPTIONS.name()),
                        Times.exactly(1)
                )
                .respond(response()
                        .withHeaders(HeadersConstants.optionsPostHeaders(EMethods.POST))
                        .withStatusCode(HttpStatus.SC_OK)
                );

        mockServer
                .when(request()
                                .withMethod(EMethods.POST.name())
                                .withPath(baseUrl),
                        Times.exactly(1)
                )
                .respond(response()
                        .withHeaders(HeadersConstants.defaultHeader())
                        .withStatusCode(HttpStatus.SC_OK)
                        .withBody(FileReader.readFileAsString(RELATIVE_PATH + "SpacecraftDetails.json"))
                );

        //GET all spacecrafts after creation
        mockServer
                .when(request()
                                .withPath(baseUrl)
                                .withMethod(EMethods.OPTIONS.name()),
                        Times.exactly(1)
                )
                .respond(response()
                        .withHeaders(HeadersConstants.optionsGetHeaders(EMethods.GET))
                        .withStatusCode(HttpStatus.SC_OK)
                );

        mockServer
                .when(request()
                                .withMethod(EMethods.GET.name())
                                .withPath(baseUrl),
                        Times.exactly(1)
                )
                .respond(response()
                        .withHeaders(HeadersConstants.defaultHeader())
                        .withStatusCode(HttpStatus.SC_OK)
                        .withBody(FileReader.readFileAsString(RELATIVE_PATH + "ListOfSpacecraftsAfterAdding.json"))
                );

        mockServer
                .when(request()
                        .withPath(baseUrl + "/4")
                        .withMethod(EMethods.OPTIONS.name()),
                        Times.exactly(1)
                )
                .respond(response()
                        .withHeaders(HeadersConstants.optionsGetHeaders(EMethods.GET))
                        .withStatusCode(HttpStatus.SC_OK)
                );

        mockServer
                .when(request()
                                .withMethod(EMethods.GET.name())
                                .withPath(baseUrl + "/4"),
                        Times.exactly(1)
                )
                .respond(response()
                        .withHeaders(HeadersConstants.defaultHeader())
                        .withStatusCode(HttpStatus.SC_OK)
                        .withBody(FileReader.readFileAsString(RELATIVE_PATH + "SpacecraftDetails.json"))
                );
    }

    private static void setAvailableComponentResponses() {

        String baseUrl = "/pa165/rest/craftComponents/available";

        mockServer
                .when(request()
                        .withPath(baseUrl)
                        .withMethod(EMethods.OPTIONS.name())
                )
                .respond(response()
                        .withHeaders(HeadersConstants.optionsGetHeaders(EMethods.GET))
                        .withStatusCode(HttpStatus.SC_OK)
                );

        mockServer
                .when(request()
                                .withMethod(EMethods.GET.name())
                                .withPath(baseUrl)
                )
                .respond(response()
                        .withHeaders(HeadersConstants.defaultHeader())
                        .withStatusCode(HttpStatus.SC_OK)
                        .withBody(FileReader.readFileAsString(RELATIVE_PATH + "ListOfAvailableCraftComponents.json"))
                );
    }
}
