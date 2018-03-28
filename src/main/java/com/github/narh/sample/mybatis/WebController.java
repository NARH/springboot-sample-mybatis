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

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * コントローラークラス
 *
 * @author NARH https://github.com/NARH
 *
 */
@Controller @Slf4j
public class WebController {

  /**
   * 一覧ページ
   *
   * @param accountDetails ログイン情報
   * @return 一覧ページテンプレート
   */
  @RequestMapping({"/WeaponList", "/"})
  public String WeaponList(@AuthenticationPrincipal AccountDetails accountDetails, Model model) {
    if(log.isDebugEnabled()) log.debug(((AccountDetails) accountDetails).getUser().toString());

    // ログインユーザをモデルに設定する
    model.addAttribute("user", (((AccountDetails)accountDetails).getUser()));
    return "weapon_list";
  }

  /**
   * 詳細ページ
   *
   * @param accountDetails ログイン情報
   * @return 詳細ページテンプレート
   */
  @RequestMapping("/WeaponDetail")
  public String weaponDetail(@AuthenticationPrincipal AccountDetails accountDetails) {
    if(log.isDebugEnabled()) log.debug(((AccountDetails) accountDetails).getUser().toString());

    return "weapon_detail";
  }
}
