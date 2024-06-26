package com.ssafy.triptogether.flashmob.domain;

import com.ssafy.triptogether.global.domain.BaseEntity;
import com.ssafy.triptogether.member.domain.Member;
import com.ssafy.triptogether.member.domain.RoomStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_flash_mob")
public class MemberFlashMob extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_flash_mob_id")
	private Long id;

	@NotNull
	@Column(name = "is_master")
	private Boolean isMaster;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private RoomStatus roomStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flash_mob_id")
	private FlashMob flashMob;

	@Builder
	public MemberFlashMob(Boolean isMaster, RoomStatus roomStatus, Member member, FlashMob flashMob) {
		this.isMaster = isMaster;
		this.roomStatus = roomStatus;
		setMember(member);
		setFlashMob(flashMob);
	}

	public void setFlashMob(FlashMob flashMob) {
		this.flashMob = flashMob;
		flashMob.getMemberFlashMobs().add(this);
	}

	public void setMember(Member member) {
		this.member = member;
		member.getMemberFlashMobs().add(this);
	}

	public void checkDenial() {
		this.roomStatus = RoomStatus.REFUSE_CHECK;
	}

	public void applyAcceptance() {
		this.roomStatus = RoomStatus.ATTEND;
	}

	public void applyDenial() {
		this.roomStatus = RoomStatus.REFUSE_UNCHECK;
	}

	public void memberToMaster() {
		this.isMaster = true;
	}

	public void memberToMember() {
		this.isMaster = false;
	}
}
