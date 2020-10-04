package jackson.filter;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter(DynamicFilterMixin.FILTER_NAME)
public class DynamicFilterMixin {
    public static final String FILTER_NAME = "_dynamicFilter";
}
