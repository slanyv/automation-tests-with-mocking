package tools;

import lombok.Getter;

public class Configuration {

    private @Getter boolean useMockServer;

    /**
     * Call this method on the beginning of test - in setUp()
     */
    public void initialize() {

        String useMockServer = System.getProperty("useMockServer");
        if (useMockServer == null || useMockServer.equals("no")) {
            this.useMockServer = false;
        } else if (useMockServer.equals("yes")) {
            this.useMockServer = true;
        } else {
            throw new IllegalArgumentException("Not unsupported -DuseMockServer parameter: '" + useMockServer + "'. " +
                    "Use one of supported values: 'yes' or 'no'");
        }
    }
}
