package com.nf.library.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nf.library.execption.AppException;
import io.jsonwebtoken.lang.Assert;

import java.io.IOException;
import java.io.OutputStream;

/**
 * json与Bean对象相互转化
 *
 * @author sam
 */
public class JsonUtils {

    /**
     * 传入一个Bean对象转化为json并输出
     * @param outputStream 字节流
     * @param object 对象
     */
    public static void write(OutputStream outputStream, Object object) {
        // 转换工具
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 将对象转换成json语句并输出
            mapper.writeValue(outputStream, object);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException("转json输出失败！", e);
        }
    }

        /**
         * 将Bean对象转换为json语句
         *
         * @param object 对象
         * @return String
         */
        public static String getJsonString(Object object) {
            // 转换工具
            ObjectMapper mapper = new ObjectMapper();
            try {
                // 将对象转换成json语句并输出
                return mapper.writeValueAsString(object);
            } catch (IOException e) {
                e.printStackTrace();
                throw new AppException("转json失败！", e);
            }
        }

        /**
         * 把json数组转为Bean对象
         *
         * @param json json语句
         * @return 返回一个泛型对象
         */
        public static <T> T readValue(String json, Class<? extends T> clazz) {
            // 转换器
            ObjectMapper mapper = new ObjectMapper();
            T bean = null;
            try {
                // 将json语句转化为Bean对象
                bean =  mapper.readValue(json, clazz);
            } catch (IOException e) {
                System.out.println("json转对象失败！");
            }
            return bean;
        }

    public static void main(String[] args) {
        String str = "{\"username\":\"123\"}";
        JwtSubject jwtSubject = readValue(str,JwtSubject.class);
        System.out.println(jwtSubject);
    }
}
