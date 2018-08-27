package VodafoneAutotest.Utilities;

import VodafoneAutotest.Models.Annotations.PageUri;

import java.net.URI;

public class UriBuilder {

    public static URI getUri(Class clazz) {
        PageUri annotation = (PageUri) clazz.getAnnotation(PageUri.class);
        return URI.create(annotation.uri());
    }

    public static Boolean isUriMatches(Class clazz, URI currentUri) {
        return getUri(clazz).equals(currentUri);
    }
}
