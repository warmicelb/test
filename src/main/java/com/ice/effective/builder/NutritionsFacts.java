package com.ice.effective.builder;

/**
 * 通过构造器构建包含多个参数的类实例
 * @author: ice
 * @create: 2019/3/22
 **/
public class NutritionsFacts {
    private int size;
    private int fat;
    private String material = "PLASTIC";

    public static class Builder{
        //required
        private int size;
        private int fat;
        //optional
        private String material;
        public Builder(int size,int fat) {
            this.size=size;
            this.fat=fat;
        }
        public Builder material(String material){
            this.material = material;
            return this;
        }
    }
    public NutritionsFacts(Builder builder){
        this.size = builder.size;
        this.fat = builder.fat;
        this.material = builder.material;
    }
}
