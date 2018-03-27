/*
 * Copyright (c) 2018, NARH https://github.com/NARH
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * * Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND
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

package com.github.narh.sample.mybatis;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * AccountDetailService のUTスクリプト
 *
 * @author NARH https://github.com/NARH
 *
 */
@RunWith(SpringRunner.class) @SpringBootTest @Slf4j
public class AccountDetailsServiceTest {

  /** 試験対象*/
  @Autowired
  private AccountDetailsService accountDetailsService;

  @Test
  public void loadUserByUsername正常系テスト() throws Exception {
    UserDetails account = accountDetailsService.loadUserByUsername("001@example.com");
    assertThat("UserDerails が null でないこと", account, is(not(nullValue())));
    assertThat("UserDetails.getAuthorities() が1件であること", account.getAuthorities().size(), is(1));
    if(log.isDebugEnabled()) log.debug("username: {}, password: {}, authorities:{}"
        ,account.getUsername(), account.getPassword(), account.getAuthorities());
  }

  @Test(expected=UsernameNotFoundException.class)
  public void loadUserByUsername存在しないユーザテスト() throws Exception {
    accountDetailsService.loadUserByUsername("foo@example.com");
  }
}
