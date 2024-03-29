package com.book.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.bookmodel.CartDAO;
import com.book.model.BookDTO;
import com.book.model.CartDTO;
import com.book.model.CouponDTO;
import com.book.model.MemberDTO;
import com.book.model.PurchaseDTO;

@Controller
public class CartController {

	@Autowired
	private CartDAO Cdao;

	@RequestMapping("cart.go")
	public void cartList(Model model,CartDTO dto,HttpServletResponse response,HttpServletRequest request,HttpSession session,BookDTO bdto) throws IOException {		
		
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		if(dto.getMemberId() == null || dto.getMemberId().equals("")) {
			out.println("<script>");
			out.println("alert('로그인 먼저 부탁드립니다')");
			out.println("location.href='login.go'");
			out.println("</script>");			

		} else{
			int check = Cdao.getCartCkeck(dto);
			
			if(check == 0) {
				this.Cdao.insertCart(dto);
				
				out.println("<script>");
				out.println("location.href='cartList.go'");
				out.println("</script>");		
			}else {
			// 확인 대화 상자 표시
					this.Cdao.cartIf(dto);
					out.println("<script>");	
					out.println("alert('기존 상품에 추가했습니다')");
					out.println("history.back()");
					out.println("</script>");	
				 } 
		
		}
	}
	@RequestMapping("cartList.go")
	public String cartList(Model model,HttpSession session){
		
		MemberDTO sessiondto = (MemberDTO) session.getAttribute("session");
		
		String memberId =sessiondto.getMemberId(); 
		
		List<CartDTO> list = this.Cdao.getcartList(memberId);
		
		model.addAttribute("cList",list );		
		return "cart/Cart";
		}
	
	@RequestMapping("cartDelete.go")
	public void deletecart(HttpSession session,CartDTO dto,HttpServletResponse response) throws IOException {
		
		int check = this.Cdao.deletcart(dto);
		
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out =  response.getWriter();
		if(check > 0) {
			this.Cdao.deletecheck(dto);
			out.println("<script>");
			out.println("location.href='cartList.go'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("histroy.back()");
			out.println("</script>");			
		}
			 
	}
	
	@ResponseBody
	@RequestMapping("cartEaMinus.go")
	public String minusCartEa(HttpSession session, CartDTO cDto, HttpServletResponse response) {
		
		int check = this.Cdao.cartMinusEa(cDto);
		
		return String.valueOf(check);
	}
	
	@ResponseBody
	@RequestMapping("cartEaPlus.go")
	public String plusCartEa(HttpSession session, CartDTO cDto, HttpServletResponse response) {
		
		int check = this.Cdao.cartPlusEa(cDto)	;
		
		return String.valueOf(check);
	}
	
	@ResponseBody
	@RequestMapping("cartTotalCostCheck.go")
	public String checkCartEa(HttpSession session, CartDTO cDto, HttpServletResponse response) {
		
		int check = this.Cdao.cartTotalCost(cDto)	;
		
		return String.valueOf(check);
	}
	
	@RequestMapping("buy.go")
	public String buyList(Model model,HttpSession session){
		
		MemberDTO sessiondto = (MemberDTO) session.getAttribute("session");

		List<CartDTO> list = this.Cdao.getcartList(sessiondto.getMemberId());
		
		List<CouponDTO> couponList = this.Cdao.getCouponList(sessiondto.getMemberId());
	
		model.addAttribute("cList",list );		
		model.addAttribute("coupon", couponList );		
		
		return "cart/Buy";
	}
	
	@RequestMapping("buySuccess.go")
	public String buySuccess(Model model,PurchaseDTO pdto ,HttpSession session, 
			@RequestParam("couponNo") int couponNo, @RequestParam("usePoint") int point,
			@RequestParam("payMoney") int paymentCost, @RequestParam("addPoint") int addPoint) {
		
		MemberDTO sessiondto = (MemberDTO) session.getAttribute("session");
		
		String memberId =sessiondto.getMemberId(); 
		
		int usePoint = sessiondto.getPoint() - point;
		sessiondto.setPoint(usePoint);
		
		// 장바구니 목록 불러오기
		List<CartDTO> list = this.Cdao.getcartList(memberId);
		
		// 현재 날짜 / 분 까지의 데이터와 4자리의 랜덤번호를 지정받아 구매영수번호를 만든다.
		String PurchaseLabel = generateRandomNumber();
		
		int usedCouponPrice;
		
		try {
			
		    usedCouponPrice = this.Cdao.getCouponPrice(couponNo);

		} catch (NullPointerException e) {

			usedCouponPrice = 0; 
			
		}
		
		// 해당 유저의 장바구니 내역과 사용한 쿠폰을 삭제처리한다.
		this.Cdao.deleteMemCart(memberId);
		
		for (int i = 0; i < list.size(); i++) {
			
			PurchaseDTO dto = new PurchaseDTO();
			
			dto.setMemberId(memberId);
			dto.setPurchaseNo(PurchaseLabel);
			dto.setBookNo(list.get(i).getBookNo());
			dto.setBookEA(list.get(i).getCartCount());
			dto.setUsedCouponCost(usedCouponPrice);
			dto.setUsedPoint(point);
			dto.setPaymentCost(paymentCost);
			
			// 장바구니 목록의 크기만큼 insert문을 실행해준다.
			this.Cdao.insertPurchase(dto);
			
			// 시퀀스 작업해주기
			this.Cdao.deletecheck(list.get(i));
		}
		
		int checkCoupon = this.Cdao.deleteCoupon(couponNo);
		
		if(checkCoupon > 0) {
		}
		
		// 포인트 적립 부분
		int calPoint = sessiondto.getPoint();
		calPoint += addPoint;
		sessiondto.setPoint(calPoint);
		
		this.Cdao.updatePoint(sessiondto);
		
		List<PurchaseDTO> pList = this.Cdao.purchasList(PurchaseLabel);
		
		model.addAttribute("pList", pList);
		
		
		return "cart/successBuy";
		
	}
	
	@RequestMapping("directBuy.go")
	public void directBuy(Model model,CartDTO dto,HttpServletResponse response,HttpServletRequest request,HttpSession session,BookDTO bdto) throws IOException {		
		
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		if(dto.getMemberId() == null || dto.getMemberId().equals("")) {
			out.println("<script>");
			out.println("alert('로그인 먼저 부탁드립니다')");
			out.println("location.href='login.go'");
			out.println("</script>");			

		} else{
			
			int check = Cdao.getCartCkeck(dto);
			
			if(check == 0) {
				this.Cdao.insertCart(dto);
				
				out.println("<script>");
				out.println("location.href='buy.go'");
				out.println("</script>");		
			}else {
			// 확인 대화 상자 표시
			this.Cdao.cartIf(dto);
			out.println("<script>");	
			out.println("alert('기존 상품에 추가했습니다')");
			out.println("history.back()");
			out.println("</script>");	
		 } 
		
		}
	}

	
	@RequestMapping("test.go")
	public String test(Model model,PurchaseDTO pdto ,HttpSession session) {
		
		 List<PurchaseDTO> pList = this.Cdao.purchasList("2023060212351283");
		 
		 model.addAttribute("pList", pList);
		
		return "cart/successBuy";
		
	}
	
	
	
	
	    public static String generateRandomNumber() {
	        // 현재 날짜와 시간 정보 가져오기
	        LocalDateTime now = LocalDateTime.now();

	        // 형식 지정을 위한 DateTimeFormatter 생성
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

	        // 현재 날짜와 시간 정보를 형식에 맞게 포맷팅
	        String formattedDateTime = now.format(formatter);

	        // 4자리 랜덤 번호 생성
	        Random random = new Random();
	        int randomNumber = random.nextInt(9000) + 1000; // 1000부터 9999까지의 난수 생성

	        // 현재 날짜와 시간 정보에 4자리 랜덤 번호를 붙여서 반환
	        String result = formattedDateTime + randomNumber;
	        return result;
	    }
	
	
}
