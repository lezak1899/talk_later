package edu.lingnan.talklater.modules.user.domain;

import lombok.val;

/**
 * Description:
 * date: 2021/3/14 20:46
 *
 * @author likunzhu
 * @since
 */
public enum RoleDict {

    普通用户("1","02042156-7f26-11eb-bb75-00ffbd07b1ad"),
    运维人员("2","02d5677e-7f26-11eb-bb75-00ffbd07b1ad"),
    系统管理员("3","f64e9b3f-7f25-11eb-bb75-00ffbd07b1ad");

    String val;
    String id;
    private RoleDict(String val,String id){ this.val = val; this.id=id;}
    public String getId() { return id; }
    public static RoleDict RoleDict(String val){
        switch (val) {
            case "1":
                return 普通用户;
            case "2":
                return 运维人员;
            case "3":
                return 系统管理员;
            default:
                return null;
        }
    }
}
