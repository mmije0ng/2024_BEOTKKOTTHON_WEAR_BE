package com.backend.wear.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@DynamicInsert //save할 때 null 값을 배제하고 insert
@DynamicUpdate
@Table(name="users")
@Entity
public class User extends BaseEntity {

    //아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //사용자 아이디
    @NotNull
    @Column(name="user_id",unique = true)
    private String userId;

    //사용자 패스워드
    @NotNull
    @Column(name="user_password",unique = true)
    private String userPassword;

    //이름 (실명)
    @NotNull
    @Column(name="user_name",unique = true)
    private String userName;

    //닉네임
    @NotNull
    @Column(name="nick_name",unique = true)
    private String nickName;

    //대학교 이메일
    @NotNull
    @Column(name="university_email",unique = true)
    private String universityEmail;

    //환경 점수
    @Size(min = 0, max = 100)
    @Column(name="point",columnDefinition = "integer default 0")
    private Integer point;

    //환경 레벨
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="level", columnDefinition = "varchar(10) default 'SEED'")
    private EnvironmentLevel level;

    //스타일
    //리스트로 수정 필요_
    @Enumerated(EnumType.STRING)
    @Column(name="style", columnDefinition = "varchar(10) default 'VINTAGE'")
    private Style style;

    //프로필 이미지
    @Column(name="profile_image", columnDefinition = "varchar(255) default 'default_image' ")
    private String profileImage;

    //대학교
    @ManyToOne
    @JoinColumn(name="university_id")
    @ToString.Exclude
    University university;

    //판매 내역
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();

    //기부 내역
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Donation> donationList=new ArrayList<>();

    //찜목록
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Wish> wishList=new ArrayList<>();

    //차단한 사용자 이름
//    @Column(name="blocked_user_name")
//    private List <String> blockedUserNameList=new ArrayList<>();
}