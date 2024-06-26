package com.ssafy.twinklebank.account.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AccountDeleteRequest(
        @NotBlank @JsonProperty("account_uuid") String accountUuid
) {
}
