package utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));

    }

    public static String[] getNullPropertyNames(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);
        
        PropertyDescriptor[] properties = src.getPropertyDescriptors();

        List<String> propertyNames = new ArrayList<String>();
        for (var property : properties){
            if (src.getPropertyValue(property.getName()) == null)
                propertyNames.add(property.getName());
        }

        return propertyNames.toArray(new String[0]);
    }
}
