package core.constants;

import java.util.ArrayList;
import java.util.List;

import org.mockserver.model.Header;

import core.enums.EMethods;

public class HeadersConstants {

    public static List<Header> defaultHeader() {

        List<Header> defaultHeader = new ArrayList<>();
        defaultHeader.add(Header.header("Content-Type", "application/json; charset=UTF-8"));
        defaultHeader.add(Header.header("Access-Control-Allow-Origin", "*"));
        return defaultHeader;
    }

    public static List<Header> optionsGetHeaders(EMethods eMethods) {

        List<Header> optionsGetHeaders = new ArrayList<>(defaultHeader());
        optionsGetHeaders.add(Header.header("Access-Control-Allow-Methods", eMethods.name()));
        optionsGetHeaders.add(Header.header("Access-Control-Allow-Headers", "authorization,x-requested-with"));
        return optionsGetHeaders;
    }

    public static List<Header> optionsPostHeaders(EMethods eMethods) {

        List<Header> optionsGetHeaders = new ArrayList<>(defaultHeader());
        optionsGetHeaders.add(Header.header("Access-Control-Allow-Methods", eMethods.name()));
        optionsGetHeaders.add(Header.header("Access-Control-Allow-Headers", "authorization,content-type,x-requested-with"));
        return optionsGetHeaders;
    }
}
