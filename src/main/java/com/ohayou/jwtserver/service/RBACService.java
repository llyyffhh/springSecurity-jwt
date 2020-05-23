package com.ohayou.jwtserver.service;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liyan
 * @date 2020/5/20 下午10:14
 */
@Service("rbacService")
public class RBACService {

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Order(1)
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String requestURI = request.getRequestURI();
            List<String[]> collect = userDetails.getAuthorities().stream().map(item -> {
                String[] split = item.getAuthority().split(",");
                return split;
            }).collect(Collectors.toList());


            return Arrays.stream(collect.get(0)).anyMatch(
                    url -> pathMatcher.matchStart(requestURI,url)
            );
//            return userDetails.getAuthorities().contains(grantedAuthorities.get(0));
        }
        return false;
    }
}
