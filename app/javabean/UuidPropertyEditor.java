package javabean;

import java.beans.PropertyEditorSupport;
import java.util.UUID;

public class UuidPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(UUID.fromString(text));
    }
}
