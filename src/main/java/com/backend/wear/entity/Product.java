package com.backend.wear.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamicInsert
@DynamicUpdate
@Table(name="product")
@Entity
public class Product extends BaseEntity {

    //아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //상품 이름, 제목
    @NotNull
    @Column(name="product_name")
    private String productName;

    //상품 가격
    @Column(nullable = false)
    private Integer price;

    //상품 이미지
 /*   @NotNull
    @ElementCollection
    @Column(name="product_image")
    private List<String> productImage = new ArrayList<>();

*/

    @NotNull
    @Column(name="product_image")
    private String productImage;

    //상품 내용, 설명
    @NotNull
    @Column(name="product_content")
    private String productContent;

    //상품 상태
    @NotNull
    @Column(name="product_status",nullable = false)
    private String productStatus;

    //거래 상태
    //null이면 거래 완료, onSale이면 판매 중
    @Column(name="post_status",columnDefinition = "varchar(255) default 'onSale'")
    private String postStatus;

    //거래 장소
    @NotNull
    private String place;

    //상품 숨김 여부
    @Column(name="is_private",columnDefinition = "boolean default false")
    private boolean isPrivate;

    //판매자
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    //카테고리
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    //찜
    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private Wish wish;

    //찜 횟수, 조회수
    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private Count count;

    //채팅방
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ChatRoom> chatRoomList=new ArrayList<>();

    public void setUpdatedAt(LocalDateTime updatedAt) {
        updatedAt= LocalDateTime.now();
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        deletedAt= LocalDateTime.now();
    }

    @Builder
    public Product(String productName, int price, String productImage, String productContent, String productStatus, String postStatus, String place, boolean isPrivate, User user, Category category, Wish wish, Count count) {
        this.productName = productName;
        this.price = price;
        this.productImage = productImage;
        this.productContent = productContent;
        this.productStatus = productStatus;
        this.postStatus = postStatus;
        this.place = place;
        this.isPrivate = isPrivate;
        this.user = user;
        this.category = category;
        this.wish = wish;
        this.count = count;
    }
}