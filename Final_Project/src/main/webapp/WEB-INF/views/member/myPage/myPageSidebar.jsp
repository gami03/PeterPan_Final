<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<div class="snb_wrap">
		<ul id="LeftNavList" class="snb_list category">
			<li class="snb_item" data-id="1">
				<a class="snb_link depth_title" href="https://order.kyobobook.co.kr/myroom/member/order-list">
					쇼핑내역
				</a>
				<ul class="snb_list">
					<li class="snb_item" data-id="8">
						<a class="snb_link" href="<%=request.getContextPath() %>/orderHistory.go">
							주문/배송목록
						</a>
					</li>
					<li class="snb_item" data-id="9">
						<a class="snb_link" href="https://order.kyobobook.co.kr/myroom/member/order-list">
							선물함
						</a>
					</li>
					<li class="snb_item" data-id="10">
						<a class="snb_link" href="https://order.kyobobook.co.kr/myroom/member/order-list">
							매장 구매 내역
						</a>
					</li>
					<li class="snb_item" data-id="11">
						<a class="snb_link" href="https://order.kyobobook.co.kr/myroom/member/order-list">
							영수증 조회 / 후 적립
						</a>
					</li>
				</ul>
			</li>
			<li class="snb_item" data-id="2">
				<a class="snb_link depth_title" href="https://my.kyobobook.co.kr/library">
					라이브러리
				</a>
				<ul class="snb_list">
					<li class="snb_item" data-id="15">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							메인
						</a>
					</li>
					<li class="snb_item" data-id="16">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							리스트
						</a>
					</li>
					<li class="snb_item" data-id="17">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							e-라이브러리
						</a>
					</li>
					<li class="snb_item" data-id="18">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							코멘트
						</a>
					</li>
					<li class="snb_item" data-id="19">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							문장수집
						</a>
					</li>
					<li class="snb_item" data-id="20">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							찜
						</a>
					</li>
					<li class="snb_item" data-id="21">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							구독
						</a>
					</li>
				</ul>
			</li>
			<li class="snb_item" data-id="3">
				<a class="snb_link depth_title" href="https://order.kyobobook.co.kr/myroom/member/order-list">
					활동내역
				</a>
				<ul class="snb_list">
					<li class="snb_item" data-id="22">
						<a class="snb_link" href="https://order.kyobobook.co.kr/myroom/member/order-list">
							커뮤니티 활동 내역
						</a>
					</li>
					<li class="snb_item" data-id="23">
						<a class="snb_link" href="https://order.kyobobook.co.kr/myroom/member/order-list">
							이벤트 참여 내역
						</a>
					</li>
					<li class="snb_item" data-id="24">
						<a class="snb_link" href="https://order.kyobobook.co.kr/myroom/member/order-list">
							문화공간 이용 내역
						</a>
					</li>
				</ul>
			</li>
			<li class="snb_item" data-id="4">
				<a class="snb_link depth_title" href="https://my.kyobobook.co.kr/library">
					혜택/포인트
				</a>
				<ul class="snb_list">
					<li class="snb_item" data-id="25">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							쿠폰
						</a>
					</li>
					<li class="snb_item" data-id="26">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							통합포인트
						</a>
					</li>
					<li class="snb_item" data-id="27">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							예치금
						</a>
					</li>
					<li class="snb_item" data-id="28">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							e교환권
						</a>
					</li>
				</ul>
			</li>
			<li class="snb_item" data-id="5">
				<a class="snb_link depth_title" href="https://my.kyobobook.co.kr/library">
					문의내역
				</a>
				<ul class="snb_list">
					<li class="snb_item" data-id="29">
						<a class="snb_link" href="<%=request.getContextPath() %>/qnaList.go">
							1:1 문의
						</a>
					</li>
					<li class="snb_item" data-id="30">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							상품문의
						</a>
					</li>
				</ul>
			</li>
			<li class="snb_item" data-id="6">
				<a class="snb_link depth_title" href="https://my.kyobobook.co.kr/library">
					회원정보
				</a>
				<ul class="snb_list">
					<li class="snb_item" data-id="31">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							배송주소록
						</a>
					</li>
					<li class="snb_item" data-id="32">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							MY매장
						</a>
					</li>
					<li class="snb_item" data-id="33">
						<a class="snb_link" href="<%=request.getContextPath() %>/member_info_midify_pwdcheck.go">
							회원정보관리
						</a>
					</li>
					<li class="snb_item" data-id="34">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							나의 회원등급
						</a>
					</li>
				</ul>
			</li>
			<li class="snb_item" data-id="7">
				<a class="snb_link depth_title" href="https://my.kyobobook.co.kr/library">
					알림센터
				</a>
				<ul class="snb_list">
					<li class="snb_item" data-id="35">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							알림함
						</a>
					</li>
					<li class="snb_item" data-id="36">
						<a class="snb_link" href="https://my.kyobobook.co.kr/library">
							알림신청내역
						</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>

</body>
</html>