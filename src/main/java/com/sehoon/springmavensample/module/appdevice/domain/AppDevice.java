package com.sehoon.springmavensample.module.appdevice.domain;

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

@Getter
@Setter
@Entity
@Table(name = "sample_app_device")
@Where(clause = "is_deleted = 0")
public class AppDevice extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Size(max = 100)
    @Column(name = "uuid", length = 100, nullable = false)
    private String uuid;

    @NotNull
    @Size(max = 1)
    @Column(name = "os_code", length = 1, nullable = false)
    private String osCode;

    @Column(name = "app_version")
    private String appVersion;

    @Size(max = 200)
    @Column(name = "device_info", length = 200)
    private String deviceInfo;

    @Column(name = "is_deleted", nullable = true, insertable = false, updatable = false)
    @JsonIgnore
    private Boolean isDeleted;
}
