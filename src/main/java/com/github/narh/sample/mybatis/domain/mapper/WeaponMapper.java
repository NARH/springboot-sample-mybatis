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

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.narh.sample.mybatis.domain.model.Weapon;

/**
 * 武器マスターを操作するインターフェース
 *
 * @author NARH https://github.com/NARH
 *
 */
@Mapper
public interface WeaponMapper {

  /**
   * 武器コードを指定して武器情報を取得する
   *
   * @param code 武器コード
   * @return 武器情報
   * @throws SQLException SQL例外
   */
  public Weapon findByCode(final String code) throws SQLException;

  /**
   * 職業コードを指定して対応する武器情報を取得する
   *
   * @param job 検索する職業コード
   * @return 武器情報
   * @throws SQLException SQL例外
   */
  public List<Weapon> findByJob(@Param("job") final String job) throws SQLException;

  /**
   * 職業コードを指定して対応する武器情報の件数を取得する
   * ページネーション対応
   *
   * @param job 検索する職業コード
   * @return 登録件数
   * @throws SQLException SQL例外
   */
  public long countByJob(@Param("job") final String job) throws SQLException;

  /**
   * 職業コードを指定して対応する武器情報を取得する
   * ページネーション対応
   *
   * @param job 検索する職業コード
   * @param limit 取得件数
   * @param offset 取得開始位置
   * @return 武器情報
   * @throws SQLException SQL例外
   */
  public List<Weapon> findByJob(@Param("job") final String job
      , @Param("limit") int limit, @Param("offset") int offset) throws SQLException;
}
