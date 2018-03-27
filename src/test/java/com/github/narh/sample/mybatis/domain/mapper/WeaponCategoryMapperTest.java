/*
 * Copyright (c) 2018, NARH https://github.com/NARH
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package com.github.narh.sample.mybatis.domain.mapper;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.narh.sample.mybatis.domain.model.Category;

import lombok.extern.slf4j.Slf4j;


/**
 * WeaponCategoryMapperのUTスクリプト
 * @author NARH https://github.com/NARH
 *
 */
@RunWith(SpringRunner.class) @MybatisTest @Slf4j
public class WeaponCategoryMapperTest {

  /** 試験対象*/
  @Autowired
  private WeaponCategoryMapper weaponCategoryMapper;

  @Test
  public void findByCode正常系テスト() throws Exception {
    Category category = weaponCategoryMapper.findByCode("01");
    assertThat("カテゴリがnullでないこと", category, is(not(nullValue())));
    if(log.isDebugEnabled()) log.debug(category.toString());
    assertThat("カテゴリコードが 01であること", category.getCode(), is("01"));
    assertThat("カテゴリ名が 剣であること", category.getName(), is("剣"));
    Category tmpCategory = new Category();

    tmpCategory.setCode("01");
    tmpCategory.setName("剣");
    assertThat("等しいこと", tmpCategory.equals(category), is(true));

    tmpCategory.setCode("02");
    assertThat("等しくないこと", tmpCategory.equals(category), is(false));

    assertThat("hashcode が null でないこと", category.hashCode(), is(not(nullValue())));
  }

  @Test
  public void findByCode存在しないテスト() throws Exception {
    Category category = weaponCategoryMapper.findByCode("99");
    assertThat("カテゴリがnullであること", category, is(nullValue()));
  }
}
