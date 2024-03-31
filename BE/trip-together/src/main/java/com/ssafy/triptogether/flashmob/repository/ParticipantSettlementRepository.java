package com.ssafy.triptogether.flashmob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.triptogether.flashmob.domain.MemberSettlement;
import com.ssafy.triptogether.flashmob.domain.ParticipantSettlement;
import com.ssafy.triptogether.flashmob.repository.query.MemberSettlementRepositoryCustom;

public interface ParticipantSettlementRepository extends MemberSettlementRepository<ParticipantSettlement>,
	MemberSettlementRepositoryCustom {

}
