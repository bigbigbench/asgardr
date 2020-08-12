package org.cloud.hikvision.common.retrofit.model;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Value {

    @JsonProperty("CreateIndex")
    public abstract long getCreateIndex();

    @JsonProperty("ModifyIndex")
    public abstract long getModifyIndex();

    @JsonProperty("LockIndex")
    public abstract long getLockIndex();

    @JsonProperty("Key")
    public abstract String getKey();

    @JsonProperty("Flags")
    public abstract long getFlags();

    @JsonProperty("Value")
    public abstract Optional<String> getValue();

    @JsonProperty("Session")
    public abstract Optional<String> getSession();
}
