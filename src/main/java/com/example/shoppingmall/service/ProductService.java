package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.request.ChangeStockQuery;
import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.request.RequestProduct;
import com.example.shoppingmall.data.dto.request.RequestProductModify;
import com.example.shoppingmall.data.dto.response.ResponseProduct;
import com.example.shoppingmall.data.dto.response.ResponseProductDetails;
import com.example.shoppingmall.data.dto.response.ResponseProductSummary;
import com.example.shoppingmall.data.entity.User;

import java.util.List;

public interface ProductService {

    /** 메인 페이지 상품 조회 */
    List<List<?>>  mainPageProductList();

    /** 상품명으로 검색 */
    List<ResponseProductSummary> findByProductName(String keyword, String sort);

    /** 상품 전체 조회 */
    List<ResponseProductSummary> findAllProduct(String sort);

    /** 상품 카테고리별 조회 */
    List<ResponseProductSummary> findByCategory(String category, String sort);

    /** 상품 상세 페이지 조회 */
    ResponseProductDetails findById(User user, Long id);

    /** 판매등록한 상품 목록 조회 */
    List<ResponseProductSummary> findProductByUsername(Long userId);

    /** 상품 등록 */
    boolean createProduct(RequestProduct requestProduct, User user);

    /** 상품 정보 수정 페이지 */
    ResponseProduct editProduct(Long id, User user);

    /** 상품 정보 수정 */
    ResponseProduct updateProduct(RequestProductModify requestProductModify);

    /** 상품 삭제 */
    boolean deleteProduct(Long id, User user);

    void increaseHits(Long id);

    void increaseFavorite(Long id);

    void decreaseFavorite(Long id);

    Boolean purchaseProduct(RequestOrder requestOrder);

    Boolean stockUpProduct(User user, ChangeStockQuery changeStockQuery);
}
