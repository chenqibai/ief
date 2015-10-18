package ief.enums;

import ief.model.KeyValueModel;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by zhangdongsheng on 15/6/27.
 */
public enum CategoryEnum {
    FOREIGN_LITERATURE("外国文学", 1),
    CHINESE_LITERATURE("中国文学", 2),
    CARTOON("漫画", 3),
    HISTORY("历史", 4),
    PHILOSOPHY("哲学", 5),
    MENTALITY("心理", 6),
    BIOGRAPHY("传记", 7),
    SOCIOLOGY("社会学", 8),
    DESIGN("设计", 9),
    BUILDING("建筑", 10),
    MILITARY("军事", 11),
    EDUCATION("教育", 12),
    MANAGE("管理", 13),
    ECONOMY("经济", 14),
    MARKETING("营销", 15),
    INTERNET("互联网", 16),
    CAVE_OUT("创业", 17);

    private String name;
    private int code;

    CategoryEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static CategoryEnum getCategoryByName(String name){
        if(StringUtils.isEmpty(name)) return null;

        for(CategoryEnum categoryEnum : CategoryEnum.values()){
            if(categoryEnum.getName().equals(name))
                return categoryEnum;
        }

        return null;
    }

    public static CategoryEnum getCategoryById(int code){
        if(code == 0) return null;

        for(CategoryEnum categoryEnum : CategoryEnum.values()){
            if(categoryEnum.getCode() == code)
                return categoryEnum;
        }

        return null;
    }

    private static final LinkedList<KeyValueModel> list = new LinkedList<>();
    public static final HashMap<Integer, String> CATEGORY_MAP = new HashMap<Integer, String>(){
        {
            for(CategoryEnum categoryEnum : CategoryEnum.values()) {
                put(categoryEnum.getCode(), categoryEnum.getName());
            }
        }
    };
    static {
        for(CategoryEnum categoryEnum : CategoryEnum.values()){
            list.add(new KeyValueModel(categoryEnum.getCode(), categoryEnum.getName()));
        }
    }

    public static LinkedList<KeyValueModel> listAllCategoryies(){
        return  list;
    }

    @Override
    public String toString() {
        return "CategoryEnum{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(CategoryEnum.CATEGORY_MAP.get(2));
    }
}
