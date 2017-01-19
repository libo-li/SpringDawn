package com.reedl.common.sys.security;

import com.reedl.common.sys.entity.SysResource;
import com.reedl.common.sys.entity.SysRole;
import com.reedl.common.sys.service.ISysResourceService;
import com.reedl.common.sys.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 资源源数据定义实现类
 *
 * 用于加载所有角色、资源的关系。即定义某一资源可以被哪些角色访问
 * Created by Li Libo on 2016/12/12.
 */
@Component("customInvocationSecurityMetadataSource")
public class CustomInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /*
    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysResourceService sysResourceService;
*/
    /**
     * key：资源URL
     * value：角色配置项
     */
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public CustomInvocationSecurityMetadataSource(){
        loadResourceDefine();
    }

    /**
     * 根据一个URL，找到这个URL的对应资源的角色集合。
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        String url = ((FilterInvocation)o).getRequestUrl();
        Collection<ConfigAttribute> returnCollection = resourceMap.get(url);

//        if(returnCollection==null){
//            returnCollection = new ArrayList<ConfigAttribute>();
//            returnCollection.add(new SecurityConfig("ROLE_NO_USER"));
//        }

        return returnCollection;
    }

    /**
     * 加载资源和资源所属角色的一对多关系
     */
    private void loadResourceDefine() {

        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

        //获取资源列表
        //List<SysResource> resourceList = sysResourceService.getResourceList();
        SysResource res = new SysResource();
        res.setUrl("/main");
        List<SysResource> resourceList = new ArrayList<SysResource>();
        resourceList.add(res);
        if(resourceList != null && resourceList.size() > 0){

            Collection<ConfigAttribute> atts = null;

            for(SysResource resource : resourceList){
                //获取可以访问该资源的角色
                //List<SysRole> sysRoleList = sysRoleService.getRoleListByResourceId(resource.getId());
                SysRole r = new SysRole();
                r.setRolename("ROLE_ADMIN");
                List<SysRole> sysRoleList = new ArrayList<SysRole>();
                sysRoleList.add(r);
                atts = new ArrayList<ConfigAttribute>();
                ConfigAttribute ca = null;
                for(SysRole role : sysRoleList){
                    ca = new SecurityConfig(role.getRolename());
                    atts.add(ca);
                }

                resourceMap.put(resource.getUrl(), atts);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
