package com.demo.store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GSJ
 * Date: 2016/10/22
 * Time: 13:43
 */
public class Demo {

        public static void main(String[] args) {
            List list = new ArrayList();
            list.add("张三");
            list.add("李四");
            list.add("王五");
            list.add(2,"赵六");
            list.add("赵六");
            System.out.println("集合的元素："+ list.get(2));


        }

}
