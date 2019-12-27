package com.renga.socialapp.lookups;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private String condition;
    private String value;
}
