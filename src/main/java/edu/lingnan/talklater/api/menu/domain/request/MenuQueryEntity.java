package edu.lingnan.talklater.api.menu.domain.request;

import edu.lingnan.talklater.request.QueryEntity;

/**
 * Description:
 * date: 2021/3/12 15:02
 *
 * @author likunzhu
 * @since
 */
public class MenuQueryEntity extends QueryEntity {

    private String menuName;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
