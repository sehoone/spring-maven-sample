package com.sehoon.springmavensample.common.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.DerbyTemplates;
import com.querydsl.sql.SQLTemplates;

/**
 * Querydsl 설정
 */
@Configuration
public class QuerydslConfig {
	@Autowired
	EntityManager em;

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(em);
	}

	@Bean
	public JPASQLQuery<?> sqlQueryFactory() {
		SQLTemplates templates = new DerbyTemplates();

		return new JPASQLQuery<Void>(em, templates);
	}
}
