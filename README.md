## ✈ 개요

Trip-Together는 은행에서 출시한 백패커들을 위한 여행 애플리케이션으로,<br>
간편 동행 모집(번개 모임)과 은행 API를 통한 환전, 결제, 정산 기능을 갖춘 슈퍼 애플리케이션입니다.

## ✈ 주요 기능

<table>
   <colgroup>
      <col width="40%"/>
      <col widht="60%"/>
   </colgroup>
	<tr>
		<td width="200" height="300"><img src="https://github.com/seomiii/seomiii/assets/86819719/674410db-8977-4ecb-ab68-ce84495aabf1" width="200" height="400"/></td>
		<td><br><h4>소셜 로그인(인증/인가)</h4><br>Twinkle-Bank의 계정으로 trip-together에서 간편하게 로그인할 수 있습니다.<br><br><ul><li>trip-together 로그인 시, 사용자가 은행의 로그인 정보(id,password)와 같은 민감한 정보를 제공하지 않고, 은행이 직접 인증 과정을 처리할 수 있도록 OAuth 2.0을 도입하였습니다.<br><br><li>Kakao 소셜 로그인의 인증 로직과 동일하게 구현했습니다. 사용자가 은행에 인증 코드를 요청 및 로그인을 진행하고, 발급된 인증 코드를 통해 여행 서비스에서 토큰을 발급받아 로그인이 진행되게 하였습니다.</ul></td>
	</tr>
	<tr>
		<td width="200" height="300"><img src="https://github.com/seomiii/seomiii/assets/86819719/402400e2-2d79-4ffc-b8e9-4b4baa895b28" width="200" height="400"/></td>
		<td><br><br><h4>핀번호 관리</h4><br>최초 로그인을 통해 Twinkle-Bank로부터 인증을 받은 이후로는 Trip-Together의 핀번호를 통해 Twinkle-Bank로부터 인증토큰을 받아올 수 있습니다.<br><br><ul><li>핀 번호 또한 비밀번호와 같은 정보처럼 암호화되어 관리됩니다.</li><li>개인 지갑을 등록할 때 필수로 pin번호를 생성해야 하고, 수정이 가능하여 유출 의심 등의 경우에 사용할 수 있습니다.</li></ul></td>
	</tr>
	<tr>
		<td width="200" height="300"><img src="https://github.com/seomiii/seomiii/assets/86819719/a9387711-b4f4-4f4f-82d6-8dc356fa7a5c" width="200" height="400"/></td>
		<td><br><br><h4>1원 인증 및 계좌 연동</h4><br>Trip-Together에서 환전시 사용할 은행계좌를 연동하기 위해 사용자는 1원을 해당 계좌로 송금받고, 송금자명을 입력하여 인증을 받을 수 있습니다.<br><br><ul><li>국립국어원 한국어 기초사전  데이터를 기반으로 랜덤으로 생성된 입금자명을 연동하고자 하는 은행계좌에 1원을 송금해줍니다.</li><li>사용자가 입력한 입금자명이 유효할 시 계좌 연동에 성공합니다.</li></ul></td>
	</tr>
   <tr>
		<td width="200" height="300"><img src="https://github.com/seomiii/seomiii/assets/86819719/0003a471-a954-472c-9f56-3c301465b170" width="200" height="400"/></td>
		<td><br><br><h4>프로필 관리</h4><br>최초 로그인시 Twinkle-Bank로부터 받아온 사용자 데이터 이외에 Trip-Together 서비스 사용에 필요한 부가 정보를 등록할 수 있습니다.<br><br><ul><li>이름, 생년월일, 성별, 가입일자의 정보는 최초 로그인시 Twinkle-Bank로부터 받아온 것으로 수정이 불가합니다.</li><li>위의 필수 정보 이외에 프로필 이미지, 닉네임, 내소개 정보를 수정하여 다른 사용자들과 소통하는 데에 활용할 수 있습니다.</li></ul></td>
	</tr>
   <tr>
		<td width="400" height="300"><img src="https://github.com/seomiii/seomiii/assets/86819719/b09ba4ee-111c-4e96-960e-60a22d5479e3" width="200" height="400"/></td>
		<td><br><br><h4>현 위치, 장소 기반 모임</h4><br>사용자의 위치, 장소를 기반으로 번개 모임을 모집, 참여할 수 있습니다.<br><br><ul><li>지도에서 편리하게 위치를 선택하고 이에 대한 위도와 경도를 기반으로 등록된 번개모임 구인글을 검색할 수 있습니다.</li><li>가고자하는 여행지별 등록된 번개모임 구인글을 검색할 수 있습니다.</li></ul></td>
	</tr>
   <tr>
		<td width="200" height="300"><img src="https://github.com/seomiii/seomiii/assets/86819719/82fe3426-18ba-4dc2-9fcc-5c9fa3de5947" width="400" height="400"/></td>
		<td><br><br><h4>모임 채팅</h4><br>모임 개설 시 참여자들을 위한 채팅방이 생성됩니다.<br><br><ul><li>모임 구성원들의 자유로운 채팅이 가능합니다.</li><li>신규 참여자는 기존 참여자들의 선택에 의해 참가 / 거절 될 수 있습니다.</li><li>신규 참여자의 정보를 확인하고, 모임 참여자들의 의견을 반영해 결정됩니다.</li></ul></td>
	</tr>
   <tr>
		<td width="200" height="300"><img src="https://github.com/seomiii/seomiii/assets/86819719/1a8e05f8-c840-423d-81e6-ac070988b3fb" width="400" height="400"/></td>
		<td><br><br><h4>결제 내역 기반 더치페이</h4><br>실제 결제 내역을 기반으로 번개 모임 정산을 할 수 있습니다.<br><br><ul><li>모임 채팅방 내에서 실제 결제 내역 및 인원을 선택하여 편리하게 정산할 수 있습니다.</li><li>모두가 정산 내역에 포함된 결제 내역들을 영수증 형태로 조회할 수 있어 거래에 대한 신뢰성을 높입니다.</li></ul></td>
	</tr>
   <tr>
		<td width="200" height="300"><img src="https://github.com/seomiii/seomiii/assets/86819719/5e552e14-c8b7-46aa-b336-030ca483d643" width="200" height="400"/></td>
		<td><br><br><h4>여행 계획 및 예산 책정</h4><br>사용자들의 실제 결제 내역이 적용된 여행지의 인당 평균 소비 금액을 통해 여행 예산을 책정할 수 있습니다.<br><br><ul><li>여행 장소에 대한 리뷰, 사진, 평점 등의 정보에 접근 가능하고, 해당 장소를 장바구니에 추가해 일자별 여행계획에 등록할 수 있습니다.</li><li>여행지의 인당 평균 소비금액은 서비스의 사용자들의 결제내역을 기준으로 주기적인 업데이트를 수행합니다.</li></ul></td>
	</tr>
   <tr>
		<td width="200" height="300"><img src="https://github.com/seomiii/seomiii/assets/86819719/c48b45fc-9071-42af-82d2-926d450b6c0b" width="200" height="400"/></td>
		<td><br><br><h4>간편 환전</h4><br>앱 내 지갑을 통해 “원화 <-> 해외 통화”로의 자유로운 환전이 가능합니다.<br><br><ul><li>“원화 -> 해외 통화“ 환전 시 해당 통화로 신규 여행 계좌가 개설되고 은행에서 원화가 차감됩니다.</li><li>“해외 통화 -> 원화” 환전 시 해당 통화에서 환불이 진행되고 은행에 원화가 입금됩니다.</li><li>매일 09:00 에 갱신되는 환율 정보를 기준으로 환전이 진행됩니다.</li></ul></td>
	</tr>
   <tr>
		<td width="200" height="300"><img src="https://github.com/seomiii/seomiii/assets/86819719/08cee8ab-0ef6-4f48-8198-591ea53c8785" width="200" height="400"/></td>
		<td><br><br><h4>바코드 결제</h4><br>사용자들은 여행지에 대한 결제 프로세스를 앱 내에서 간편 결제를 통해 진행합니다.<br><br><ul><li>가상의 결제 프로세스를 진행하기 위해 여행지의 사업자 번호를 QR에 등록했습니다.</li><li>해당 여행지의 통화에 맞는 사용자의 여행 계좌를 통해 결제가 이루어집니다.</li><li>앱 내 간편 결제를 통해 사용자의 마이 데이터를 확보했습니다.</li></ul></td>
	</tr>
</table>

---

## ✈ 사용 기술 설명

[바로가기](./README_Description_of_technology_used.md)

## ✈ 개발 환경

**📍Back-End**

<img src="https://img.shields.io/badge/java-E2B714?style=for-the-badge"> <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=spring boot&logoColor=white">
<img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=spring security&logoColor=white"> <img src="https://img.shields.io/badge/jpa-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> <img src="https://img.shields.io/badge/querydsl-004088?style=for-the-badge"> 
<br>
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/mongodb-47A248?style=for-the-badge&logo=mongodb&logoColor=white"> <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"> <img src="https://img.shields.io/badge/rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white"> <img src="https://img.shields.io/badge/websocket-010101?style=for-the-badge"> <img src="https://img.shields.io/badge/stomp-59666C?style=for-the-badge">

**📍Front-End**

<img src="https://img.shields.io/badge/typescript-3178C6?style=for-the-badge&logo=typescript&logoColor=white"> <img src="https://img.shields.io/badge/react native-61DAFB?style=for-the-badge&logo=react&logoColor=white"> <img src="https://img.shields.io/badge/redux-764ABC?style=for-the-badge&logo=redux&logoColor=white"> <img src="https://img.shields.io/badge/android studio-3DDC84?style=for-the-badge&logo=android studio&logoColor=white">
<br>
<img src="https://img.shields.io/badge/flipper-2874F0?style=for-the-badge"> <img src="https://img.shields.io/badge/stomp-59666C?style=for-the-badge"> <img src="https://img.shields.io/badge/styledcomponents-DB7093?style=for-the-badge&logo=styledcomponents&logoColor=white">

**📍Infra**

<img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white"> <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
<br>
<img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> <img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white"> <img src="https://img.shields.io/badge/gitlab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white">

## ✈ 서비스 아키텍쳐

![서비스아키텍쳐](https://github.com/seomiii/seomiii/assets/86819719/28b0d5b5-e115-42a7-a7ce-3ec2e3d1e952)

## ✈ 명세서

**ERD**
[바로가기](https://sirlyun.notion.site/ERD-7cc5192cbbff4b85ae90bff77a04d2e1?pvs=4)

**API 명세**
[바로가기](https://sirlyun.notion.site/API-8d1507bd65f44d11989b13f0ec63a99e?pvs=4)

**기능 명세**
[바로가기](https://sirlyun.notion.site/5cd9e9f950294200bc715531a975f60f?pvs=4)

## ✈ 팀원 소개

<table>
 <tr>
    <td align="center"><a href="https://github.com/sirlyun"><img src="https://avatars.githubusercontent.com/sirlyun" width="130px;" alt=""></a></td>
    <td align="center"><a href="https://github.com/seomiii"><img src="https://avatars.githubusercontent.com/seomiii" width="130px;" alt=""></a></td>
    <td align="center"><a href="https://github.com/hgene0929"><img src="https://avatars.githubusercontent.com/hgene0929" width="130px;" alt=""></a></td>
    <td align="center"><a href="https://github.com/ohiju"><img src="https://avatars.githubusercontent.com/ohiju" width="130px;" alt=""></a></td>
    <td align="center"><a href="https://github.com/KTaeGyu"><img src="https://avatars.githubusercontent.com/KTaeGyu" width="130px;" alt=""></a></td>
  <td align="center"><a href="https://github.com/madcom96"><img src="https://avatars.githubusercontent.com/madcom96" width="130px;" alt=""></a>
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/sirlyun"><b>윤설</b></a><br>BACK-END</td>
    <td align="center"><a href="https://github.com/seomiii"><b>김미서</b></a><br>BACK-END</td>
    <td align="center"><a href="https://github.com/hgene0929"><b>이현진</b></a><br>BACK-END<br>INFRA</td>
    <td align="center"><a href="https://github.com/ohiju"><b>오희주</b></a><br>FRONT-END</td>
    <td align="center"><a href="https://github.com/KTaeGyu"><b>김태규</b></a><br>LEAD<br>FRONT-END</td>
    <td align="center"><a href="https://github.com/madcom96"><b>황진하</b></a><br>BACK-END<br>INFRA</td>
  </tr>
</table>
