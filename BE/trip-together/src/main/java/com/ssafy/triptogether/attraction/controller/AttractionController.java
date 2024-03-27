package com.ssafy.triptogether.attraction.controller;

import com.ssafy.triptogether.attraction.data.FlashmobListFindResponse;
import com.ssafy.triptogether.attraction.data.FlashmobUpdateRequest;
import com.ssafy.triptogether.attraction.data.FlashmobUpdateResponse;
import com.ssafy.triptogether.attraction.service.AttractionLoadService;
import com.ssafy.triptogether.attraction.service.AttractionSaveService;
import com.ssafy.triptogether.auth.utils.SecurityMember;
import com.ssafy.triptogether.global.data.response.ApiResponse;
import com.ssafy.triptogether.plan.data.response.AttractionDetailFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.triptogether.global.data.response.StatusCode.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/plan/v1/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionLoadService attractionLoadService;
    private final AttractionSaveService attractionSaveService;

    @GetMapping("/{attraction_id}")
    public ResponseEntity<ApiResponse<AttractionDetailFindResponse>> findAttractionDetail(
        @PathVariable("attraction_id") long attractionId
    ) {
        AttractionDetailFindResponse response = attractionLoadService.findAttractionDetail(attractionId);
        return ApiResponse.toResponseEntity(OK, SUCCESS_ATTRACTION_DETAIL_FIND, response);
    }

    @PatchMapping("/{attraction_id}/flashmobs/{flashmob_id}")
    public ResponseEntity<ApiResponse<FlashmobUpdateResponse>> updateFlashmob(
        @PathVariable("attraction_id") long attractionId,
        @PathVariable("flashmob_id") long flashmobId,
        @RequestBody FlashmobUpdateRequest flashmobUpdateRequest
    ) {
        FlashmobUpdateResponse response = attractionSaveService.updateFlashmob(flashmobId, flashmobUpdateRequest);
        return ApiResponse.toResponseEntity(OK, SUCCESS_FLASHMOB_UPDATE, response);
    }

    @GetMapping("/{attraction_id}/flashmobs")
    public ResponseEntity<ApiResponse<FlashmobListFindResponse>> findFlashmobList(
        @PathVariable("attraction_id") long attractionId,
        @AuthenticationPrincipal SecurityMember securityMember
    ) {
        long memberId = securityMember.getId();
        FlashmobListFindResponse response = attractionLoadService.findFlashmobList(attractionId, memberId);
        return ApiResponse.toResponseEntity(OK, SUCCESS_FLASHMOB_LIST_FIND, response);
    }
}
