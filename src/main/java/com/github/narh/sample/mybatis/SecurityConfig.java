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

package com.github.narh.sample.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * セキュリティー設定
 *
 * @author NARH https://github.com/NARH
 *
 */
@Configuration @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * パスワードハッシュ化
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * MyBatis を使った認証情報取得サービスをコンテナに格納する
   *
   * @return 認証情報取得サービス
   */
  @Bean
  public AccountDetailsService accountDetailsService() {
    return new AccountDetailsService();
  }

  /**
   * セキュリティー除外設定
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(
      "/h2-console/*"                 // H2 Database Console 対応
     );
  }

  /**
   * セキュリティー設定
   */
  public void configure(HttpSecurity http) throws Exception {
    /* 認証対象の定義*/
    http.authorizeRequests()
      .antMatchers("index.html").permitAll()    // index.html は認証不要
      .antMatchers("/css/**").permitAll()       // /css/** は認証不要
      .antMatchers("/images/**").permitAll()    // /images/** は認証不要
      .antMatchers("/js/**").permitAll()        // /js/** は認証不要
      .antMatchers("/Login**").permitAll()        // Login 画面は認証不要
      .anyRequest().authenticated();            // その他は認証対象

    /* ログインURL*/
    http.formLogin()
      .loginPage("/Login")
      .failureUrl("/Login?error")
      .defaultSuccessUrl("/", true).permitAll();

    /* ログアウトURL*/
    http.logout()
      .logoutUrl("/Logout")
      .clearAuthentication(true)
      .logoutRequestMatcher(new AntPathRequestMatcher("/Logout"))
      .invalidateHttpSession(true)
      .permitAll();

    /* remember-me*/
    http.rememberMe().disable();
  }
}
