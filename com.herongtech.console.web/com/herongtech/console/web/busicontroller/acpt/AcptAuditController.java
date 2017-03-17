package com.herongtech.console.web.busicontroller.acpt;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.herongtech.console.core.controller.BaseController;

/**
 * 承兑审核Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/acptAuditController")
public class AcptAuditController extends BaseController {

}
