package providers;

import java.util.Arrays;

import org.testng.ITestContext;


public class CommonProvider {

    private static final int INDEX_TO_END = -1;

    public static Object[][] returnDesiredRows(ITestContext ctx, Object[][] provider) {

        String from = ctx.getCurrentXmlTest().getParameter("dataprovider-from");
        String to = ctx.getCurrentXmlTest().getParameter("dataprovider-to");
        if (from != null && to != null){
            int fromIndex = Integer.parseInt(from);
            int toIndex = Integer.parseInt(to);

            if (toIndex == INDEX_TO_END) {
                toIndex = provider.length;
            }

            if (fromIndex >= 0 && toIndex <= provider.length) {

                // TODO
                //Log.debug("RETURNING PART OF PROVIDER from " + fromIndex + " to " + toIndex);
                return Arrays.copyOfRange(provider, fromIndex, toIndex);
            } else {

                throw new IllegalArgumentException("Argument '" + from + "' or '" + to + "' is not valid!");
            }
        } else {

            if (from == null && to == null) {
                return provider;
            }
            throw new IllegalArgumentException("Argument '" + from + "' or '" + to + "' is not valid!");
        }
    }
}
