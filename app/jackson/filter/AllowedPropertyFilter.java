package jackson.filter;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter.FilterExceptFilter;

import java.util.Collections;
import java.util.Set;

public class AllowedPropertyFilter extends FilterExceptFilter {

    private Set<String> allowedProperties;

    public AllowedPropertyFilter(Set<String> properties) {
        super(properties);
    }

    public Set<String> getAllowedProperties() {
        if (allowedProperties == null) {
            allowedProperties = Collections.unmodifiableSet(_propertiesToInclude);
        }
        return allowedProperties;
    }
}
