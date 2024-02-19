package br.com.fcolucasdev.apiprofessionals.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class Utils {

  public static void copyNonNullProperties(Object source, Object target) {
    BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
  }

  public static String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    PropertyDescriptor[] pds = src.getPropertyDescriptors();
    Set<String> emptyNames = new HashSet<>();

    for (PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null)
        emptyNames.add(pd.getName());
    }

    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }

  public static Object filterFields(Object source, List<String> fields) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    PropertyDescriptor[] pds = src.getPropertyDescriptors(); // list of fields in the source object

    if (fields.isEmpty()) {
      return source;
    }

    for (PropertyDescriptor pd : pds) {
      if (!pd.getName().equals("class") && !pd.getName().equals("id") && !fields.contains(pd.getName())) {
        src.setPropertyValue(pd.getName(), null);

      }
    }

    return src.getWrappedInstance();
  }
}
