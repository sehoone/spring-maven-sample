package com.sehoon.springmavensample.module.appversion.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sehoon.springmavensample.common.domain.AbstractAuditingEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sample_app_version")
@Where(clause = "is_deleted = 0")
public class AppVersion extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 1)
    @Column(name = "os_code", length = 1)
    private String osCode;

    @Size(max = 15)
    @Column(name = "version", length = 15)
    private String version;

    @Size(max = 200)
    @Column(name = "file_down_url", length = 200)
    private String fileDownUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "is_force_update")
    private Boolean isForceUpdate;

    @Column(name = "is_deleted", nullable = true, insertable = false, updatable = false)
    @JsonIgnore
    private Boolean isDeleted;
}
