/*
 * JacksonObjectMapperProvider.java
 *
 * Copyright (c) 2016 by General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.aws.petproject.configuration;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Provides custom {@code ObjectMapper} for Jackson JSON serialization
 */
@Provider
public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    /**
     * Creates a new instance
     *
     * @param objectMapper the object mapper
     */
    @Autowired
    public JacksonObjectMapperProvider( ObjectMapper objectMapper ) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ObjectMapper getContext( Class<?> type ) {
        return objectMapper;
    }
}
