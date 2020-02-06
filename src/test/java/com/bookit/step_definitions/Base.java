package com.bookit.step_definitions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class Base {
    protected RequestSpecification request;
    protected Response response;
}
