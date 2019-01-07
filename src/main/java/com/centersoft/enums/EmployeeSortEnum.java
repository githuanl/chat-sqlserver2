package com.centersoft.enums;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Tanghailong
 * @Date Created in 2017/11/15
 * @see
 */
public enum EmployeeSortEnum {
    regular("正式"),//正式
    quit("离职"),//离职
    dismiss("辞退"),//辞退
    retire("退休"),//退休
    create("新增"),//新增
    probation("试用"),
    internship("实习");

    private String text;
    EmployeeSortEnum(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
