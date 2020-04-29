import java.lang.reflect.Field;
import java.util.Collection;

public class ClassSerializer implements Serializer {
    private final SerializerHelper serializerHelper;

    ClassSerializer(SerializerHelper serializerHelper) {
        this.serializerHelper = serializerHelper;
    }

    public String serialize(Object o) {
        return getFieldsWithIndentation(o, "");
    }

    String getFieldsWithIndentation(Object obj, String indentation) {
        if (obj == null) {
            return "null";
        }

        Class objectClass = obj.getClass();
        String className = objectClass.getSimpleName();
        StringBuilder result = new StringBuilder(serializerHelper.getStart(className));

        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            serializeField(obj, indentation, result, field);
        }

        result.append(indentation).append(serializerHelper.getEnd(className));
        return result.toString();
    }

    private void serializeField(Object o, String indentation, StringBuilder result, Field field) {
        field.setAccessible(true);
        Class fieldClass = field.getType();
        String fieldName = field.getName();
        try {
            Object fieldObject = field.get(o);

            if (isPrimitiveTypeOrWrapperOrString(fieldClass)) {
                serializerHelper.serializePrimitiveTypeOrWrapperOrString(indentation, result, fieldObject, fieldName);
            } else if(isCollection(fieldClass)) {
                serializerHelper.serializeCollection(indentation, result, fieldObject, fieldName);
            } else {
                serializerHelper.serializeClass(indentation, result, fieldObject, this, fieldName);
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isCollection(Class fieldClass) {
        return Collection.class.isAssignableFrom(fieldClass);
    }

    private static boolean isPrimitiveTypeOrWrapperOrString(Class<?> type) {
        return  type.isPrimitive() || type == Double.class || type == Float.class || type == Long.class
                || type == Integer.class || type == Short.class || type == Character.class ||type == Byte.class
                || type == Boolean.class || type == String.class;
    }
}
