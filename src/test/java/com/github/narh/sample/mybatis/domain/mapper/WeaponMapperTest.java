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

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.narh.sample.mybatis.domain.model.Weapon;

import lombok.extern.slf4j.Slf4j;

/**
 * WeaponMapperのUTスクリプト
 *
 * @author NARH https://github.com/NARH
 *
 */
@RunWith(SpringRunner.class) @MybatisTest @Slf4j
public class WeaponMapperTest {

  /** 試験対象*/
  @Autowired
  private WeaponMapper weaponMapper;

  @Test
  public void findByCode正常系テスト() throws Exception {
    Weapon weapon = weaponMapper.findByCode("01");
    assertThat("武器情報がnullでないこと", weapon, is(not(nullValue())));
    if(log.isDebugEnabled()) log.debug(weapon.toString());
    assertThat("武器コードが 01であること", weapon.getCode(), is("01"));
    assertThat("武器名が 木のつるぎであること", weapon.getName(), is("木のつるぎ"));
    assertThat("カテゴリコードが 01であること", weapon.getCategory().getCode(), is("01"));
    assertThat("カテゴリ名が 剣であること", weapon.getCategory().getName(), is("剣"));
    assertThat("攻撃力が 50であること", weapon.getStrength(), is(50));
    assertThat("呪われていないこと", weapon.isCurse(), is(false));
  }

  @Test
  public void findByCode存在しないテスト() throws Exception {
    Weapon weapon = weaponMapper.findByCode("99");
    assertThat("武器情報がnullであること", weapon, is(nullValue()));
  }

  @Test
  public void findByJob正常系テスト() throws Exception {
    List<Weapon> results = weaponMapper.findByJob("01");
    assertThat("武器情報がnullでないこと", results, is(not(nullValue())));
    assertThat("結果が1件以上であること", results.size(), is(greaterThanOrEqualTo(1)));
    results.forEach(r->{
      if(log.isDebugEnabled()) log.debug(r.toString());
      assertThat("全てのカテゴリが が01 または 03 であること"
          , r.getCategory().getCode(), isIn(Arrays.asList("01","03")));
    });
  }

  @Test
  public void countByJob正常系テスト() throws Exception {
    long count = weaponMapper.countByJob("01");
    assertThat("結果が14件であること", count, is(14L));
  }

  @Test
  public void findByJobLimitOffet有り() throws Exception {
    List<Weapon> resultsAll = weaponMapper.findByJob("01");
    assertThat("武器情報がnullでないこと", resultsAll, is(not(nullValue())));
    assertThat("結果が3件以上であること", resultsAll.size(), is(greaterThanOrEqualTo(3)));
    resultsAll.forEach(r->{
      if(log.isDebugEnabled()) log.debug(r.toString());
    });

    List<Weapon> resultsPage1 = weaponMapper.findByJob("01", 3, 0);
    assertThat("武器情報がnullでないこと", resultsPage1, is(not(nullValue())));
    assertThat("結果が3件であること", resultsPage1.size(), is(3));
    resultsPage1.forEach(r->{
      if(log.isDebugEnabled()) log.debug(r.toString());
    });

    assertThat("0 武器コードが同じであること"
        , resultsPage1.get(0).getCode(), is(resultsAll.get(0).getCode()));
    assertThat("1 武器コードが同じであること"
        , resultsPage1.get(1).getCode(), is(resultsAll.get(1).getCode()));
    assertThat("2 武器コードが同じであること"
        , resultsPage1.get(2).getCode(), is(resultsAll.get(2).getCode()));

    assertThat("0 武器名が同じであること"
        , resultsPage1.get(0).getName(), is(resultsAll.get(0).getName()));
    assertThat("1 武器名が同じであること"
        , resultsPage1.get(1).getName(), is(resultsAll.get(1).getName()));
    assertThat("2 武器名が同じであること"
        , resultsPage1.get(2).getName(), is(resultsAll.get(2).getName()));

    List<Weapon> resultsPage2 = weaponMapper.findByJob("01", 3, 3);
    assertThat("武器情報がnullでないこと", resultsPage2, is(not(nullValue())));
    assertThat("結果が3件であること", resultsPage2.size(), is(3));
    resultsPage2.forEach(r->{
      if(log.isDebugEnabled()) log.debug(r.toString());
    });

    assertThat("3 武器コードが同じであること"
        , resultsPage2.get(0).getCode(), is(resultsAll.get(3).getCode()));
    assertThat("4 武器コードが同じであること"
        , resultsPage2.get(1).getCode(), is(resultsAll.get(4).getCode()));
    assertThat("5 武器コードが同じであること"
        , resultsPage2.get(2).getCode(), is(resultsAll.get(5).getCode()));

    assertThat("3 武器名が同じであること"
        , resultsPage2.get(0).getName(), is(resultsAll.get(3).getName()));
    assertThat("4 武器名が同じであること"
        , resultsPage2.get(1).getName(), is(resultsAll.get(4).getName()));
    assertThat("5 武器名が同じであること"
        , resultsPage2.get(2).getName(), is(resultsAll.get(5).getName()));
  }
}
