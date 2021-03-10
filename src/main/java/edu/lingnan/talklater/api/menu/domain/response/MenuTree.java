package edu.lingnan.talklater.api.menu.domain.response;

import edu.lingnan.talklater.api.menu.domain.MenuXx;
import edu.lingnan.talklater.api.menu.service.MenuService;

import javax.persistence.Column;
import java.util.List;

/**
 * Description: 二级菜单实体类
 * date: 2021/3/8 11:43
 *
 * @author likunzhu
 * @since
 */
public class MenuTree {

    private String id;

    private String fid;

    private String name;

    private String url;

    private String icon;

    private Integer order;

    private List<MenuXx> childs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<MenuXx> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuXx> childs) {
        this.childs = childs;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
