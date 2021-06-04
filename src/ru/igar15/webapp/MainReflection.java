package ru.igar15.webapp;

import ru.igar15.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume resume = new Resume("vasya");
        Method method = resume.getClass().getMethod("toString");
        String invokeResult = (String) method.invoke(resume);
        System.out.println(invokeResult);
    }
}
