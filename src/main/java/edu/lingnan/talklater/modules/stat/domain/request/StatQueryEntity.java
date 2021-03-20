package edu.lingnan.talklater.modules.stat.domain.request;

import edu.lingnan.talklater.request.QueryEntity;

/**
 * Description:
 * date: 2021/3/16 20:35
 *
 * @author likunzhu
 * @since
 */
public class StatQueryEntity extends QueryEntity {

    private String type;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
