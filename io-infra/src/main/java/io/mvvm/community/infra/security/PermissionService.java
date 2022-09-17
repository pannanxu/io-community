package io.mvvm.community.infra.security;

import org.springframework.stereotype.Service;

/**
 * example @PreAuthorize("@ps.hasRole('admin1')")
 *
 * @author: Pan
 **/
@Service("ps")
public class PermissionService {

    /**
     * 判断用户是否拥有某个资源
     *
     * @return 用户是否具备某资源
     */
    public boolean hasResource(String resource) {
        return true;
    }

}