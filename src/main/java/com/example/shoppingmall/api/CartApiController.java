package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.request.RequestSize;
import com.example.shoppingmall.data.dto.response.ResponseCart;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CartApiController {

    private final CartService cartService;
    @Autowired
    public CartApiController(CartService cartService) {
        this.cartService = cartService;
    }

    /** 장바구니 목록 조회 */
    @GetMapping("/user/cart")
    public ResponseEntity<List<ResponseCart>> readCart(HttpServletRequest request) {
        List<ResponseCart> cartList = cartService.readCart((User) request.getAttribute("user"));
        return (cartList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(cartList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 장바구니 상품 추가 */
    @PostMapping("/user/cart/{id}")
    public ResponseEntity<List<ResponseCart>> createCart(HttpServletRequest request, @PathVariable Long id, @RequestBody RequestSize requestSize) {
        List<ResponseCart> cartList = cartService.createCart((User) request.getAttribute("user"), id, requestSize.getSize());
        return (cartList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(cartList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 장바구니 상품 삭제 */
    @DeleteMapping("/user/cart/{id}")
    public ResponseEntity<List<ResponseCart>> deleteCart(HttpServletRequest request, @PathVariable Long id, @RequestParam(value = "size") String size) {
        List<ResponseCart> cartList = cartService.deleteCart((User) request.getAttribute("user"), id, size);
        return (cartList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(cartList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
