package com.hhy.shop.sso.controller.common.view;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhy.shop.sso.controller.common.constants.GlobalConstants;
import com.hhy.shop.sso.controller.common.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * 微医自定义freemarkerview
 *
 * @author hehy
 * @create 2017-02-23
 **/
public class ShopFreemarkerView extends FreeMarkerView {
    private FreeMarkerViewResolver freeMarkerViewResolver;

    /** 文件服务器配置常量Key */
    private final static String IMAGE_SERVER_KEY = "imageServer";

    /** 静态服务器配置常量Key */
    private final static String STATIC_SERVER_KEY = "staticServer";

    /** 静态服务器配置常量Key */
    private final static String SHOP_SERVER_KEY = "shopServer";

    @Override
    protected void initServletContext(ServletContext servletContext) throws BeansException {
        super.initServletContext(servletContext);
        freeMarkerViewResolver = getApplicationContext().getBean(FreeMarkerViewResolver.class);
        // 检查引用的FreeMarker框架式原生的还是重新封装的（com.greenline.freemaker_*.jar）
        if (!checkReferFreeMarkerSource()) {
            logger.error("The freemaker framework must be used to re package(com.greenline.freemaker_*.jar) ");
        }
    }


    /**
     * <p>
     * 此处处理的model中的变量只会在/home/views/*.ftl中生效；<br/>
     * 若layout层也使用该变量，只有更改this.mursiFreeMarkerViewResolver.
     * getAttributesMap()里的值才可行；<br/>
     * 若widget层也使用该变量，可同上，也可更改wigetController<br/>
     * 继续扩展MursiFreeMarkerView doRender 主要原因是：页面渲染使用的Constants现在是Variables
     * </p>
     */
    @Override
    protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map<String, Object> attributes = this.freeMarkerViewResolver.getAttributesMap();

        String imageServer = (String) attributes.get(IMAGE_SERVER_KEY);
        if (logger.isInfoEnabled()) {
            logger.info("mursi freemarker imageServer0000 >>> " + imageServer + "request scheme >> "
                    + request.getScheme());
        }

        String staticServer = (String) attributes.get(STATIC_SERVER_KEY);
        if (logger.isInfoEnabled()) {
            logger.info("mursi freemarker staticServer0000 >>> " + staticServer + "request scheme >> "
                    + request.getScheme());
        }

        String shopServer = (String) attributes.get(SHOP_SERVER_KEY);
        if (logger.isInfoEnabled()) {
            logger.info("mursi freemarker staticServer0000 >>> " + shopServer + "request scheme >> "
                    + request.getScheme());
        }

        // 页面渲染需要额外的几个非常量
        model.put(GlobalConstants.IMAGE_SERVER, CommonUtils.switchHttpAndHttps(imageServer, request.getScheme()));
        model.put(GlobalConstants.STATIC_SERVER, CommonUtils.switchHttpAndHttps(staticServer, request.getScheme()));
        model.put(GlobalConstants.SHOP_SERVER, CommonUtils.switchHttpAndHttps(shopServer, request.getScheme()));

        // Start 只有在第三方登录进行DomainInterceptor拦截之后才会生效的代码
        String servername = (String) request.getAttribute(GlobalConstants.REQUEST_ATTR_KEY_SERVER_NAME);
        if (StringUtils.isNotBlank(servername)) {
            // 获取域名+上下文
            String domainservername = request.getScheme() + "://" + servername + CommonUtils.getServerPort(request)
                    + request.getContextPath();
            model.put(GlobalConstants.SHOP_SSO_SERVER, domainservername);
        }

        if (logger.isInfoEnabled()) {
            logger.info("mursi freemarker imageServer >>> " + String.valueOf(model.get(GlobalConstants.IMAGE_SERVER))
                    + "staticServer >>> " + String.valueOf(model.get(GlobalConstants.STATIC_SERVER)));
        }

        super.doRender(model, request, response);
        // 渲染结束，清除代码
    }


    /**
     * 检测引入的FreeMarker框架是否是咱们扩展的， 若是原生的FreeMarker就提示报错，并抛出异常
     *
     * @return
     */
    private boolean checkReferFreeMarkerSource() {
        try {
            Class<?> clazz = Class.forName("freemarker.core.DollarVariable");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if ("doNoEscape".equals(method.getName())) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            logger.error("内部异常", e);
        }

        return false;
    }

}
