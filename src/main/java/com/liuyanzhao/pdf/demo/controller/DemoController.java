package com.liuyanzhao.pdf.demo.controller;

import com.liuyanzhao.pdf.demo.util.PdfUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {

    @GetMapping("pdf")
    public void test(HttpServletResponse response) throws Exception {
        Map<String, Object> variables = new HashMap<>();
        // 资源信息
        // 资源名称
        variables.put("resourceName", "XXXXXXXXXX");
        // 目录名称
        variables.put("infoName", "XXXXXXXXXX");
        //资源类型
        variables.put("resourceType", "XXXXXXXXXX");
        // 数据提供方
        variables.put("resOrgName", "XXXXXXXXXX");

        // 资源需求方
        variables.put("orgName", "XXXXXXXXXX");
        // 需求方系统
        variables.put("systemName", "XXXXXXXXXX");
        // 联系人
        variables.put("linkMan", "XXXXXXXXXX");
        // 联系手机
        variables.put("linkPhone", "XXXXXXXXXX");
        // 申请日期
        variables.put("orderDate", "XXXXXXXXXX");
        // 申请理由
        variables.put("orderReason", "XXXXXXXXXX");

        // 审核人
        variables.put("prejuUserName", "XXXXXXXXXX");
        // 审核结果
        variables.put("prejuResult", "XXXXXXXXXX");
        // 审核日期
        variables.put("prejuDate", "XXXXXXXXXX");
        // 审核意见
        variables.put("prejuSuggest", "XXXXXXXXXX");

        PdfUtil.exportPdf(response, "审批记录", "audit_order_record.html", variables);
    }
}
