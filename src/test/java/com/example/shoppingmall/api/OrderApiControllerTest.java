package com.example.shoppingmall.api;

import com.example.shoppingmall.config.auth.PrincipalDetails;
import com.example.shoppingmall.data.dto.queryselect.QueryOrderProduct;
import com.example.shoppingmall.data.dto.request.RequestOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderApiControllerTest extends BaseControllerTest {

    @Nested
    @DisplayName("주문 조회")
    @WithUserDetails(value = "hwang")
    class read_order {
        @Test
        @DisplayName("성공")
        @Transactional
        void success() throws Exception {
            String url = "/user/order";

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(url)
                    .requestAttr("user", principalDetails.getUser());
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("주문 생성")
    @WithUserDetails(value = "hwang")
    class create_order {
        @Test
        @DisplayName("성공")
        @Transactional
        void success() throws Exception {
            String url = "/user/order";
            String content = objectMapper.writeValueAsString(new RequestOrder(
                    LocalDateTime.now().withNano(0).toString(),
                    "배송완료",
                    Arrays.asList(new QueryOrderProduct(1L, 2, "M"), new QueryOrderProduct(2L, 2, "L"))));

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(url)
                    .requestAttr("user", principalDetails.getUser())
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON);
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }
}