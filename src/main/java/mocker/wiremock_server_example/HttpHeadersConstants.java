package mocker.wiremock_server_example;

import java.util.HashSet;
import java.util.Set;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;

public class HttpHeadersConstants {

    public static String accessControlAllowMethodsHeader  = "Access-Control-Allow-Methods";

    private static final HttpHeader ACCESS_ORIGIN_HEADER = new HttpHeader("Access-Control-Allow-Origin", "*");
    private static final HttpHeader ACCESS_CONTROL_ALLOW_METHODS_GET_HEADER = new HttpHeader("Access-Control-Allow-Methods", "GET");
    private static final HttpHeader ACCESS_CONTROL_ALLOW_METHODS_POST_HEADER = new HttpHeader("Access-Control-Allow-Methods", "POST");
    private static final HttpHeader ACCESS_CONTROL_ALLOW_METHODS_DELETE_HEADER = new HttpHeader("Access-Control-Allow-Methods", "DELETE");
    private static final HttpHeader ACCESS_HEADERS_HEADER = new HttpHeader("Access-Control-Allow-Headers", "accept, content-type");
    private static final HttpHeader ACCESS_REQUEST_GET_HEADER = new HttpHeader("Access-Control-Allow-Headers", "authorization,x-requested-with"); //this is needed
    private static final HttpHeader ACCESS_REQUEST_POST_HEADER = new HttpHeader("Access-Control-Allow-Headers", "authorization,content-type,x-requested-with"); //this is needed
    private static final HttpHeader CONTENT_HEADER = new HttpHeader("Content-Type", "application/json; charset=UTF-8");
    private static final HttpHeader AUTHORIZATION_HEADER = new HttpHeader("Authorization", "Basic YWRtaW5AYWRtaW4uY29tOnBhc3N3b3Jk");
    private static final HttpHeader PRAGMA_HEADER = new HttpHeader("Pragma", "no-cache");
    private static final HttpHeader CONNECTION_HEADER = new HttpHeader("Connection", "keep-alive");
    private static final HttpHeader TRANSFER_HEADER = new HttpHeader("Transfer-Encoding", "chunked");
    private static final HttpHeader CACHE_HEADER = new HttpHeader("Cache-Control", "no-cache, no-store");
    private static final HttpHeader OPTION_CACHE_HEADER = new HttpHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");

    private static final Set<HttpHeader> GET_HEADERS = new HashSet<HttpHeader>() {
        {
            add(CONTENT_HEADER);
            add(AUTHORIZATION_HEADER);
            add(ACCESS_ORIGIN_HEADER);
            add(ACCESS_REQUEST_GET_HEADER);
            add(OPTION_CACHE_HEADER);
            add(PRAGMA_HEADER);
            add(TRANSFER_HEADER);
            add(ACCESS_CONTROL_ALLOW_METHODS_GET_HEADER);
        }
    };

    private static final Set<HttpHeader> DEFAULT_OPTION_HEADERS = new HashSet<HttpHeader>() {
        {
            add(CONTENT_HEADER);
            add(AUTHORIZATION_HEADER);
            add(ACCESS_ORIGIN_HEADER);
            add(ACCESS_REQUEST_GET_HEADER);
            add(OPTION_CACHE_HEADER);
            add(PRAGMA_HEADER);
            add(TRANSFER_HEADER);
        }
    };

    private static final Set<HttpHeader> DEFAULT_HEADERS = new HashSet<HttpHeader>() {
        {
            add(ACCESS_ORIGIN_HEADER);
            add(ACCESS_HEADERS_HEADER);
            add(ACCESS_REQUEST_GET_HEADER);
            add(ACCESS_CONTROL_ALLOW_METHODS_GET_HEADER);
            add(CONTENT_HEADER);
            add(AUTHORIZATION_HEADER);
            add(TRANSFER_HEADER);
            add(CACHE_HEADER);
            add(PRAGMA_HEADER);
            add(CONNECTION_HEADER);
        }
    };


    private static final Set<HttpHeader> POST_HEADERS = new HashSet<HttpHeader>() {
        {
            add(CONTENT_HEADER);
            add(AUTHORIZATION_HEADER);
            add(ACCESS_ORIGIN_HEADER);
            add(ACCESS_REQUEST_POST_HEADER);
            add(OPTION_CACHE_HEADER);
            add(PRAGMA_HEADER);
            add(TRANSFER_HEADER);
            add(ACCESS_CONTROL_ALLOW_METHODS_POST_HEADER);
        }
    };

    private static final Set<HttpHeader> DELETE_HEADERS = new HashSet<HttpHeader>() {
        {
            add(CONTENT_HEADER);
            add(AUTHORIZATION_HEADER);
            add(ACCESS_ORIGIN_HEADER);
            add(ACCESS_REQUEST_GET_HEADER);
            add(OPTION_CACHE_HEADER);
            add(PRAGMA_HEADER);
            add(TRANSFER_HEADER);
            add(ACCESS_CONTROL_ALLOW_METHODS_DELETE_HEADER);
        }
    };

    public static final HttpHeaders HEADERS = new HttpHeaders(DEFAULT_HEADERS);
    public static final HttpHeaders GET_OPTION_HEADERS = new HttpHeaders(GET_HEADERS);
    public static final HttpHeaders POST_OPTION_HEADERS = new HttpHeaders(POST_HEADERS);
    public static final HttpHeaders DELETE_OPTION_HEADERS = new HttpHeaders(DELETE_HEADERS);

}
