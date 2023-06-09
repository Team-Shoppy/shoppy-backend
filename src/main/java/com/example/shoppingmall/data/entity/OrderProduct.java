package com.example.shoppingmall.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Table(name="order_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int count; // 수량

    @Column(nullable = false)
    private String size;

    /** ManyToOne의 기본 Fetch는 EAGER */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

    @Builder
    public OrderProduct(Long id, int count, Order order, Product product, String size) {
        this.id = id;
        this.count = count;
        this.order = order;
        this.product = product;
        this.size = size;
    }

    public void setOrder(Order order){
        if (this.order != null){
            this.order.getOrderProductList().remove(this);
        }

        this.order = order;
    }

    public void setOption(int count, String size) {
        this.count = count;
        this.size = size;
    }
}
