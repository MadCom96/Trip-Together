package com.ssafy.triptogether.flashmob.data.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record SettlementsLoadResponse(
	@JsonProperty("settlements")
	List<SettlementsLoadDetail> settlementsLoadDetails
) {
}
