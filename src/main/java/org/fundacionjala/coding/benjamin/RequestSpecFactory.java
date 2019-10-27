package org.fundacionjala.coding.benjamin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Benjamin Huanca on 10/17/2019.
 * @version 1.0
 */
public class RequestSpecFactory {
    private static final Map<String, Supplier<RequestSpecification>> REQUEST_SPEC_MAP=new HashMap<>();
    static{
        REQUEST_SPEC_MAP.put("pivotal", RequestSpecFactory::getRequestSpec);
    }

    private RequestSpecFactory() {
    }

    private static RequestSpecification getRequestSpec() {
        RequestSpecification requestSpecification=new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getValue("baseUri"))
                .addHeader("X-TrackerToken", Environment.getInstance().getValue("credentials.owner.token"))
                .build();
        return requestSpecification
                .log().method()
                .log().uri()
                .log().params()
                .log().body();
    }

    public static RequestSpecification getRequestSpec(final String serviceName){
        return REQUEST_SPEC_MAP.get(serviceName).get();
    }
}
