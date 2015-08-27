/**
 * Copyright (c) 2014 OCC.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * OCC. ("Confidential Information").
 *
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of license agreement you entered
 * into with OCC.
 */
package org.occ.csp.web.controller.authority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongfang.csp.webframeworx.web.controller.CoreController;
import com.hongfang.webframeworx.authentication.annotation.SkipAuthorization;

/**
 * 
 * 作 業 代 碼 ：<br>
 * 作 業 名 稱 ：<br>
 * 程 式 代 號 ：LoginController.java<br>
 * 描             述 ：<br>
 * 組             織 ：OCC<br><br>
 *【 資 料 來 源】  ：<br>
 *【 輸 出 報 表】  ：<br>
 *【 異 動 紀 錄】  ：<br>
 * @author   : Mark Wong <br>
 * @version  : 1.0.0 2014/4/8<P>
 */

@Controller
@SkipAuthorization
public class LoginController extends CoreController {
	//TODO Logger (logback.xml)
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.out.println("in LoginController");
		return "root/index";
	}
}
