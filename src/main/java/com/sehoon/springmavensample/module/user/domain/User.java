package com.sehoon.springmavensample.module.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sehoon.springmavensample.common.domain.AbstractAuditingEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A AdminAccount.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "sample_user")
@Where(clause = "is_deleted is null or is_deleted = 0")
public class User extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "account", length = 50, nullable = false)
    private String account;

    @NotNull
    @Size(max = 255)
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Size(max = 30)
    @Column(name = "name", length = 30)
    private String name;

    @Size(max = 50)
    @Column(name = "eng_name", length = 50)
    private String engName;

    @Size(max = 30)
    @Column(name = "department_name", length = 30)
    private String departmentName;

    @Size(max = 30)
    @Column(name = "position_name", length = 30)
    private String positionName;

    @Size(max = 200)
    @Column(name = "profile_url", length = 200)
    private String profileUrl;

    @Size(max = 30)
    @Column(name = "employ_no", length = 30)
    private String employNo;

    @Size(max = 200)
    @Column(name = "email_addr", length = 200)
    private String emailAddr;

    @Size(max = 30)
    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Size(max = 100)
    @Column(name = "enter_card_no", length = 100)
    private String enterCardNo;

    @Column(name = "is_deleted", nullable = true, insertable = false, updatable = false)
    @JsonIgnore
    private Boolean isDeleted;
}
