package edu.lingnan.talklater.api.menu.domain.response;

import javax.persistence.Column;

/**
 * Description:
 * date: 2021/3/13 11:25
 *
 * @author likunzhu
 * @since
 */
public class MenuOption {

    private String value;

    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
