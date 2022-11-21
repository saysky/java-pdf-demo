package com.liuyanzhao.pdf.demo.util;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.Map;

public class PdfUtil {

    /**
     * @param response     http请求后的相应
     * @param pdfFileName  pdf文件名称(不包含pdf后缀)
     * @param templateName 模板名称
     * @param variables    模板变量
     */
    public static void exportPdf(HttpServletResponse response, String pdfFileName, String templateName, Map<String, Object> variables) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((pdfFileName + ".pdf").getBytes(), "iso8859-1"));
        OutputStream os = response.getOutputStream();
        //构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("pdf/templates/"); //模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html"); //模板文件后缀
//        FileTemplateResolver resolver = new FileTemplateResolver();
//        resolver.setPrefix(ServletActionContext.getServletContext().getRealPath("pdf/templates") + File.separator); // 如果放在web下
//        resolver.setSuffix(".html");

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文(Model)
        Context context = new Context();
        context.setVariable("pdfFileName", pdfFileName);
        context.setVariables(variables);
        //渲染模板
        String example = templateEngine.process(templateName, context);
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFont(ResourceUtils.getFile("classpath:pdf/fonts/SIMHEI.TTF"), "stxihei");
//        builder.useFont(new File(ServletActionContext.getServletContext().getRealPath("/pdf/fonts/SIMHEI.TTF")), "stxihei"); // 如果是放在web下面
        builder.useFastMode();
        builder.withHtmlContent(example, null);
        builder.toStream(os);
        builder.run();
    }
}
