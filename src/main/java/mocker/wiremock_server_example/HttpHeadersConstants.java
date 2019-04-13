package mocker.wiremock_server_example;

import java.util.HashSet;
import java.util.Set;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;

public class HttpHeadersConstants {

    private static final HttpHeader ACCESS_ORIGIN_HEADER = new HttpHeader("Access-Control-Allow-Origin", "*");
    private static final HttpHeader ACCESS_CONTROL_ALLOW_METHODS_GET_HEADER = new HttpHeader("Access-Control-Allow-Methods", "GET");
    private static final HttpHeader ACCESS_CONTROL_ALLOW_METHODS_POST_HEADER = new HttpHeader("Access-Control-Allow-Methods", "POST");
    private static final HttpHeader ACCESS_CONTROL_ALLOW_METHODS_DELETE_HEADER = new HttpHeader("Access-Control-Allow-Methods", "DELETE");
    private static final HttpHeader ACCESS_REQUEST_GET_HEADER = new HttpHeader("Access-Control-Allow-Headers", "authorization,x-requested-with");
    private static final HttpHeader ACCESS_REQUEST_POST_HEADER = new HttpHeader("Access-Control-Allow-Headers", "authorization,content-type,x-requested-with");
    private static final HttpHeader CONTENT_HEADER = new HttpHeader("Content-Type", "application/json; charset=UTF-8");


    private static final Set<HttpHeader> DEFAULT_HEADERS = new HashSet<HttpHeader>() {
        {
            add(CONTENT_HEADER);
            add(ACCESS_ORIGIN_HEADER);
        }
    };

    private static final Set<HttpHeader> GET_HEADERS = new HashSet<HttpHeader>(DEFAULT_HEADERS) {
        {
            add(ACCESS_REQUEST_GET_HEADER);
            add(ACCESS_CONTROL_ALLOW_METHODS_GET_HEADER);
        }
    };

    private static final Set<HttpHeader> POST_HEADERS = new HashSet<HttpHeader>(DEFAULT_HEADERS) {
        {
            add(ACCESS_REQUEST_POST_HEADER);
            add(ACCESS_CONTROL_ALLOW_METHODS_POST_HEADER);
        }
    };

    private static final Set<HttpHeader> DELETE_HEADERS = new HashSet<HttpHeader>(DEFAULT_HEADERS) {
        {
            add(ACCESS_REQUEST_GET_HEADER);
            add(ACCESS_CONTROL_ALLOW_METHODS_DELETE_HEADER);
        }
    };

    public static final HttpHeaders HEADERS = new HttpHeaders(DEFAULT_HEADERS);
    public static final HttpHeaders GET_OPTION_HEADERS = new HttpHeaders(GET_HEADERS);
    public static final HttpHeaders POST_OPTION_HEADERS = new HttpHeaders(POST_HEADERS);
    public static final HttpHeaders DELETE_OPTION_HEADERS = new HttpHeaders(DELETE_HEADERS);
}
