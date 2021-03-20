package edu.lingnan.talklater.modules.security;

import edu.lingnan.talklater.modules.user.domain.UserXx;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description:
 * date: 2021/3/19 17:39
 *
 * @author likunzhu
 * @since
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取请求的RUi:去除http:localhost:8080这部分剩下的

        HttpSession session = request.getSession();
        UserXx currentUser = (UserXx) session.getAttribute("currentUser");

        /**
         * 验证
         */
        if (currentUser!=null) {
            System.out.println("已登陆");
            return true;
        }

        return false;
    }

}

