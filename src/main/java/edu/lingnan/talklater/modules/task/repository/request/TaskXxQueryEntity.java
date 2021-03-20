package edu.lingnan.talklater.modules.task.repository.request;

import edu.lingnan.talklater.request.QueryEntity;

/**
 * Description:
 * date: 2021/3/16 20:43
 *
 * @author likunzhu
 * @since
 */
public class TaskXxQueryEntity extends QueryEntity {

    private String name;

    private String type;

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
