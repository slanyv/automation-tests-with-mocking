package tools;

import lombok.Getter;

public class Configuration {

    private @Getter boolean useWireMockServer;
    private @Getter boolean useMockServerClient;
    private @Getter boolean useExternalMockServer;

    /**
     * Call this method on the beginning of test - in setUp()
     */
    public void initialize() {

        String useWireMockServer = System.getProperty("useWireMockServer");
        if (useWireMockServer == null || useWireMockServer.equals("no")) {
            this.useWireMockServer = false;
        } else if (useWireMockServer.equals("yes")) {
            this.useWireMockServer = true;
        } else {
            throw new IllegalArgumentException("Not unsupported -DuseWireMockServer parameter: '" + useWireMockServer + "'. " +
                    "Use one of supported values: 'yes' or 'no'");
        }

        String useMockServerClient = System.getProperty("useMockServerClient");
        if (useMockServerClient == null || useMockServerClient.equals("no")) {
            this.useMockServerClient = false;
        } else if (useMockServerClient.equals("yes")) {
            this.useMockServerClient = true;
        } else {
            throw new IllegalArgumentException("Not unsupported -DuseMockServerClient parameter: '" + useMockServerClient + "'. " +
                    "Use one of supported values: 'yes' or 'no'");
        }

        String useExternalMockServer = System.getProperty("useExternalMockServer");
        if (useExternalMockServer == null || useExternalMockServer.equals("no")) {
            this.useExternalMockServer = false;
        } else if (useExternalMockServer.equals("yes")) {
            this.useExternalMockServer = true;
        } else {
            throw new IllegalArgumentException("Not unsupported -DuseExternalMockServer parameter: '" + useExternalMockServer + "'. " +
                    "Use one of supported values: 'yes' or 'no'");
        }

        if (this.useWireMockServer && this.useMockServerClient) {
            throw new IllegalArgumentException("Not unsupported combination of values for -DuseMockServerClient and -DuseWireMockServer. " +
                    "Only one mocking server can be used at the same time.");
        }
    }
}
