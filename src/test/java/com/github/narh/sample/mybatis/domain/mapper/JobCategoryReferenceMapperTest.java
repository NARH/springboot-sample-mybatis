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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.narh.sample.mybatis.domain.model.Category;
import com.github.narh.sample.mybatis.domain.model.Job;
import com.github.narh.sample.mybatis.domain.model.JobCategoryReference;

import lombok.extern.slf4j.Slf4j;

/**
 * JobCategoryReferenceMapperのUTスクリプト
 * @author NARH https://github.com/NARH
 *
 */
@RunWith(SpringRunner.class) @MybatisTest @Slf4j
public class JobCategoryReferenceMapperTest {

  @Autowired
  private JobCategoryReferenceMapper jobCategoryReferenceMapper;

  @Test
  public void findAll正常系テスト() throws Exception {
    List<JobCategoryReference> results = jobCategoryReferenceMapper.findAll();
    assertThat("結果がnullでないこと", results, is(not(nullValue())));
    assertThat("結果が９件であること", results.size(), is(9));
    if(log.isDebugEnabled()) results.forEach(r->{log.debug(r.toString());});
  }

  @Test
  public void findByJob正常系テスト() throws Exception {
    List<JobCategoryReference> results = jobCategoryReferenceMapper.findByJob("02");
    assertThat("結果がnullでないこと", results, is(not(nullValue())));
    assertThat("結果が１件であること", results.size(), is(1));

    Job job = results.get(0).getJob();
    assertThat("職業コードが 02であること", job.getCode(), is("02"));
    assertThat("職業が魔導士であること", job.getName(), is("魔導士"));
    assertThat("職業が呪い利用可否がfalseであること", job.isCurse(), is(false));

    Category category = results.get(0).getCategory();
    assertThat("武器カテゴリコードが 02であること", category.getCode(), is("02"));
    assertThat("武器カテゴリ名が杖であること", category.getName(), is("杖"));
  }

  @Test
  public void findByJob存在しないテスト() throws Exception {
    List<JobCategoryReference> results = jobCategoryReferenceMapper.findByJob("99");
    assertThat("結果がnullでないこと", results, is(not(nullValue())));
    assertThat("結果が０件であること", results.size(), is(0));
  }
}
