package com.renga.api.models;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class BaseModel {
    private UUID id;
    private Date created_at;
    private Date updated_at;
}