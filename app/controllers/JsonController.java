package controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.entity.ContentType;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Function;

public abstract class JsonController extends Controller {
    public static String getErrorAsJson(Throwable throwable) {
        return getErrorStringAsJson(ExceptionUtils.getStackTrace(throwable));
    }

    public static String getErrorStringAsJson(String message) {
        StringWriter writer = new StringWriter();
        try (JsonGenerator generator = new JsonFactory().createGenerator(writer)) {
            generator.writeStartObject();
            generator.writeObjectField("error", message);
            generator.writeEndObject();
        } catch (IOException ioe) {
            return null;
        }
        return writer.toString();
    }

    public static Result getErrorAsJsonResult(Throwable throwable, Function<String, Result> creator) {
        return creator.apply(getErrorAsJson(throwable)).as(ContentType.APPLICATION_JSON.getMimeType());
    }

    public static Result getErrorStringAsJsonResult(String message, Function<String, Result> creator) {
        return creator.apply(getErrorStringAsJson(message)).as(ContentType.APPLICATION_JSON.getMimeType());
    }
}
