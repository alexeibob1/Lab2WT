package by.bsuir.lab2.controller.filter;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();

        Role role = (Role) session.getAttribute(SessionAttribute.ROLE);
        if (role == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + CommandName.GO_TO_HOME_COMMAND);
            return;
        }

        switch (role) {
            case ADMIN, DOCTOR, PHARMACIST -> filterChain.doFilter(servletRequest, servletResponse);
            case CLIENT ->
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + CommandName.GO_TO_HOME_COMMAND);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
