package autotest.utilities;

import autotest.models.annotations.PageUri;

import java.net.URI;

public class UriBuilder {

    public static URI getUri(Class clazz) {
        PageUri annotation = (PageUri) clazz.getAnnotation(PageUri.class);
        return URI.create(annotation.uri());
    }

    static Boolean isUriMatches(Class clazz, URI currentUri) {
        return getUri(clazz).equals(currentUri);
    }
}
